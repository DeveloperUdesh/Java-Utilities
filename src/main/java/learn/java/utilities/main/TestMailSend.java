package learn.java.utilities.main;

import learn.java.utilities.mail.EmailSender;

import java.util.Properties;

public class TestMailSend {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("", "");
        String status = new EmailSender("", "", "", "", "", "", "", properties).send();
        System.out.println(status);
    }
}
