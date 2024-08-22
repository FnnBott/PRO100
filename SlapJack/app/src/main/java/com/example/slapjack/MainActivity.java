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
    ProgressBar enemyHealth;
    Button giveCardsButton;
    Jack player = new Jack("Main Character", 1, 1 , 1);
    Jack enemy = new Jack("Glass Jack", 50, 1 , 1);
    Card slap,doubleSlap,tense;
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

        enemyName = findViewById(R.id.enemyName);

        slap = new Card(1,"Slap",0,1);
        doubleSlap = new Card(2,"Double Slap",0,3);
        tense = new Card(1,"Defend",1,0);

        enemyName.setText(enemy.name);

    }

    public void giveCards(View v){
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
                    break;
                case 2:
                    hand.addToHand(doubleSlap);
                    switch (i){
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
                    break;
                case 3:
                    hand.addToHand(tense);
                    switch (i){
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
                    break;
            }
        }
        FadeInAnimationCalls(giveCardsButton);
    }
    public void card1clicked(View v){
        cardClicked(1);
    }
    public void card2clicked(View v){
        cardClicked(2);
    }
    public void card3clicked(View v){
        cardClicked(3);
    }
    public void card4clicked(View v){
        cardClicked(4);
    }



    public void cardClicked(int card) {
        FadeOutAnimationCalls(card);
        //playerCardAction(card);
    }

    private void enemyCardAction(String name){
        if(!isPlayersTurn){

        }
    }

    private void playerCardAction(int card) {
        if (enemy.health >= 0) {
            System.out.println(enemy.health);
            if (hand.getCardName(card).equals("Slap")) {
                enemy.health -= slap.attack + player.power;
            } else if (hand.getCardName(card).equals("Double Slap")) {
                enemy.health -= doubleSlap.attack + player.power;
            } else if (hand.getCardName(card).equals("Defend")) {
                player.guard += 2;
            }
            System.out.println(enemy.health);
            enemyHealth.setScaleX((float) enemy.health / 100);
        }
    }
    private void FadeOutAnimationCalls(int cardToAnimate){
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new LinearInterpolator());
        fadeOut.setDuration(500);
        fadeOut.setFillAfter(true);
        try {
            switch (cardToAnimate) {
                case 1:
                    cardInHandPosition1.setOnClickListener(cardUseAnim -> cardInHandPosition1.startAnimation(fadeOut));
                    break;
                case 2:
                    cardInHandPosition2.setOnClickListener(cardUseAnim -> cardInHandPosition2.startAnimation(fadeOut));
                    break;
                case 3:
                    cardInHandPosition3.setOnClickListener(cardUseAnim -> cardInHandPosition3.startAnimation(fadeOut));
                    break;
                case 4:
                    cardInHandPosition4.setOnClickListener(cardUseAnim -> cardInHandPosition4.startAnimation(fadeOut));
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void FadeInAnimationCalls(Button button){
        ImageButton[] btnList = {cardInHandPosition1, cardInHandPosition2, cardInHandPosition3, cardInHandPosition4};
        try{
            for (ImageButton btn : btnList) {
                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setDuration(100);
                fadeIn.setInterpolator(new LinearInterpolator());

                btn.startAnimation(fadeIn);
                btn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn.setVisibility(View.VISIBLE);
                    }
                }, 100);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}