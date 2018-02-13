import java.util.ArrayList;
import java.util.*;
import java.io.*;
public class Tester{
    public static void main(String args[]){
        EliteMonster[] boss = new EliteMonster[3];
        Farmer[] farmer = new Farmer[4];
        Potion[] potion = new Potion[3];
        Weapon weapon = new Weapon();
        Hero hero = new Hero();
        System.out.println("** Hero & Monsters **");
        Object[][] map = new Object[15][15];
        fillMap(map);
        System.out.println("* The map has been generated *");
        updateHero(map,hero);
        System.out.println("* The enemies have been placed *");
        System.out.println("* The items have been placed *");
        System.out.println("* Remember you can only win if you defeat the BOSS *");
        String x = "";
        String y = "";
        int xcoord = (int)(Math.random() * 10);
        int ycoord = (int)(Math.random() * 10);
        int a = 1;
        int b = 0;
        int bossNumber = 0;
        int f = 0;
        int battleLoop = 1;
        int monsterKilled = 0;
        int potionNum = 3;
        int p = 0;
        int potionCarried = 0;
        int potionCondition = 0;
        int halfEnergyDrink = 0;
        int fullEnergyDrink = 0;
        int shop = 0;
        int hides = 0;
        int getHides = 20;
        int bossLoop = 0;
        int traderLoop = 0;
        int bossBattleLoop = 1;
        int bossKilled = 0;
        int weaponSwitchLoop = 0;
        int bomb = 0;
        int phenoix = 0;
        int i = 1;
        int inventorySpace = 0;
        int sandalNum = 1;
        int satchelNum = 1;
        int activate = 0;
        int no_monster = 0;
        if(xcoord > 5){
            x = "east";
        }else if(xcoord < 5){
            x = "west";
        }
        
        if(ycoord > 5){
            y = "North";
        }else if(ycoord < 5){
            y = "South";
        }
        
        if(xcoord == 5 && ycoord == 5){
            System.out.println("Hero begins in the middle of the Yore. ");
        }else{
            System.out.println("Hero begins his journey in the " + y + x + " corner of Yore. ");
        }
        
        while(bossNumber < boss.length){
            boss[i-1] = new EliteMonster(i);
            if(map[boss[i-1].getX()][boss[i-1].getY()] == "[  ? ?  ]"){
                map[boss[i-1].getX()][boss[i-1].getY()] = "[ BOSS "+i+" ]";
                bossLoop = 1;
                i++;
                bossNumber++;
            }
        }
        bossNumber = 0;
        i = 1;
        
        Trader trader = new Trader();
        if(map[trader.getX()][trader.getY()] == "[  ? ?  ]"){
            map[trader.getX()][trader.getY()] = "[ Shop  ]";
            traderLoop = 1;
        }
        
        while(p < potion.length){
            potion[p] = new Potion();
            if(map[potion[p].getX()][potion[p].getY()] == "[  ? ?  ]"){
                map[potion[p].getX()][potion[p].getY()] = "[Potion ]";
                p++;
            }
        }
        
        while(f < farmer.length){
            farmer[f] = new Farmer(f);
            if(map[farmer[f].getX()][farmer[f].getY()] == "[  ? ?  ]"){
                map[farmer[f].getX()][farmer[f].getY()] = "[Farmer ]";
                f++;
            }
        }
        
        //* turn this on/off to toggle the "wallhack" *
        fillMap(map);
        map[hero.getX()][hero.getY()] = "[ Hero  ]";
        map[trader.getX()][trader.getY()] = "[ Shop  ]";
        
        printMap(map);
        
        while(a == 1){
            shop = 0;
            potionCondition = 0;
            battleLoop = 1;
            bossBattleLoop = 1;
            Scanner kbReader = new Scanner(System.in);
            System.out.println("\n\nEnter direction (north, south, east, west): ");
            System.out.println("Enter recover or r to use potion");
            String direction = kbReader.nextLine();
            if(direction.equals("mason")){
                hero.setHealth(200);
            }
            if(direction.equals("money")){
                hides+=500;
            }
            if(direction.equals("no")){
                no_monster++;
            }
            if(direction.equals("north") || direction.equals("w") && hero.getY() <= 14){
                if(map[hero.getX()][hero.getY()] == "[  ? ?  ]"||map[hero.getX()][hero.getY()] == "[ Hero  ]"){                    
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("n");
                updateHero(map, hero);
                checkMonsterMethod();
                hero.setDirection("North");
                for(int z = 0; z < 4; z++){
                    if(farsightFarmer(farmer[z], hero, map)){
                        map[farmer[z].getX()][farmer[z].getY()] = farmer[z].getName();
                    }
                }
                printMap(map);
                System.out.println("\n\nHero now has " + hero.getHealth() + "/100 health left");
                System.out.println("Hero has defeated " + monsterKilled + " monster(s)");
                System.out.println("Weapon: " + hero.getWeapon() + " \nDamage: " + hero.getDamage());
                System.out.println("Armor: " + hero.getArmor());
                System.out.println("Hero has " + potionCarried + " potion(s) in inventory");
                System.out.println("Hero has " + hides + " hide(s)");
                System.out.println("Hero level: " + hero.getLevel());
                System.out.println("Hero's inventory: " + hero.getInventory());
            }else if(direction.equals("south") || direction.equals("s") && hero.getY() >= 0){
                if(map[hero.getX()][hero.getY()] == "[  ? ?  ]"||map[hero.getX()][hero.getY()] == "[ Hero  ]"){                    
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("s");
                updateHero(map, hero);
                checkMonsterMethod();
                hero.setDirection("South");
                for(int z = 0; z < 4; z++){
                    if(farsightFarmer(farmer[z], hero, map)){
                        map[farmer[z].getX()][farmer[z].getY()] = farmer[z].getName();
                    }
                }
                printMap(map);
                System.out.println("\n\nHero now has " + hero.getHealth() + "/100 health left");
                System.out.println("Hero has defeated " + monsterKilled + " monster(s)");
                System.out.println("Weapon: " + hero.getWeapon() + " \nDamage: " + hero.getDamage());
                System.out.println("Armor: " + hero.getArmor());
                System.out.println("Hero has " + potionCarried + " potion(s) in inventory");
                System.out.println("Hero has " + hides + " hide(s)");
                System.out.println("Hero level: " + hero.getLevel());
                System.out.println("Hero's inventory: " + hero.getInventory());
            }else if(direction.equals("west") || direction.equals("a") && hero.getX() >= 0){
                if(map[hero.getX()][hero.getY()] == "[  ? ?  ]"||map[hero.getX()][hero.getY()] == "[ Hero  ]"){                    
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("w");
                updateHero(map, hero);
                checkMonsterMethod();
                hero.setDirection("West");
                for(int z = 0; z < 4; z++){
                    if(farsightFarmer(farmer[z], hero, map)){
                        map[farmer[z].getX()][farmer[z].getY()] = farmer[z].getName();
                    }
                }
                printMap(map);
                System.out.println("\n\nHero now has " + hero.getHealth() + "/100 health left");
                System.out.println("Hero has defeated " + monsterKilled + " monster(s)");
                System.out.println("Weapon: " + hero.getWeapon() + " \nDamage: " + hero.getDamage());
                System.out.println("Armor: " + hero.getArmor());
                System.out.println("Hero has " + potionCarried + " potion(s) in inventory");
                System.out.println("Hero has " + hides + " hide(s)");
                System.out.println("Hero level: " + hero.getLevel());
                System.out.println("Hero's inventory: " + hero.getInventory());
            }else if(direction.equals("east") || direction.equals("d") && hero.getX() <= 14){
                if(map[hero.getX()][hero.getY()] == "[  ? ?  ]"||map[hero.getX()][hero.getY()] == "[ Hero  ]"){                    
                    map[hero.getX()][hero.getY()] = "[       ]";
                }
                if(hero.getY() > 14 || hero.getY() < 0 || hero.getX() > 14 || hero.getX() < 0){
                    System.out.println("Hero is on the edge of the map, please go back!");
                }
                hero.heroMove("e");
                updateHero(map, hero);
                checkMonsterMethod();
                hero.setDirection("East");
                for(int z = 0; z < 4; z++){
                    if(farsightFarmer(farmer[z], hero, map)){
                        map[farmer[z].getX()][farmer[z].getY()] = farmer[z].getName();
                    }
                }
                printMap(map);
                System.out.println("\n\nHero now has " + hero.getHealth() + "/100 health left");
                System.out.println("Hero has defeated " + monsterKilled + " monster(s)");
                System.out.println("Weapon: " + hero.getWeapon() + " \nDamage: " + hero.getDamage());
                System.out.println("Armor: " + hero.getArmor());
                System.out.println("Hero has " + potionCarried + " potion(s) in inventory");
                System.out.println("Hero has " + hides + " hide(s)");
                System.out.println("Hero level: " + hero.getLevel());
                System.out.println("Hero's inventory: " + hero.getInventory());
            }else if(direction.equals("recover") || direction.equals("Recover") || direction.equals("r") || direction.equals("R")){
                System.out.println("1. 1/2 Potion");
                System.out.println("2. 100% Potion");
                Scanner recoverDecisionW = new Scanner(System.in);
                System.out.println("Please enter a selecion");
                String recoverW = recoverDecisionW.nextLine();
                if(recoverW.equals("1")){
                    if(halfEnergyDrink > 0){
                        hero.setHealth(hero.getHealth() + 30);
                        halfEnergyDrink--;
                        inventorySpace--;
                        System.out.println("*Hero has gain 30 hp*");
                        printMap(map);
                    }else{
                        System.out.println("*Hero doesn't have any potion to drink*");
                    }
                }else if(recoverW.equals("2")){
                    if(fullEnergyDrink > 0){
                        hero.setHealth(hero.getHealth() + 100);
                        fullEnergyDrink--;
                        inventorySpace--;
                        System.out.println("*Hero has gain 100 hp*");
                        printMap(map);
                    }else{
                        System.out.println("*Hero doesn't have any potion to drink*");
                    }
                }else{
                    System.out.println("Please enter a valid command");
                }
            }else{
                System.out.println("Please enter a valid command!");
            }
            map[hero.getX()][hero.getY()] = "[ Hero  ]";
            
            while(shop == 0){
                if(checkTraderMethod(trader, hero) == true){
                    map[hero.getX()][hero.getY()] = "[ Shop  ]";
                    System.out.println("\n\n Hero has found a trader in town, he offers some supplies that can help hero!");
                    System.out.println("\n\n Trader: Hello, what would you like to buy?");
                    Scanner shopDecision = new Scanner(System.in);
                    System.out.println("0. Sword(needed to kill boss 2)");
                    System.out.println("Cost: 30 hides");
                    System.out.println("1. 1/2 Energy Drink (Energy drink can give hero a recovery of 30 hp, hero's health can be greater than 100)");
                    System.out.println("Cost: 10 hides");
                    System.out.println("2. 100% Energy Drink (Energy drink can give hero a recovery of 100 hp, hero's health can be greater than 100)");
                    System.out.println("Cost: 30 hides");
                    System.out.println("3. Battle Axe (A strong weapon that has a damage of 80)");
                    System.out.println("Cost: 60 hides");
                    System.out.println("4. EXCALIBUR (The legendary sword of King Arthur and Saber! Has a damage of 50)needed to kill boss 3");
                    System.out.println("Cost: 140 hides");
                    System.out.println("5. Bomb (Power item that can kill the monster instantly NO MATTER WHOW MUCH HEALTH IT HAS)");
                    System.out.println("Cost: 220 hides");
                    System.out.println("6. Sandals (Allow hero to attack twice as fast)");
                    System.out.println("Cost: 250 hides");
                    System.out.println("7. Phoenix down (The item that can revive the hero when hero dies)");
                    System.out.println("Cost: 300 hides");
                    System.out.println("8. Satchel (Allaw Hero the ability to carry 6 additional items);needed to kill boss 1");
                    System.out.println("Cost: 10 hides");
                    System.out.println("9. Quit");
                    String shop1 = shopDecision.nextLine();
                    if(shop1.equals("0")){
                        if(hero.getInventory().equals("Trousers")){
                            if(inventorySpace <= 3){
                                if(hides >= 30){
                                    hero.switchWeapon();
                                    hero.setWeapon();
                                    inventorySpace++;
                                    hides = hides - 30;
                                    System.out.println("Hero now has an Sword as his weapon!");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }else if(hero.getInventory().equals("Satchel")){
                            if(inventorySpace <= 7){
                                if(hides >= 30){
                                    hero.switchWeapon();
                                    hero.setWeapon();
                                    inventorySpace++;
                                    hides = hides - 30;
                                    System.out.println("Hero now has an Sword as his weapon!");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }
                    }
                    if(shop1.equals("1")){
                        if(hero.getInventory().equals("Trousers")){
                            if(inventorySpace <= 3){
                                if(hides >= 10){
                                    halfEnergyDrink++;
                                    inventorySpace++;
                                    hides = hides - 10;
                                    System.out.println("Item 1/2 Energy Drink has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }else if(hero.getInventory().equals("Satchel")){
                            if(inventorySpace <= 7){
                                if(hides >= 10){
                                    halfEnergyDrink++;
                                    inventorySpace++;
                                    hides = hides - 10;
                                    System.out.println("Item 1/2 Energy Drink has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }
                    }else if(shop1.equals("2")){
                        if(hero.getInventory().equals("Trousers")){
                            if(inventorySpace <= 3){
                                if(hides >= 30){
                                    fullEnergyDrink++;
                                    inventorySpace++;
                                    hides = hides - 30;
                                    System.out.println("Item 100& Energy Drink has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }else if(hero.getInventory().equals("Satchel")){
                            if(inventorySpace <= 7){
                                if(hides >= 10){
                                    fullEnergyDrink++;
                                    inventorySpace++;
                                    hides = hides - 30;
                                    System.out.println("Item 100% Energy Drink has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }
                    }else if(shop1.equals("3")){
                        if(hides >= 60){
                            hero.switchWeapon2();
                            hero.setWeapon2();
                            hides = hides - 60;
                            System.out.println("Hero now has an Axe as his weapon!");
                        }else{
                            System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                        }
                    }else if(shop1.equals("4")){
                        if(hides >= 140){
                            hero.switchWeapon1();
                            hero.setWeapon1();
                            hides = hides - 140;
                            System.out.println("Hero now has the EXCALIBUR sword as his weapon!");
                        }else{
                            System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                        }
                    }else if(shop1.equals("5")){
                        if(hero.getInventory().equals("Trousers")){
                            if(inventorySpace <= 3){
                                if(hides >= 220){
                                    hides = hides - 220;
                                    bomb++;
                                    inventorySpace++;
                                    System.out.println("Bomb item has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }else if(hero.getInventory().equals("Satchel")){
                            if(inventorySpace <= 7){
                                if(hides >= 220){
                                    hides = hides - 220;
                                    bomb++;
                                    inventorySpace++;
                                    System.out.println("Bomb item has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }
                    }else if(shop1.equals("6")){
                        if(hides >= 250){
                            if(sandalNum != 0){
                                hides = hides - 250;
                                sandalNum--;
                                hero.setSandals();
                                System.out.println("Sandal item has been added");
                                System.out.println("Hero can attack twice as fast");
                            }else{
                                System.out.println("Item Sandal can only be purchased once");
                            }
                        }else{
                            System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                        }
                    }else if(shop1.equals("7")){
                        if(hero.getInventory().equals("Trousers")){
                            if(inventorySpace <= 3){
                                if(hides >= 220){
                                    hides = hides - 220;
                                    phenoix++;
                                    inventorySpace++;
                                    System.out.println("Phenoix Down item has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }else if(hero.getInventory().equals("Satchel")){
                            if(inventorySpace <= 7){
                                if(hides >= 300){
                                    hides = hides - 300;
                                    phenoix++;
                                    inventorySpace++;
                                    System.out.println("Phenoix Down item has been added to inventory");
                                }else{
                                    System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                                }
                            }else{
                                System.out.println("*Hero doesn't have enough space for more items*");
                            }
                        }
                    }else if(shop1.equals("8")){
                        if(hides >= 10){
                            if(satchelNum != 0){
                                hides = hides - 10;
                                satchelNum--;
                                hero.setInventory("Satchel");
                                System.out.println("Hero can now carry 6 more items");
                            }else{
                                System.out.println("Item Sathcel can only be purchased once");
                            }
                        }else{
                            System.out.println("Hero doesn't have enough hides! Go kill some monsters!");
                        }
                    }else if(shop1.equals("9")){
                        shop = 1;
                        printMap(map);
                    }else{
                        System.out.println("Please enter a valid command");
                    }
                }
                shop = 1;
            }

            if(checkFarmerMethod(farmer, hero, map) == 0){
                System.out.println(farmer[0]);
                for(int q = 0; q < 4; q++){
                    map[farmer[q].getX()][farmer[q].getY()]= farmer[q].getName();
                }
            }else if(checkFarmerMethod(farmer, hero, map) == 1){
                map[hero.getX()][hero.getY()] = farmer[1].getName();
                System.out.println(farmer[1]);
                if(checkBoss(farmer[1],boss)){
                    System.out.println("Boss 1 is appearing at Map!");
                    map[boss[0].getX()][boss[0].getY()] = "[ BOSS 1]";
                    activate = 1;
                }else{
                    System.out.println("nice to meet you! Have a good Day!");
                }
            }else if(checkFarmerMethod(farmer, hero, map) == 2){
                map[hero.getX()][hero.getY()] = farmer[2].getName();
                System.out.println(farmer[2]);
                if(checkBoss(farmer[2],boss)){
                    System.out.println("Boss 2 is appearing at Map!");
                    map[boss[1].getX()][boss[1].getY()] = "[ BOSS 2]";
                    activate =2;
                }else{
                    System.out.println("nice to meet you! Have a good Day!");
                }
            }else if(checkFarmerMethod(farmer, hero, map) == 3){
                map[hero.getX()][hero.getY()] = farmer[3].getName();
                System.out.println(farmer[3]);
                if(checkBoss(farmer[3],boss)){
                    System.out.println("Boss 2 is appearing at Map!");
                    map[boss[2].getX()][boss[2].getY()] = "[ BOSS 3]";
                    activate = 3;
                }else{
                    System.out.println("nice to meet you! Have a good Day!");   
                }
            }

            if(checkBossMethod(boss, hero, activate)){
                map[hero.getX()][hero.getY()] = "[ BOSS "+i+"]";
                while(bossBattleLoop == 1){
                    System.out.println("\n\nMonster incoming! This time it is a Boss! BOSS PHASE " + i + "Ready to encounter");
                    printMap(map);
                    System.out.println("The BOSS's info has been updated: ");
                    System.out.println("Health: " + boss[i-1].getHealth());
                    System.out.println("Attack: " + boss[i-1].getBossAttack());
                    if(i ==2 &&hero.getWeapon().equals("Sword")){
                        Scanner bossBattle = new Scanner(System.in);
                        System.out.println("Enter an action (1.Run, 2.Attack, 3.Recover): ");
                        String bossDecision = bossBattle.nextLine();
                        if(bossDecision.equals("mason")){
                            hero.setHealth(200);
                        }
                        if(bossDecision.equals("money")){
                            hides+=500;
                        }
                        if(bossDecision.equals("Run") || bossDecision.equals("1")){
                            System.out.println("\n\nHero has successfully ran away!");
                            map[hero.getX()][hero.getY()] = "[ BOSS "+i+"]";
                            bossBattleLoop = 0;
                        }else if(bossDecision.equals("Attack") || bossDecision.equals("2")){
                            System.out.println("\n\nHero attacks, BOSS's energy goes down to " + (boss[i-1].getHealth() - hero.getDamage()));
                            System.out.println("\nBOSS attacks, hero's energy goes down to " + (hero.getHealth() - boss[i-1].getAttack(hero)) + "/100");
                            boss[i-1].setHealth(boss[i-1].getHealth() - hero.getDamage());
                            hero.setHealth(hero.getHealth() - boss[i-1].getAttack(hero));
                            if(boss[i-1].getHealth() <= 0){
                                bossBattleLoop = 0;
                                map[hero.getX()][hero.getY()] = "[  x_x  ]";
                                printMap(map);
                                i++;
                                bossKilled++;
                                if(bossKilled == 3){
                                    System.out.println("* Hero has defeated all the legendary BOSS! *");
                                    System.out.println("* YOU WIN *");
                                    a = 0;
                                    bossBattleLoop = 0;
                                }
                            }
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by The BOSS *");
                                System.out.println("* GAME OVER *");
                                a = 0;
                                bossBattleLoop = 0;
                            }
                        }else if(bossDecision.equals("recover") || bossDecision.equals("3")){
                            System.out.println("1. 1/2 Potion");
                            System.out.println("2. 100% Potion");
                            Scanner recoverDecisionB = new Scanner(System.in);
                            System.out.println("Please enter a selecion");
                            String recoverB = recoverDecisionB.nextLine();
                            if(recoverB.equals("1")){
                                if(halfEnergyDrink > 0){
                                    hero.setHealth(hero.getHealth() + 50);
                                    halfEnergyDrink--;
                                    inventorySpace--;
                                    System.out.println("*Hero has gain 50 hp*");
                                }else{
                                    System.out.println("*Hero doesn't have any potion to drink*");
                                }                                
                            }else if(recoverB.equals("2")){
                                if(fullEnergyDrink > 0){
                                    hero.setHealth(hero.getHealth() + 100);
                                    fullEnergyDrink--;
                                    inventorySpace--;
                                    System.out.println("*Hero has gain 100 hp*");
                                }else{
                                    System.out.println("*Hero doesn't have any potion to drink*");
                                }
                            }else{
                                System.out.println("Please enter a valid command");
                            }
                        }
                    }else if(i == 1 && hero.getInventory() == "Satchel"){
                        Scanner bossBattle = new Scanner(System.in);
                        System.out.println("Enter an action (1.Run, 2.Attack, 3.Recover): ");
                        String bossDecision = bossBattle.nextLine();
                        if(bossDecision.equals("mason")){
                            hero.setHealth(200);
                        }
                        if(bossDecision.equals("money")){
                            hides+=500;
                        }
                        if(bossDecision.equals("Run") || bossDecision.equals("1")){
                            System.out.println("\n\nHero has successfully ran away!");
                            map[hero.getX()][hero.getY()] = "[ BOSS "+i+"]";
                            bossBattleLoop = 0;
                        }else if(bossDecision.equals("Attack") || bossDecision.equals("2")){
                            System.out.println("\n\nHero attacks, BOSS's energy goes down to " + (boss[i-1].getHealth() - hero.getDamage()));
                            System.out.println("\nBOSS attacks, hero's energy goes down to " + (hero.getHealth() - boss[i-1].getAttack(hero)) + "/100");
                            boss[i-1].setHealth(boss[i-1].getHealth() - hero.getDamage());
                            hero.setHealth(hero.getHealth() - boss[i-1].getAttack(hero));
                            if(boss[i-1].getHealth() <= 0){
                                bossBattleLoop = 0;
                                bossKilled++;
                                map[hero.getX()][hero.getY()] = "[  x_x  ]";
                                i++;
                                printMap(map);
                                if(bossKilled == 3){
                                    System.out.println("* Hero has defeated all the legendary BOSS! *");
                                    System.out.println("* YOU WIN *");
                                    a = 0;
                                    bossBattleLoop = 0;
                                }
                            }
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by The BOSS *");
                                System.out.println("* GAME OVER *");
                                a = 0;
                                bossBattleLoop = 0;
                            }
                        }else if(bossDecision.equals("recover") || bossDecision.equals("3")){
                            System.out.println("1. 1/2 Potion");
                            System.out.println("2. 100% Potion");
                            Scanner recoverDecisionB = new Scanner(System.in);
                            System.out.println("Please enter a selecion");
                            String recoverB = recoverDecisionB.nextLine();
                            if(recoverB.equals("1")){
                                if(halfEnergyDrink > 0){
                                    hero.setHealth(hero.getHealth() + 50);
                                    halfEnergyDrink--;
                                    inventorySpace--;
                                    System.out.println("*Hero has gain 50 hp*");
                                }else{
                                    System.out.println("*Hero doesn't have any potion to drink*");
                                }                                
                            }else if(recoverB.equals("2")){
                                if(fullEnergyDrink > 0){
                                    hero.setHealth(hero.getHealth() + 100);
                                    fullEnergyDrink--;
                                    inventorySpace--;
                                    System.out.println("*Hero has gain 100 hp*");
                                }else{
                                    System.out.println("*Hero doesn't have any potion to drink*");
                                }
                            }else{
                                System.out.println("Please enter a valid command");
                            }
                        }
                    }else if(i ==3 &&hero.getWeapon().equals("EXCALIBUR")){
                        Scanner bossBattle = new Scanner(System.in);
                        System.out.println("Enter an action (1.Run, 2.Attack, 3.Recover): ");
                        String bossDecision = bossBattle.nextLine();
                        if(bossDecision.equals("mason")){
                            hero.setHealth(200);
                        }
                        if(bossDecision.equals("money")){
                            hides+=500;
                        }
                        if(bossDecision.equals("Run") || bossDecision.equals("1")){
                            System.out.println("\n\nHero has successfully ran away!");
                            map[hero.getX()][hero.getY()] = "[ BOSS "+i+"]";
                            bossBattleLoop = 0;
                        }else if(bossDecision.equals("Attack") || bossDecision.equals("2")){
                            System.out.println("\n\nHero attacks, BOSS's energy goes down to " + (boss[i-1].getHealth() - hero.getDamage()));
                            System.out.println("\nBOSS attacks, hero's energy goes down to " + (hero.getHealth() - boss[i-1].getAttack(hero)) + "/100");
                            boss[i-1].setHealth(boss[i-1].getHealth() - hero.getDamage());
                            hero.setHealth(hero.getHealth() - boss[i-1].getAttack(hero));
                            if(boss[i-1].getHealth() <= 0){
                                bossBattleLoop = 0;
                                map[hero.getX()][hero.getY()] = "[  x_x  ]";
                                printMap(map);
                                i++;
                                bossKilled++;
                                if(bossKilled == 3){
                                    System.out.println("* Hero has defeated all the legendary BOSS! *");
                                    System.out.println("* YOU WIN *");
                                    a = 0;
                                    bossBattleLoop = 0;
                                }
                            }
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by The BOSS *");
                                System.out.println("* GAME OVER *");
                                a = 0;
                                bossBattleLoop = 0;
                            }
                        }else if(bossDecision.equals("recover") || bossDecision.equals("3")){
                            System.out.println("1. 1/2 Potion");
                            System.out.println("2. 100% Potion");
                            Scanner recoverDecisionB = new Scanner(System.in);
                            System.out.println("Please enter a selecion");
                            String recoverB = recoverDecisionB.nextLine();
                            if(recoverB.equals("1")){
                                if(halfEnergyDrink > 0){
                                    hero.setHealth(hero.getHealth() + 50);
                                    halfEnergyDrink--;
                                    inventorySpace--;
                                    System.out.println("*Hero has gain 50 hp*");
                                }else{
                                    System.out.println("*Hero doesn't have any potion to drink*");
                                }                                
                            }else if(recoverB.equals("2")){
                                if(fullEnergyDrink > 0){
                                    hero.setHealth(hero.getHealth() + 100);
                                    fullEnergyDrink--;
                                    inventorySpace--;
                                    System.out.println("*Hero has gain 100 hp*");
                                }else{
                                    System.out.println("*Hero doesn't have any potion to drink*");
                                }
                            }else{
                                System.out.println("Please enter a valid command");
                            }
                        }
                    }
                    else{
                        System.out.println("* Hero is not strong enough to challenge the Boss *");
                        bossBattleLoop =0;
                    }
                }
            }
        
            if(checkMonsterMethod()&&no_monster ==0){
                Monster monster = new Monster();
                map[hero.getX()][hero.getY()] = "[battle ]";
                while(battleLoop == 1){
                    System.out.println("\n\nMonster incoming! Ready to encounter!");
                    printMap(map);
                    System.out.println("The monster's info has been updated: ");
                    System.out.println("Health: " + monster.getHealth());
                    System.out.println("Speed: " + monster.getSpe());
                    System.out.println("Attack: " + monster.getAtt(hero));
                    System.out.println("Monster is facing: " + monster.getDirection());
                    Scanner Battle = new Scanner(System.in);
                    System.out.println("Enter an action (run, attack, recover, bomb): ");
                    String battle = Battle.nextLine();
                    if(battle.equals("mason")){
                            hero.setHealth(200);
                    }
                    if(battle.equals("money")){
                            hides+= 500;
                    }
                    if(battle.equals("run")){
                        if(hero.getDirection().equals("North") && monster.getDirection().equals("South")){
                            System.out.println("*Hero can't run away because he is facing the monster*");
                            System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                            hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                if(phenoix > 0){
                                    System.out.println("Item Phenoix Down activated! Hero has been revived! Keep fighting!");
                                    hero.setHealth(100);
                                    System.out.println("Item Phenoxi Down - 1");
                                    phenoix--;
                                    inventorySpace--;
                                }else{
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }else if(hero.getDirection().equals("South") && monster.getDirection().equals("North")){
                            System.out.println("*Hero can't run away because he is facing the monster*");
                            System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                            hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                if(phenoix > 0){
                                    System.out.println("Item Phenoix Down activated! Hero has been revived! Keep fighting!");
                                    hero.setHealth(100);
                                    System.out.println("Item Phenoxi Down - 1");
                                    phenoix--;
                                    inventorySpace--;
                                }else{
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }else if(hero.getDirection().equals("West") && monster.getDirection().equals("East")){
                            System.out.println("*Hero can't run away because he is facing the monster*");
                            System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                            hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                if(phenoix > 0){
                                    System.out.println("Item Phenoix Down activated! Hero has been revived! Keep fighting!");
                                    hero.setHealth(100);
                                    System.out.println("Item Phenoxi Down - 1");
                                    phenoix--;
                                    inventorySpace--;
                                }else{
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }else if(hero.getDirection().equals("East") && monster.getDirection().equals("West")){
                            System.out.println("*Hero can't run away because he is facing the monster*");
                            System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                            hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            if(hero.death()){
                                System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                                if(phenoix > 0){
                                    System.out.println("Item Phenoix Down activated! Hero has been revived! Keep fighting!");
                                    hero.setHealth(100);
                                    System.out.println("Item Phenoxi Down - 1");
                                    phenoix--;
                                    inventorySpace--;
                                }else{
                                    System.out.println("GAME OVER");
                                    a = 0;
                                    battleLoop = 0;
                                }
                            }
                        }
                        if(hero.getDirection().equals(monster.getDirection())){
                            System.out.println("*Hero has successfully ran away*");
                            battleLoop = 0;
                        }
                        if(hero.getDirection().equals("North") && monster.getDirection().equals("West")){
                            if(monster.getSpe() == 3){
                                System.out.println("*Hero tries to run away, the monster is too fast*");
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            }else if(monster.getSpe() == 2){
                                int chance = (int)(Math.random() * 2);
                                if(chance == 0){
                                    System.out.println("*Hero tries to run away, the monster is too fast*");
                                    System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                    hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                                }else if(chance == 1){
                                    System.out.println("*Hero has successfully ran away*");
                                    battleLoop = 0;
                                }
                            }else if(monster.getSpe() == 1){
                                System.out.println("*Hero has successfully ran away*");
                                battleLoop = 0;
                            }else if(monster.getSpe() == 0){
                                System.out.println("*Hero has successfully ran away*");
                                battleLoop = 0;
                            }
                        }
                    }
                    
                    if(battle.equals("attack")){
                        if(hero.getSandals()){
                            if(checkMiss(monster)){
                                System.out.println("*Hero missed his attack*");
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            }else{
                                System.out.println("\n\nHero attacks with Sandal, the monster's energy goes down to " + (monster.getHealth() - (hero.getDamage() * 2)));
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                                monster.setHealth(monster.getHealth() - (hero.getDamage() * 2));
                            }
                        }else if(weapon.getName().equals("Axe")){
                            if(checkMiss(monster)){
                                System.out.println("*Hero missed his attack*");
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            }else{
                                System.out.println("\n\nHero attacks with Axe, the monster's energy goes down to " + (monster.getHealth() - hero.getDamage()));
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - (monster.getAtt(hero) * 2)) + "/100");
                                hero.setHealth(hero.getHealth() - (monster.getAtt(hero) * 2));
                                monster.setHealth(monster.getHealth() - hero.getDamage());
                            }
                        }else if(weapon.getName().equals("Axe") && hero.getSandals() == true){
                            if(checkMiss(monster)){
                                System.out.println("*Hero missed his attack*");
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            }else{
                                System.out.println("\n\nHero attacks with Axe and a Sandal, the monster's energy goes down to " + (monster.getHealth() - hero.getDamage()));
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                                monster.setHealth(monster.getHealth() - hero.getDamage());
                            }
                        }else{
                            if(checkMiss(monster)){
                                System.out.println("*Hero missed his attack*");
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                            }else{
                                System.out.println("\n\nHero attacks, the monster's energy goes down to " + (monster.getHealth() - hero.getDamage()));
                                System.out.println("\nMonster attacks, hero's energy goes down to " + (hero.getHealth() - monster.getAtt(hero)) + "/100");
                                hero.setHealth(hero.getHealth() - monster.getAtt(hero));
                                monster.setHealth(monster.getHealth() - hero.getDamage());
                            }
                        }
                        if(monster.getHealth() <= 0 ){
                            System.out.println("\n\nThe monster has been defeated!");
                            monster.setX();
                            hero.setExp(hero.getExp() + 15);
                            System.out.println("Hero EXP + 15");
                            System.out.println("Reward: +" + getHides +" Hides");
                            hides = hides + getHides;
                            monsterKilled++;
                            map[hero.getX()][hero.getY()] = "[  x_x  ]";
                            printMap(map);
                            battleLoop = 0;
                            if(hero.getExp() >= hero.getLevelUpExp()){
                                hero.upgrade();
                                System.out.println("LEVEL UP!");
                                hero.setHealth(hero.getHealth() + 20);
                                System.out.println("Hero has gained 20 extra HP!");
                            }
                        }
                        if(monster.getHealth() <= 0 || hero.getHealth() <= 0){
                            if(hero.death()){
                                System.out.println("*Hero has defeated the monster but also lost all of his energy!*");
                                System.out.println("GAME OVER");
                            }
                        }
                        if(hero.death()){
                            System.out.println("* Hero collapses and is struck a deadly blow by Monster *");
                            if(phenoix > 0){
                                System.out.println("Item Phenoix Down activated! Hero has been revived! Keep fighting!");
                                hero.setHealth(100);
                                System.out.println("Item Phenoxi Down - 1");
                                phenoix--;
                                inventorySpace--;
                            }else{
                                System.out.println("GAME OVER");
                                a = 0;
                                battleLoop = 0;
                            }
                       
                        }
                    }      
                    
                    if(battle.equals("recover")){
                        System.out.println("1. 1/2 Potion");
                        System.out.println("2. 100% Potion");
                        Scanner recoverDecision = new Scanner(System.in);
                        System.out.println("Please enter a selecion");
                        String recover = recoverDecision.nextLine();
                        if(recover.equals("1") ){
                            if(halfEnergyDrink > 0){
                                hero.setHealth(hero.getHealth() + 30);
                                halfEnergyDrink--;
                                inventorySpace--;
                                System.out.println("*Hero has gain 30 hp*");
                            }else{
                                System.out.println("**Hero doesn't have any potion to drink");
                            }
                        }else if(recover.equals("2")){
                            if(fullEnergyDrink > 0){
                                hero.setHealth(hero.getHealth() + 100);
                                fullEnergyDrink--;
                                inventorySpace--;
                                System.out.println("*Hero has gain 100 hp*");
                            }else{
                                System.out.println("**Hero doesn't have any potion to drink");
                            }
                        }else{
                            System.out.println("Please enter a valid command");
                        }
                    }
                    
                    if(!battle.equals("run") && !battle.equals("attack") && !battle.equals("recover")){
                        System.out.println("Please enter a valid command!");
                    }
                    
                    if(battle.equals("bomb")){
                        if(bomb > 0){
                            System.out.println("\n\nThe monster has been defeated!");
                            monster.setX();
                            hero.setExp(hero.getExp() + 15);
                            System.out.println("Hero EXP + 15");
                            System.out.println("Reward: +" + getHides +" Hides");
                            hides = hides + getHides;
                            monsterKilled++;
                            map[hero.getX()][hero.getY()] = "[  x_x  ]";
                            printMap(map);
                            battleLoop = 0;
                            if(hero.getExp() >= hero.getLevelUpExp()){
                                hero.upgrade();
                                System.out.println("LEVEL UP!");
                                hero.setHealth(hero.getHealth() + 20);
                                System.out.println("Hero has gained 20 extra HP!");
                            }
                        }else{
                            System.out.println("*Hero doesn't have any bomb*");
                        }
                    }
                }
            }
        }
    }

    public static void fillMap(Object[][]map){
        for(int k = 0; k < map.length; k++){
            for(int l = 0; l < map[0].length; l++){
                map[k][l] = "[  ? ?  ]";
            }
        }
    }
    
    public static void printMap(Object[][] map){
        for(int o = 0; o < map.length; o++){
            for(int p = 0; p < map[0].length; p++){
                System.out.print(map[o][p] + "  ");
            }
            System.out.println();
        }
    }

    public static void updateHero(Object[][] map, Hero hero){
        map[hero.getX()][hero.getY()] = "[ Hero  ]";
    }
    
    public static boolean farsightFarmer(Farmer farmer, Hero hero, Object[][] map){
        if(hero.getX() == farmer.getX()){
            if(hero.getY() == farmer.getY() + 2 || hero.getY() == farmer.getY() - 2){
                return true;
            }
        }else if(hero.getY() == farmer.getY()){
            if(hero.getX() == farmer.getX() + 2 || hero.getX() == farmer.getX() - 2){
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkPotionMethod(Potion[] potion, Hero hero, Object[][] map){
        for(int i = 0; i < potion.length; i++){
            if(hero.getX() == potion[i].getX() && hero.getY() == potion[i].getY() && potion[i].potionReturn()){
                potion[i].foundP();
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkMonsterMethod(){
        int monsterc = (int)(Math.random() * 20) + 1;
        if(monsterc == 4){
            return true;
        }
        return false;
    }
    
    public static boolean checkBossMethod(EliteMonster[] Boss, Hero hero, int activate){
        if(hero.getX() == Boss[0].getX() &&hero.getY() == Boss[0].getY()&& activate ==1){            
            return true;
        }else if(hero.getX() == Boss[1].getX() &&hero.getY() == Boss[1].getY()&& activate ==2){            
            return true;
        }else if(hero.getX() == Boss[2].getX() &&hero.getY() == Boss[2].getY()&& activate ==3){            
            return true;
        }
        return false;
    }
    
    public static int checkFarmerMethod(Farmer[] farmer, Hero hero, Object[][] map){
        int count = 4;
        for(int i = 0; i < farmer.length; i++){
            if(hero.getX() == farmer[i].getX() && hero.getY() == farmer[i].getY()){
                count = i;
            }
        }
        return count;
    }

    public static boolean checkBoss(Farmer farm, EliteMonster[] boss){
        if(farm.getNumber() == 1){
            if(boss[0].Death()==false){
                return true;
            }else {
                return false; 
            }         
        }else if(farm.getNumber() == 2){
            if(boss[0].Death()){
                return true;
            }else {
                return false;
            }  
        }else if(farm.getNumber()== 3){
            if(boss[0].Death()&&boss[1].Death()){
                return true;
            }else {
                return false;
            } 
        }
        return false;
    }
    
    public static boolean checkTraderMethod(Trader trader, Hero hero){
        if(hero.getX() == trader.getX() && hero.getY() == trader.getY()){
            return true;
        }
        return false;
    }
    
     public static boolean checkMiss(Monster monster){
        int rate = (int)(Math.random()*10)+1;
        if(monster.getSpe()==1&&rate ==1){
            return true; 
        }else if (monster.getSpe()==2&&rate ==1||rate ==2){
            return true; 
        }else if (monster.getSpe()==3&&rate ==1||rate ==2||rate ==3){
            return true;
        }else if(monster.getSpe() == 0){
            return false;
        }
        return false;
    }
}