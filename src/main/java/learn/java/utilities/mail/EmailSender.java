package learn.java.utilities.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final Logger LOG = LoggerFactory.getLogger(EmailSender.class);
    private final String to;
    private final String cc;
    private final String from;
    private final String subject;
    private final String content;
    private final String user;
    private final String password;
    private final Properties properties;

    public EmailSender(String to, String cc, String from, String subject, String content, String user, String password, Properties properties) {
        this.to = to;
        this.cc = cc;
        this.from = from;
        this.subject = subject;
        this.content = content;
        this.user = user;
        this.password = password;
        this.properties = null != properties ? properties : defaultSMTPProperties();
    }

    public String send() {
        try {
            Session mailSession = Session.getDefaultInstance(properties);
            mailSession.setDebug(true);
            Message message = constructEmailMessage(mailSession);
            Transport transport = mailSession.getTransport();
            transport.connect(user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.connect();
            return "OK";
        } catch (MessagingException e) {
            LOG.error(e.getMessage(), e);
            return "NOK";
        }
    }

    private Properties defaultSMTPProperties() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    private Message constructEmailMessage(Session mailSession) throws MessagingException {
        Message message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
        message.setSubject(subject);
        message.setText(content);
        message.saveChanges();
        return message;
    }
}
