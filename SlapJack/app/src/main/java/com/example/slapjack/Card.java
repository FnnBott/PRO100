package com.example.slapjack;

public class Card {
    int cost;
    String name;
    int attack;
    int defense;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }
    public int getAttack() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Card(int cost, String name, int defense, int attack) {
        this.cost = cost;
        this.name = name;
        this.defense = defense;
        this.attack = attack;
    }

}

