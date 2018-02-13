public class Potion{
    private int x_coord;
    private int y_coord;
    private boolean foundPotion;
    
    public Potion(){
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
        foundPotion = true;
    }
    
    public void foundP(){
        foundPotion = false;
    }
    
    public boolean potionReturn(){
        return foundPotion;
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
}
