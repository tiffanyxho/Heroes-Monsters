import java.io.*;
import java.util.*;

public class Driver{
    public static void main(String args[]){
        int numPotion = 0, numMonster = 0, numFarmer = 0, monsterKill = 0;;
        String directChoice = "";

        // create objects
        Hero hiro = new Hero();
        
        // create map 10 rows by 10 columns
        Object[][] map = new Object[10][10];
        
        // fill map with objects
        map[hiro.getX()][hiro.getY()] = hiro;
        
        while(numPotion < 2){
            Potion potion = new Potion();
            if (map[potion.getX()][potion.getY()] == null){
                map[potion.getX()][potion.getY()] = potion;
                numPotion += 1;
            }
        }
        
        while (numMonster < 6){
            Monster monster = new Monster();
            if (map[monster.getX()][monster.getY()] == null){
                map[monster.getX()][monster.getY()] = monster;
                numMonster += 1;
                System.out.println(monster.getClass());
            }
        }
        
        while (numFarmer < 2){
            Farmer farmer = new Farmer(new Weapon("", 10, 15));
            if (map[farmer.getX()][farmer.getY()] == null){
                map[farmer.getX()][farmer.getY()] = farmer;
                numFarmer += 1;
            }
        }
        
        while(hiro.getHealth() > 0 && monsterKill < 6){
            int farmerRun = 0;
            Scanner kbReader = new Scanner(System.in);
            System.out.println("Enter w, a, s, or d to move. (Case sensitive!)");
            directChoice = kbReader.next();
            
            if (directChoice.equals("x")){
                break;
            }
            
            if (hiro.checkOverlap(map, directChoice) == false){
                if (directChoice.equals("w") || directChoice.equals("s") || directChoice.equals("a") || directChoice.equals("d")){
                    hiro.move(directChoice, map, hiro);
                }
                else{
                    System.out.println("Only enter w, a, s, or d! Case sensitive!");
                }
                
                printMap(map);
            }
            else if (hiro.checkOverlap(map, directChoice)){
                int newX = hiro.getX(), newY = hiro.getY();
                
                if (directChoice.equals("w") && newY != 0){
                    newY -= 1;
                }
                else if (directChoice.equals("a") && newX != 0){
                    newX -= 1;
                }
                else if (directChoice.equals("s") && newY != 9){
                    newY += 1;
                }
                else if (directChoice.equals("d") && newX != 9){
                    newX += 1;
                }
                
                if (map[newX][newY] != null){
                    if (map[newX][newY].getClass().equals(Monster.class)){
                        boolean engage = true;
                        while (engage == true){
                            Monster monst = (Monster) map[newX][newY];
                            engage = hiro.engageMonster(monst, map, hiro);
                            if (monst.getHealth() < 1){
                                monsterKill += 1;
                            }
                        }
                        engage = true;
                    }
                    else if (map[newX][newY].getClass().equals(Farmer.class)){
                        System.out.println(" FARMER! ");
                        farmerRun += 1;
                        System.out.println(farmerRun);
                        if (farmerRun == 2){
                            System.out.println("The monsters have ruined me!  If you want my broad sword, you must avenge me, and slay 4 monsters.");                            
                        }
                        else if(farmerRun == 1){
                            System.out.println("My sheep have been taken… My family is gone and I have nothing left except for bronze armor I have buried… Only a true hero will receive this.");
                        }
                    }
                    else if (map[newX][newY].getClass().equals(Potion.class)){
                        System.out.println("POTION! \nHero's health is now 100.");
                        hiro.heal();
                        hiro.move(directChoice, map, hiro);
                    }
                }
                if (farmerRun >= 1 && monsterKill >= 2){
                    hiro.setArmor(true);
                }
                if(farmerRun >= 2 && monsterKill >= 4){
                    hiro.setWeap("broad sword", 20, 50);
                }
            }
            if (monsterKill == 6){
                System.out.println("You have successfully slain all the monsters!  You discovered that you actually have family in other parts of the world and have decided to go on another journey to find them.");
            }
        }        
    }
    
    public static void printMap(Object[][] map){
        for (int row = 0; row < map.length; row++){
            for (int col = 0; col <  map[row].length; col++){
                if (map[col][row] == null){
                    System.out.print("0\t");
                }else if (map[col][row].getClass().equals(Potion.class)){
                    System.out.print("Potion\t");
                }else if (map[col][row].getClass().equals(Monster.class)){
                    System.out.print("Monster\t");
                }else if (map[col][row].getClass().equals(Farmer.class)){
                    System.out.print("Farmer\t");
                }
                else if (map[col][row].getClass().equals(Hero.class)){
                    System.out.print("Hero\t");
                }
            }
            System.out.println("\n");
        }
    }
    
    
    public static void StateOfMap(Object[][] obj){
        for (int row = 0; row < obj.length; row++){
            for (int col = 0; col < obj[row].length; col++){
                if (obj[row][col] != null){
                    System.out.println(obj[row][col]);
                }
            }
        }
        System.out.println();
    }
}