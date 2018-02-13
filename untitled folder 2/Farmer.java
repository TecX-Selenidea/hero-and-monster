public class Farmer{
    private int number;
    private String voice;
    private int x_coord;
    private int y_coord;
    private String name;
    public Farmer(int n ){
        number = n;
        if(number == 0){
            name = "[Benson ]";
            voice = "I am Benson; My sheep have been taken… My family is gone and I have nothing left except for bronze amor I have buried… Only a true hero will receive this.\n I will tell you the positions of other farmers";
        }
        if(number == 1){
            name = "[ Andy  ]";
            voice = "I am Andy; nice to meet you";
        }
        if(number == 2){
            name = "[ Ivan  ]";
            voice = "I am Ivan; Nice to meetttttttt you!!!!";
        }
        if(number == 3){
            name = "[ Mason ]";
            voice = "I am Mason; nice to meet you...";
        }
        
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
    }
 
    public String toString(){
        String output = "";
        output = "\n*Hero has meet a townpeople*" +"\n" + voice; 
        return output;
    }
    
    public int getNumber(){
        return number;
    }
    
    public String getName(){
        return name;
    }

    public int getX(){
        return x_coord;
    }
    
    public int getY(){
        return y_coord;
    }
    
}
