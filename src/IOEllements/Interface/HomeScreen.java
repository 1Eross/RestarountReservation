package IOEllements.Interface;

import IOEllements.Interface.Details.Color;
import IOEllements.Interface.Details.Frame;
import IOEllements.Interface.Details.Pointer;
import IOEllements.Interface.Details.Text;
import LogicalParts.Database;
import LogicalParts.RootConsole;

import java.util.Scanner;

public class HomeScreen {
    private static final Frame rootFrame = new Frame(0, 0, 40, 156, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text title = new Text(55,10,"DATABASE", Color.ANSI_RESET, "");
    private static final Text errors = new Text(54,18, "", Color.ANSI_RED, "");
    private static final Frame terminalFrame = new Frame(0, 28, 3 ,13, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text terminal = new Text(3, 29, "Terminal", Color.ANSI_RESET, "");
    private static final Frame inputFrame = new Frame(13, 28, 3, 142, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    public static void Draw(){

        rootFrame.draw();
        title.draw();
        errors.draw();
        terminal.draw();
        terminalFrame.draw();
        inputFrame.draw();
    }
    public static void Update(Database database){

        RootConsole.clearConsole();
        HomeScreen.Draw();

        Scanner scanner = new Scanner(System.in);
        Pointer.set(15 ,29);
        String command = scanner.nextLine();

        switch (command.toLowerCase()) {
            case "logon":
                Registration.Update(database);
                break;
            case "login":
                Authorization.AuthorizationUpdate(database);
                break;
            case "close":
                System.exit(0);
            default:
                errors.setText("Wrong Input");
                Update(database);
                break;
        }
    }
}
