public class Weapon{
    private String weaponName = "dagger";
    private int damage;

    public Weapon(){
        damage = (int)(Math.random() * 20 + 11);
    }
    
    public int getDamage(){
        return damage;
    }
    
    public void setDamage(int d){
        damage = d;
    }
    
    public void switchWeapon1(){
        weaponName = "EXCALIBUR";
    }
    
    public void switchWeapon2(){
        weaponName = "Axe";
    }
    
    public void switchWeapon(){
        weaponName = "sword";
    }
    
    public String getName(){
        return weaponName;
    }
}