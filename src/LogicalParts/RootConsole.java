package LogicalParts;


import IOEllements.Interface.Details.Color;
import IOEllements.Interface.HomeScreen;

public class RootConsole {
    public static void Update(Database database){
        HomeScreen.Update(database);
    }
    public static void clearConsole() {
        System.out.print(Color.ANSI_RESET);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
