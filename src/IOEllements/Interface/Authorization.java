package IOEllements.Interface;

import IOEllements.Interface.Details.Color;
import IOEllements.Interface.Details.Frame;
import IOEllements.Interface.Details.Text;
import LogicalParts.Database;
import LogicalParts.RootConsole;

import java.util.Scanner;

public class Authorization {

    private static final Frame rootFrame = new Frame(0, 0, 40, 156, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text title = new Text(55,10,"AUTHORIZATION", Color.ANSI_RESET, "");
    private static final Text login = new Text(40, 13, "Login: ", Color.ANSI_RESET, "");
    private static final Text password = new Text(40,15, "Password: ", Color.ANSI_RESET, "");

    public static void AuthorizationDraw(){

        rootFrame.draw();
        title.draw();
        login.draw();
        password.draw();

    }
    public static void AuthorizationUpdate(Database database){
        Scanner scanner = new Scanner(System.in);
        StringBuilder data = new StringBuilder();

        RootConsole.clearConsole();
        AuthorizationDraw();

        login.pointerAfter();
        data.append(scanner.next());

        password.pointerAfter();
        data.append(scanner.next());

        PersonalAccount account = new PersonalAccount(database.authorization(data.substring(0)));
        account.Update(database);

    }
}
