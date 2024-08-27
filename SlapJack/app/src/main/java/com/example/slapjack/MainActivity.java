package com.example.slapjack;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Hand hand = new Hand();
    ImageButton cardInHandPosition1, cardInHandPosition2, cardInHandPosition3, cardInHandPosition4;
    ProgressBar enemyHealth,playerHealth;
    Button giveCardsButton;
    Jack player = new Jack("Main Character", 100, 1 , 1);
    Jack enemy = new Jack("Glass Jack", 50, 1 , 1);
    Card card_Slap,card_DoubleSlap,card_Tense;
    Boolean isPlayersTurn;
    TextView enemyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            giveCards(v);
            return insets;
        });
        onInit();


    }

    private void onInit() {
        cardInHandPosition1 = findViewById(R.id.cardInHandPosition1);
        cardInHandPosition2 = findViewById(R.id.cardInHandPosition2);
        cardInHandPosition3 = findViewById(R.id.cardInHandPosition3);
        cardInHandPosition4 = findViewById(R.id.cardInHandPosition4);

        giveCardsButton = findViewById(R.id.giveCardsButton);
        enemyHealth = findViewById(R.id.enemyHealth);
        enemyHealth.setScaleX((float) enemy.health /100);

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setScaleX((float) player.health /100);

        enemyName = findViewById(R.id.enemyName);

        card_Slap = new Card(1,"Slap",0,1);
        card_DoubleSlap = new Card(2,"Double Slap",0,3);
        card_Tense = new Card(1,"Tense",1,0);

        enemyName.setText(enemy.name);

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

    public void giveCards(View v){

        Random rand = new Random();
        hand.clear();
        for (int i = 1; i <= 4; i++)
        {
            Card tempCard = getRandomCard();
            hand.addToHand(tempCard);
            if(tempCard.name.equals("Slap")){
                switch (i) {
                    case 1:
                    cardInHandPosition1.setImageResource(R.drawable.card_slap);
                    break;
                    case 2:
                        cardInHandPosition2.setImageResource(R.drawable.card_slap);
                        break;
                    case 3:
                        cardInHandPosition3.setImageResource(R.drawable.card_slap);
                        break;
                    case 4:
                        cardInHandPosition4.setImageResource(R.drawable.card_slap);
                        break;
                }
            }
            if(tempCard.name.equals("Double Slap")){
                switch (i) {
                    case 1:
                        cardInHandPosition1.setImageResource(R.drawable.card_doubleslap);
                        break;
                    case 2:
                        cardInHandPosition2.setImageResource(R.drawable.card_doubleslap);
                        break;
                    case 3:
                        cardInHandPosition3.setImageResource(R.drawable.card_doubleslap);
                        break;
                    case 4:
                        cardInHandPosition4.setImageResource(R.drawable.card_doubleslap);
                        break;
                }
            }
            if(tempCard.name.equals("Tense")){
                switch (i) {
                    case 1:
                        cardInHandPosition1.setImageResource(R.drawable.card_tense);
                        break;
                    case 2:
                        cardInHandPosition2.setImageResource(R.drawable.card_tense);
                        break;
                    case 3:
                        cardInHandPosition3.setImageResource(R.drawable.card_tense);
                        break;
                    case 4:
                        cardInHandPosition4.setImageResource(R.drawable.card_tense);
                        break;
                }
            }
        }

        cardInHandPosition1.setVisibility(View.VISIBLE);
        cardInHandPosition2.setVisibility(View.VISIBLE);
        cardInHandPosition3.setVisibility(View.VISIBLE);
        cardInHandPosition4.setVisibility(View.VISIBLE);

    }

    public void card1clicked(View v){
        cardClicked(1);
        cardInHandPosition1.setVisibility(View.INVISIBLE);
    }
    public void card2clicked(View v){
        cardClicked(2);
        cardInHandPosition2.setVisibility(View.INVISIBLE);
    }
    public void card3clicked(View v){
        cardClicked(3);
        cardInHandPosition3.setVisibility(View.INVISIBLE);
    }
    public void card4clicked(View v){
        cardClicked(4);
        cardInHandPosition4.setVisibility(View.INVISIBLE);
    }




    public void cardClicked(int card) {

        playerCardAction(card);
        isPlayersTurn = false;
        enemyCardAction(getRandomCard().name);


    }


    private void enemyCardAction(String cardName){
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

    private void playerCardAction(int card) {
        if (enemy.health >= 0 && player.health >= 0 && isPlayersTurn) {
            System.out.println("This is the player health" + player.health);
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
            System.out.println("This is the Enemy health" + player.health);
            enemyHealth.setScaleX((float) enemy.health / 100);
        }
        if (enemy.health <=0){
            enemyHealth.setVisibility(View.INVISIBLE);
        }
    }

}
