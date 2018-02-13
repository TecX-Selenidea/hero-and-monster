public class Hero{
    private int health;
    private int x_coord;
    private int y_coord;
    private int weaponCondition = 0;
    private int armorCondition = 0;
    private Weapon weapon;
    private Armor armor = new Armor();
    private int ArmorCheck = 0;
    private int damage = 0;
    private int exp;
    private int level;
    private int levelUpExp;
    private boolean damageAdd;
    private String direction;
    private String inventory;
    public boolean sandals;
    
    public Hero(){
        health = 100;
        weapon = new Weapon();
        x_coord = 0;
        y_coord = 14;
        exp = 0;
        level = 0;
        levelUpExp = 30;
        inventory = new String("Trousers");
        sandals = false;
    }
    
    public void setSandals(){
        sandals = true;
    }
    
    public boolean getSandals(){
        return sandals;
    }
    
    public void setInventory(String inventory){
        this.inventory = inventory;
    }
    
    public String getInventory(){
        return inventory;
    }
    
    public String getDirection(){
        return direction;
    }
    
    public void setDirection(String direction){
        this.direction = direction;
    }
    
    public void damageAddStart(){
        damageAdd = true;
    }
    
    public int getExp(){
        return exp;
    }
    
    public void setExp(int e){
        exp = e;
    }
    
    public int getLevel(){
        return level;
    }
    
    public int getLevelUpExp(){
        return levelUpExp;
    }
    
    public void upgrade(){
        level++;
        exp = 0;
        levelUpExp += 10;
    }
    
    public void setWeapon(){
        weaponCondition = 1;
    }
    
    public void setWeapon1(){
        weaponCondition = 2;
    }
    
    public void setWeapon2(){
        weaponCondition = 3;
    }
    
    public void setArmor(){
        armorCondition = 1;
    }
    
    public void setDamage(int d){
        d = damage;
    }
    
    public boolean getArmor(){
        if(armorCondition == 1){
            return true;
        }
        return false;
    }
    
    public String getWeapon(){
        if(weaponCondition == 1){
            return "Sword";
        }else if(weaponCondition == 2){
            return "EXCALIBUR";
        }else if(weaponCondition == 3){
            return "Axe";
        }
        return "Dagger";
    }
    
    public void heroMove(String direction){
        if(direction.equals("n")){
            x_coord -= 1;
        }else if(direction.equals("s")){
            x_coord += 1;
        }else if(direction.equals("w")){
            y_coord -= 1;
        }else if(direction.equals("e")){
            y_coord += 1;
        }
        if(x_coord > 14){
            x_coord = 14;
        }
        if(y_coord > 14){
            y_coord = 14;
        }
        if(x_coord < 0){
            x_coord = 0;
        }
        if(y_coord < 0 ){
            y_coord = 0;
        }
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int h){
        health = h;
    }
    
    public int getDamage(){
        if(weapon.getName().equals("dagger")){
            damage = weapon.getDamage();
        }
        if(weapon.getName().equals("Sword")){
            damage = (int)(Math.random() * 30 + 21);
        }
        if(weapon.getName().equals("Axe")){
            damage = 40;
        }
        if(weapon.getName().equals("EXCALIBUR")){
            damage = 50;
        }
        return damage;
    }
    
    public void pickArmor(){
        ArmorCheck = 1;
    }
    
    public boolean checkArmor(){
        if(ArmorCheck == 1){
            return true;
        }
        return false;
    }
    
    public boolean death(){
        if(health <= 0){
            return true;
        }
        return false;
    }
    
    public void pickPotion(){
        health = 100;
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
    
    public void switchWeapon(){
        weapon.switchWeapon();
    }
    
    public void switchWeapon1(){
        weapon.switchWeapon1();
    }
    
    public void switchWeapon2(){
        weapon.switchWeapon2();
    }
}
