package IOEllements.Interface.Details;

public class Pointer {
    public static void set(int X, int Y){
        System.out.print("\033[" + Y + ";" + X + "H");
    }
}
