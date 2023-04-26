import InfoElements.Node;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Log.Clear();

        Database database = new Database("login.txt", "clients.txt", null, null);
        Node temp = Registration.Authorization(database.loginsBuffer, database.clientsBuffer);

        if (temp != null) {
            JOptionPane.showMessageDialog(null, "Пользователь " + temp.dataline);
        } else {
            JOptionPane.showMessageDialog(null, "Пользователь не найден");
        }
    }
}