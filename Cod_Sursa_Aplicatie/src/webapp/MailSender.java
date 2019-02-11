package webapp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private final String fromAddress = "houmzzanunturiimobiliare";
    private final String fromPassword = "anunturiImobiliare";
    private String toAddressEmail;
    private final String[] subject = {"Thank you for registration",
                                      "Anuntul dvs a fost adaugat cu success"
                                     };
    private final String[] mailBody = {"You account has been created succesfully! We wish you all the best",
                                       "Anuntul tau a fost adaugat cu success. Intra in aplicatie pentru a-l vizualiza"};

    public MailSender(String toAddressEmail) {
        this.toAddressEmail = toAddressEmail;
    }

    public void sendEmail(int index) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", fromAddress);
        props.put("mail.smtp.password", fromPassword);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(fromAddress));
            InternetAddress[] toAddress = new InternetAddress[1];
            toAddress[0] = new InternetAddress(toAddressEmail);
            message.addRecipient(Message.RecipientType.TO, toAddress[0]);
            message.setSubject(subject[index]);
            message.setText(mailBody[index]);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, fromAddress, fromPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
