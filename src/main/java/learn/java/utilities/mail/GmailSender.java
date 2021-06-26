package learn.java.utilities.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSender {
    public static void send(String from, String password, String to, String sub, String msg) {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        /*props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");*/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        //session.setDebug(true);
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            System.out.println("about to send message");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", 587, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
        } catch (
                MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        send("udesh.puli.52694@gmail.com", "Luckyteddy@143", "udesh.mudhigonda@gmail.com", "hello Udesh", "I'm sending this from my Java code :)");
        System.out.println("end");
    }
}
