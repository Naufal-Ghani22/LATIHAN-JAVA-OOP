package com.latihan;

// PlAYEER
class Player{
    String name;
    double health;
    int level;

    Weapon weapon;
    Armor armor;

    public Player(String name, double health) {
        this.name = name;
        this.health = health;
    }

    void attack(Player opponent){
        System.out.println(this.name + "attacking" + opponent.name);
    }

    void defence(double attackPower){
        System.out.println(this.name + " got's damage " + attackPower);
    }

    void equipWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    void equipArmor(Armor armor){
        this.armor = armor;
    }
    
    void display(){
        System.out.println("Name    : " + this.name);
        System.out.println("Health  : " + this.health +" HP");
        this.weapon.display();
        this.armor.display();
    }
}


// SENJATA
class Weapon{
    double attackPower;
    String name;

    Weapon(String name, double attackPower){
        this.name = name;
        this.attackPower = attackPower;
    }

    void display(){
        System.out.println("Weapon  : " + this.name + ", power : " + this.attackPower);
    }
}

// ARMOR
class Armor{
    String name;
    double defencePower;

    Armor(String name, double defencePower) {
        this.name = name;
        this.defencePower = defencePower;
    }

    void display(){
        System.out.println("Armor   : " + this.name + ", defence : " + this.defencePower);
    }
}


public class App {
    public static void main(String[] args) {
        
        // PLAYER
        Player player1 = new Player("Ucup", 100);
        Player player2 = new Player("Budi", 200);

        // WEAPON
        Weapon pedang = new Weapon("pedang", 25);
        Weapon ketapel = new Weapon("ketapel", 90);
        
        // DEFENCE
        Armor bajuBesi = new Armor("baju besi",30);
        Armor antiPeluru = new Armor("Anti Peluru",50);
        
        // EQUIP SENJATA
        player1.equipWeapon(pedang);
        player1.equipArmor(bajuBesi);
        player1.display();
        
        player2.equipWeapon(ketapel);
        player2.equipArmor(antiPeluru);
        player2.display();

        System.out.println("\nPERTEMPURAN");
        player1.attack(player2);
    }
}
