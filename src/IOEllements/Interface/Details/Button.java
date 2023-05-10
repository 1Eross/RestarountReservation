package IOEllements.Interface.Details;

public class Button implements Drowable{
    int X;
    int Y;
    int height;
    int width;
    char symbol;
    String text;
    String color;
    String backgroundColor;
    public Button(int X, int Y, int height, int width, String text, char symbol, String color, String backgroundColor){
        this.X = X;
        this.Y = Y;
        this.height = height;
        this.width = width;
        this.text = text;
        this.symbol = symbol;
        this.color = color;
        this.backgroundColor = backgroundColor;
    }

    public void Highlight() {

    }
    public void onPress(){

    }
    public void draw(){
            for (int y = Y; y < height + Y; y++) {
                for (int x = X; x < width + X; x++) {
                    if (x == X || x == width + X - 1 || y == Y || y == height + Y - 1) {
                        System.out.print("\033[" + y + ";" + x + "H");
                        System.out.print(backgroundColor + color + symbol);
                    }
                }
            }
            int tempX = width / 2 - text.length() / 2 + X;
            int tempY = height / 2 + Y;
            System.out.print("\033[" + tempY + ";" + tempX + "H");
            System.out.print(text);
    }
}
