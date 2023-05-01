package LogicalParts;

import HashUtilities.HashTable;
import InfoElements.Node;

import javax.swing.*;
import java.io.IOException;

public class Registration {
    public static Node Authorization(HashTable logins, HashTable clients) throws IOException {
        StringBuilder auth = new StringBuilder();

        String temp = JOptionPane.showInputDialog(null, "Введите логин");
        auth.append(temp);

        temp = JOptionPane.showInputDialog(null, "Введите пароль");
        auth.append(temp);

        Node logUser = logins.find(auth.substring(0));
        Node client = null;

        if (logUser != null) {
            client = clients.find(logUser.dataline);
        }

        return client;
    }
    public static Node createNewUser(HashTable logins, HashTable clients){
        return null;
    }
}