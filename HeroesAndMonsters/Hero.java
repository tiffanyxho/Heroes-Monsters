import java.util.*;

public class Hero{
    private int xPos, yPos;
    private int health;
    private Weapon weap;
    private boolean hasArmor;
    
    public Hero(){
        xPos = 0;
        yPos = 9;
        health = 100;
        weap = new Weapon("dagger", 10, 30);
        hasArmor = false;
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public int getHealth(){
        return health;
    }
    
    public boolean getArmor(){
        return hasArmor;
    }
    
    public int attack(){
        int minDmg = weap.getMinDmg();
        int dmg = (int) (Math.random() * 11) + minDmg;
        return dmg;
    }
    
    public void setWeap(String type, int min, int max){
        weap = new Weapon(type, min, max);
    }
    
    public void setArmor(boolean armor){
        hasArmor = armor;
    }
    
    public boolean checkOverlap(Object[][] map, String choice){
        int newX = getX(), newY = getY();
        
        if (choice.equals("w") && newY != 0){
            newY -= 1;
        }
        else if (choice.equals("a") && newX != 0){
            newX -= 1;
        }
        else if (choice.equals("s") && newY != 9){
            newY += 1;
        }
        else if (choice.equals("d") && newX != 9){
            newX += 1;
        }        
        if (map[newX][newY] != null){
            return true;
        }
        
        return false;
    }
    
    public boolean engageMonster(Monster monster, Object[][] map, Hero hero){
        int speed = monster.getSpeed();
        double randomRun = 0;
        double chance = Math.random();
        boolean canRun = false, run = false;
        String engage = "";
        String runStr = "";
        Scanner reader = new Scanner(System.in);

        System.out.println("MONSTER!!! >:)");
        
        while (getHealth() > 0 && monster.getHealth() > 0 && ((!canRun && run) || (!canRun && !run))){
            while (!engage.equals("Y") && !engage.equals("N")){
                System.out.println("Enter Y to fight the monster or N to attempt to flee.");
                engage = reader.next();
            }
            
            if (engage.equals("Y")){
                run = false;
                engage = "";
            }
            else if (engage.equals("N")){
                run = true;
                engage = "";
            }
            
            randomRun = Math.random();
            
            if (speed == 0){
                if (randomRun >= 0.25){
                    canRun = true;
                }else{
                    canRun = false;
                }
            }else if (speed == 1){
                if (randomRun >= 0.5){
                    canRun = true;
                }else{
                    canRun = false;
                }
            }else if (speed == 2){
                if (randomRun >= 0.75){
                    canRun = true;
                }else{
                    canRun = false;
                }
            }else if (speed == 3){
                canRun = false;
            }
            
            if (canRun && run){
                System.out.println("Where would you like to run to?  Enter w, a, d, or s to run.  (Run away from the monster!!)");
                Scanner dirRead = new Scanner(System.in);
                runStr = dirRead.next();
                while (!runStr.equals("w") && !runStr.equals("a") && !runStr.equals("s") && !runStr.equals("d")){
                    System.out.println("Only enter w, a, s, or d!");
                    runStr = dirRead.next();
                }
                
                boolean awayFromMonster = false;
                while (!awayFromMonster){
                    if (runStr.equals("w")){
                        if (map[getX()][getY()-1] == monster){
                            System.out.println("Run away from the monster!!");
                            runStr = dirRead.next();
                        }else{
                            move(runStr, map, hero);
                            System.out.println("You have successfully run away from the monster.");
                            awayFromMonster = true;
                            return false;
                        }
                    }
                    else if (runStr.equals("a")){
                        if (map[getX()-1][getY()] == monster){
                            System.out.println("Run away from the monster!!");
                            runStr = dirRead.next();
                        }else{
                            move(runStr, map, hero);
                            System.out.println("You have successfully run away from the monster.");
                            awayFromMonster = true;
                            return false;
                        }
                    }
                    else if (runStr.equals("d")){
                        if (map[getX()+1][getY()] == monster){
                            System.out.println("Run away from the monster!!");
                            runStr = dirRead.next();
                        }else{
                            move(runStr, map, hero);
                            System.out.println("You have successfully run away from the monster.");
                            awayFromMonster = true;
                            return false;
                        }
                    }
                    else if (runStr.equals("s")){
                        if (map[getX()][getY()+1] == monster){
                            System.out.println("Run away from the monster!!");
                            runStr = dirRead.next();
                        }else{
                            move(runStr, map, hero);
                            System.out.println("You have successfully run away from the monster.");
                            awayFromMonster = true;
                            return false;
                        }
                    }
                }
            }
            else if (canRun == false && run){
                System.out.println("You cannot run right now!  You must fight.");
                monster.setHealth(attack());
                if (getArmor() == false){
                    health -= monster.getAttack();
                    System.out.println(toString());
                }
                else if (getArmor()){
                    health -= monster.getAttack() / 3;
                    System.out.println(toString());
                }
                if (monster.getHealth() > 0){
                    System.out.println(monster);
                }
            }
            else if (run == false){
                System.out.println("You have chosen to fight the monster.");
                monster.setHealth(attack());
                
                if (getArmor() == false){
                    health -= monster.getAttack();
                    System.out.println("Hero's health is " + getHealth());
                }
                else if (getArmor()){
                    health -= monster.getAttack() / 3;
                    System.out.println("Hero's health is " + getHealth());
                }
                
                if (monster.getHealth() > 0){
                    System.out.println(monster);
                }
            }
            if (monster.getHealth() <= 0){
                System.out.println("You have successfully defeated the monster!");
                System.out.println("Hero's health is " + getHealth());
                map[monster.getX()][monster.getY()] = null;
                move(runStr, map, hero);
                
                return false;
            }
            if (getHealth() <= 0){
                System.out.println("Hero has died... Game over :(");
                
                return false;
            }
        }
        
        
        return false;
    }
    
    public void heal(){
        health = 100;
    }
    
    public void setX(int x){
        xPos = x;
    }
    
    public void setY(int y){
        yPos = y;
    }
    
    public void move(String direction, Object map[][], Hero hero){
        if (direction.toLowerCase().equals("d")){
            if (xPos == 9){
                System.out.println("You cannot move right anymore.  You are at the edge of the map.  Try moving a different direction.");
            }
            else{
                map[getX()][getY()] = null;                
                xPos += 1;
                map[getX()][getY()] = hero;
            }
        }
        else if (direction.toLowerCase().equals("a")){
            if (xPos == 0){
                System.out.println("You cannot move left anymore.  You are at the edge of the map.  Try moving a different direction.");
            }
            else{
                map[getX()][getY()] = null;
                xPos -= 1;
                map[getX()][getY()] = hero;
            }
        }
        else if (direction.toLowerCase().equals("w")){
            if (yPos == 0){
                System.out.println("You cannot move up anymore.  You are at the edge of the map.  Try moving a different direction.");
            }
            else{
                map[getX()][getY()] = null;
                yPos -= 1;
                map[getX()][getY()] = hero;
            }
        }
        else if (direction.toLowerCase().equals("s")){
            if (yPos == 9){
                System.out.println("You cannot move down anymore.  You are at the edge of the map.  Try moving a different direction.");
            }
            else{
                map[getX()][getY()] = null;
                yPos += 1;
                map[getX()][getY()] = hero;
            }
        }
    }

    public String toString(){
        return "Hero position: " + xPos + ", " + yPos + "\nHero health: " + health;
    }
}
