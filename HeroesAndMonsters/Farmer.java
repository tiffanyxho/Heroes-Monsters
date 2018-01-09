public class Farmer{
    private int xPos, yPos;
    private Weapon farmWeapon;
    
    public Farmer(Weapon weapon){
        xPos = (int) (Math.random() * 10);
        yPos = (int) (Math.random() * 10);
        farmWeapon = weapon;
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public String toString(){
        return "The farmer is located at (" + xPos + ", " + yPos + ") and has a " + farmWeapon.getType();
    }
}