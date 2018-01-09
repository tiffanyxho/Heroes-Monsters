public class Weapon{
    private String type;
    private int minDamage, maxDamage;
    
    public Weapon(String type, int min, int max){
        this.type = type;
        minDamage = min;
        maxDamage = max;
    }
    
    public String getType(){
        return type;
    }
    
    public int getMinDmg(){
        return minDamage;
    }
    
    public String toString(){
        return "You have a " + type + "that does " + minDamage + " - " + maxDamage + " damage.";
    }
}