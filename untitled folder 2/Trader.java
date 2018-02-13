public class Trader{
    private int x_coord;
    private int y_coord;
    
    public Trader(){
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
    }
    
    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
}
