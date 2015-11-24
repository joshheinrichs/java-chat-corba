package server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    Date date;
    String username;
    String text;

    public Message(String username, String text) {
        this.date = new Date();
        this.username = username;
        this.text = text;
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date) + " - " + username + ": " + text;
    }
}
