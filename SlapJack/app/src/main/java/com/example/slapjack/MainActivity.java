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
    ImageButton cardInHandPosition1, cardInHandPosition2, cardInHandPosition3, cardInHandPosition4;
    ProgressBar enemyHealth,playerHealth;
    Button giveCardsButton;
    Jack player = new Jack("Main Character", 100, 1 , 1);
    Jack enemy = new Jack("Glass Jack", 50, 1 , 1);
    TextView enemyName,battleDescription;
    Integer energy = 3;

    BattleHandler battleHandler = new BattleHandler(enemy,player,enemyHealth,playerHealth, battleDescription);


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

        battleDescription = findViewById(R.id.battleDescription);
        enemyName.setText(enemy.name);



        battleHandler = new BattleHandler(enemy,player,enemyHealth,playerHealth, battleDescription);
    }



    public void giveCards(View v){
        battleHandler.enemyCardAction(battleHandler.getRandomCard().name);
        energy = 3;
        if (player.health > 0 && enemy.health > 0) {
            battleHandler.hand.clear();
            for (int i = 1; i <= 4; i++) {
                Card tempCard = battleHandler.getRandomCard();
                battleHandler.hand.addToHand(tempCard);
                if (tempCard.name.equals("Slap")) {
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
                if (tempCard.name.equals("Double Slap")) {
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
                if (tempCard.name.equals("Tense")) {
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
    }

    public void card1clicked(View v){
        if( energy - battleHandler.hand.cards.get(0).cost >= 0) {
            if (player.health > 0 && enemy.health > 0 && energy > 0) {
                battleHandler.cardClicked(1);
                cardInHandPosition1.setVisibility(View.INVISIBLE);
            }
            energy -= battleHandler.hand.cards.get(0).cost;

        }
        System.out.println(energy);

    }
    public void card2clicked(View v){
        if( energy - battleHandler.hand.cards.get(1).cost >= 0) {
            if (player.health > 0 && enemy.health > 0 && energy > 0 ) {
                battleHandler.cardClicked(2);
                cardInHandPosition2.setVisibility(View.INVISIBLE);
            }
            energy -= battleHandler.hand.cards.get(1).cost;

        }
        System.out.println(energy);

    }
    public void card3clicked(View v){
        if( energy - battleHandler.hand.cards.get(2).cost >= 0) {
            if (player.health > 0 && enemy.health > 0 && energy > 0 ) {
                battleHandler.cardClicked(3);
                cardInHandPosition3.setVisibility(View.INVISIBLE);
            }
            energy -= battleHandler.hand.cards.get(2).cost;

        }
        System.out.println(energy);

    }
    public void card4clicked(View v){
        if( energy - battleHandler.hand.cards.get(3).cost >= 0) {
            if (player.health > 0 && enemy.health > 0 && energy  > 0 ) {
                battleHandler.cardClicked(4);
                cardInHandPosition4.setVisibility(View.INVISIBLE);
            }
            energy -= battleHandler.hand.cards.get(3).cost;

        }
        System.out.println(energy);

    }

}
