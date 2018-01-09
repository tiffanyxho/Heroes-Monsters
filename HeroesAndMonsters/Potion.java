public class Potion{
    private int xPos, yPos;
    
    public Potion(){
        xPos = (int) (Math.random() * 10);
        yPos = (int) (Math.random() * 10);
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public String toString(){
        return "The potion is located at (" + xPos + ", " + yPos + ").";
    }
}