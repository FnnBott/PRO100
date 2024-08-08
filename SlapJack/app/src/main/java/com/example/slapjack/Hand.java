package com.example.slapjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card>  cards = new ArrayList<Card>();

    public void addToHand(Card card)
    {
        cards.add(card);
    }
    public String getCardName(int index){
        String name = "";
        name = cards.get(index-1).name;
        return name;
    }
    public void clear(){
        cards.clear();
    }
}
