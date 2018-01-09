import java.util.*;

public class Monster{
    private int attack, health, speed;
    private int xPos, yPos;
    
    public Monster(){
        attack = (int) (Math.random() * 29) + 1;
        health = (int) (Math.random() * 100) + 1;
        speed = (int) (Math.random() * 4);
        xPos = (int) (Math.random() * 10);
        yPos = (int) (Math.random() * 10);
    }
    
    public void setHealth(int decHealth){
        health -= decHealth;
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getAttack(){
        return attack;
    }
    
    public String toString(){
        return "There is a monster at " + "(" + xPos + ", " + yPos + ") that has " + health + " health, " + attack + " attack, and " + speed + " speed.";
    }
}