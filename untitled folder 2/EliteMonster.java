public class EliteMonster extends Character implements setMonster{
    private int number;
    private int attack;
    private int health;
    private int x_coord;
    private int y_coord;
    private int x = 0;
    
    public EliteMonster(int number){
        if(number ==1){
            health = 100;
            attack = 20;
        }
        else if(number == 2){
            health = 200;
            attack = 30;
        }else if(number ==3){
            health = 300;
            attack = 40;
        }
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
    }
    
    public void setHealth(int h){
        health = h;
    }
    
    public int getBossAttack(){
        return attack;
    }
    
    public int getNumber(){
        return number;
    }
    
    public void setNumber(int num){
        number = num;
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
    
    public int getHealth(){
        return health;
    }
    
    
    
    public boolean checkWeapon(String weapon){
        if(number == 1){
            if(weapon == "Satchel"){
                return true;
            }
            else{
                return false;
            } 
        }else if(number == 2){
            if(weapon == "Sword"){
                return true;
            }else{
                return false;
            }
        }else if(number == 3){
            if(weapon == "EXCALIBUR"){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    public boolean Death(){
        if(health <= 0){
            return true;
        }
        return false;
    }
    
    public int getAttack(Hero hero){
        if(x == 0){
            if(hero.getArmor()){
                attack = attack - (int)(attack / 3);
                x++;
            }
        }
        return attack;
    }
}
