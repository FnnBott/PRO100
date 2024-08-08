package com.example.slapjack;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Hand hand = new Hand();
    ImageButton card1,card2,card3,card4;
    ProgressBar enemyHealth;
    Button giveCardsButton;
    Jack player = new Jack("Main Character", 1, 1 , 1);
    Jack enemy = new Jack("Glass", 50, 1 , 1);
    Card slap,doubleSlap,defense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        onInit();
    }

    private void onInit() {
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);

        giveCardsButton = findViewById(R.id.giveCardsButton);
        enemyHealth = findViewById(R.id.enemyHealth);
        enemyHealth.setScaleX((float) enemy.health /100);

        slap = new Card(1,"Slap",0,1);
        doubleSlap = new Card(2,"Double Slap",0,3);
        defense = new Card(1,"Defend",1,0);

    }

    public void giveCards(View v){
        card1.setVisibility(View.VISIBLE);
        card2.setVisibility(View.VISIBLE);
        card3.setVisibility(View.VISIBLE);
        card4.setVisibility(View.VISIBLE);
        Random rand = new Random();
        hand.clear();
        for (int i = 1; i <= 4; i++)
        {
            int card = rand.nextInt(3);
            card++;
            switch (card){
                case 1:
                    hand.addToHand(slap);
                    switch (i){
                        case 1:
                            card1.setImageResource(R.drawable.splapexample);
                            break;
                        case 2:
                            card2.setImageResource(R.drawable.splapexample);
                            break;
                        case 3:
                            card3.setImageResource(R.drawable.splapexample);
                            break;
                        case 4:
                            card4.setImageResource(R.drawable.splapexample);
                            break;
                    }
                    break;
                case 2:
                    hand.addToHand(doubleSlap);
                    switch (i){
                        case 1:
                            card1.setImageResource(R.drawable.doubleslapexample);
                            break;
                        case 2:
                            card2.setImageResource(R.drawable.doubleslapexample);
                            break;
                        case 3:
                            card3.setImageResource(R.drawable.doubleslapexample);
                            break;
                        case 4:
                            card4.setImageResource(R.drawable.doubleslapexample);
                            break;
                    }
                    break;
                case 3:
                    hand.addToHand(defense);
                    switch (i){
                        case 1:
                            card1.setImageResource(R.drawable.blockexample);
                            break;
                        case 2:
                            card2.setImageResource(R.drawable.blockexample);
                            break;
                        case 3:
                            card3.setImageResource(R.drawable.blockexample);
                            break;
                        case 4:
                            card4.setImageResource(R.drawable.blockexample);
                            break;
                    }
                    break;
            }

        }
    }
    public void card1clicked(View v){
        cardClicked(1);
    }
    public void card2clicked(View v){
        cardClicked(2);
    }public void card3clicked(View v){
        cardClicked(3);
    }public void card4clicked(View v){
        cardClicked(4);
    }



    public void cardClicked(int card){
        switch (card){
            case 1:
                card1.setVisibility(View.INVISIBLE);
                cardAction(card);
                break;
            case 2:
                card2.setVisibility(View.INVISIBLE);
                cardAction(card);
                break;
            case 3:
                card3.setVisibility(View.INVISIBLE);
                cardAction(card);
                break;
            case 4:
                card4.setVisibility(View.INVISIBLE);
                cardAction(card);
                break;
        }
    }

    private void cardAction( int card) {
        if (enemy.health >= 0) {
            System.out.println(enemy.health);
            if (hand.getCardName(card).equals("Slap")) {
                enemy.health -= slap.attack + player.power;
            } else if (hand.getCardName(card).equals("Double Slap")) {
                enemy.health -= doubleSlap.attack + player.power;
            } else if (hand.getCardName(card).equals("Defend")) {
            }
            System.out.println(enemy.health);
            enemyHealth.setScaleX((float) enemy.health / 100);
        }
    }
}