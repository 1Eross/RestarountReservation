package IOEllements.Interface.Details;

public class Text implements Drowable{
    int X;
    int Y;
    String text;
    String color;
    String backgroundColor;
    public Text(int X, int Y, String Text, String color, String backgroundColor){
        this.X = X;
        this.Y = Y;
        this.text = Text;
        this.color = color;
        this.backgroundColor = backgroundColor;
    }
    public void draw(){
        System.out.print("\033[" + Y + ";" + X + "H");
        System.out.print(color + backgroundColor + text + Color.ANSI_RESET);
    }
    public void pointerAfter(){
        System.out.print("\033[" + Y + ";" + (X + text.length() + 1) + "H");
    }
    public void setText(String text){
        this.text = text;
    }
}
