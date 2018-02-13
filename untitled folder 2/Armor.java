public class Armor{
    private int x_coord;
    private int y_coord;
    
    public Armor(){
        x_coord = (int)(Math.random() * 10) + 1;
        y_coord = (int)(Math.random() * 10) + 1;
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
}
