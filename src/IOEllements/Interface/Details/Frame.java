package IOEllements.Interface.Details;

public class Frame implements Drowable {
    int X;
    int Y;
    int height;
    int width;
    char symbol;
    String color;
    String backgroundColor;


    public Frame(int X, int Y, int height, int width, char symbol, String color, String backgroundColor){
        this.X = X;
        this.Y = Y;
        this.height = height;
        this.width = width;
        this.symbol = symbol;
        this.color = color;
        this.backgroundColor = backgroundColor;
    }
    public void draw() {
        for (int y = Y; y < height + Y; y++) {
            for (int x = X; x < width + X; x++) {
                if (x == X || x == width + X - 1 || y == Y || y == height + Y - 1) {
                    System.out.print("\033[" + y + ";" + x + "H");
                    System.out.print(backgroundColor + color + symbol + Color.ANSI_RESET);
                }
            }
        }
    }
}
