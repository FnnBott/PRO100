package com.example.slapjack;

public class Jack {
    String name;
    int health;
    int power;
    int guard;

    public Jack(String name, int health, int power, int guard) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.guard = guard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getGuard() {
        return guard;
    }

    public void setGuard(int guard) {
        this.guard = guard;
    }
}
