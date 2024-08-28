package com.example.slapjack;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class BattleHandler {
    Hand hand = new Hand();
    Jack enemy;
    Jack player;
    Boolean isPlayersTurn;
    Card card_Slap,card_DoubleSlap,card_Tense;
    ProgressBar enemyHealth;
    ProgressBar playerHealth;
    TextView battleDescription;

    public BattleHandler(Jack enemy, Jack player, ProgressBar enemyHealth,ProgressBar playerHealth, TextView battleDescription ) {
        this.enemy = enemy;
        this.player = player;
        this.enemyHealth = enemyHealth;
        this.playerHealth = playerHealth;
        this.battleDescription = battleDescription;


        card_Slap = new Card(1,"Slap",0,1);
        card_DoubleSlap = new Card(2,"Double Slap",0,3);
        card_Tense = new Card(1,"Tense",1,0);

        isPlayersTurn = true;
    }

    public Card getRandomCard (){
        Random rand = new Random();
        int card = rand.nextInt(3);
        card++;
        switch (card){
            case 1:
                return card_Slap;
            case 2:
                return card_DoubleSlap;
            case 3:
                return card_Tense;
        }
        return card_Slap;
    }

    void playerCardAction(int card) {
        if (enemy.health >= 0 && player.health >= 0 && isPlayersTurn) {
            System.out.println("This is the enemy health" + enemy.health);
            if (hand.getCardName(card).equals("Slap")) {
                enemy.health -= card_Slap.attack + player.power;
                System.out.println("Player used Slap");
            } else if (hand.getCardName(card).equals("Double Slap")) {
                enemy.health -= card_DoubleSlap.attack + player.power;
                System.out.println("Player used Double Slap");
            } else if (hand.getCardName(card).equals("Tense")) {
                player.guard += card_Tense.defense;
                System.out.println("Player used Tense");
            }
            System.out.println("This is the Enemy health" + enemy.health);
            enemyHealth.setScaleX((float) enemy.health / 100);
        }
        if (enemy.health <=0){
            enemyHealth.setVisibility(View.INVISIBLE);
        }
    }
    void enemyCardAction(String cardName){
        if (enemy.health >= 0 && player.health >= 0 && !isPlayersTurn) {
            System.out.println("This is the player health" + player.health);
            System.out.println("Enemy used" + cardName);
            if (cardName.equals("Slap")) {
                player.health -= card_Slap.attack + enemy.power;
            } else if (cardName.equals("Double Slap")) {
                player.health -= card_DoubleSlap.attack + enemy.power;
            } else if (cardName.equals("Tense")) {
                enemy.guard += card_Tense.defense;
            }
            System.out.println("This is the player health" + player.health);
            playerHealth.setScaleX((float) player.health / 100);
        }
        if (player.health <=0){
            playerHealth.setVisibility(View.INVISIBLE);
        }
        isPlayersTurn = true;
    }
    public void cardClicked(int card) {

        playerCardAction(card);
        isPlayersTurn = false;
    }
}
