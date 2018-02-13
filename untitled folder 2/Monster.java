public class Monster extends Character implements setMonster{
    private int attack;
    private int health;
    private int speed;
    private int x = 0;
    private int directionNum;
    private String direction;
    
    
    public Monster(){
        directionNum = (int)(Math.random() * 4);
        if(directionNum == 0){
            direction = "North";
        }else if(directionNum == 1){
            direction = "South";
        }else if(directionNum == 2){
            direction = "West";
        }else if(directionNum == 3){
            direction = "East";
        }
        attack = (int)(Math.random() * 20) + 10;
        health = (int)(Math.random() * 50) + 1;
        speed = (int)(Math.random() * 4);
        
    }
    
    public String getDirection(){
        return direction;
    }
    
    public void setX(){
        x = 0;
    }
    
    public int getAtt(Hero hero){
        if(x == 0){
            if(hero.getArmor()){
                attack -= (int)(attack / 3);
                x++;
            }
        }
        return attack;
    }
    
    public boolean death(){
        if(health <= 0){
            return true;
        }
        return false;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int h){
        health = h;
    }
    
    public int getSpe(){
        return speed;
    }
    
    public String toString(){
        return "Monster: \nAttack: " + attack + "\nHealth: " + health + "\nspeed: " + speed;
    }
}
