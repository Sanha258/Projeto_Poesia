package Projeto_Poesia.BackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.EmailTemplateDTO;
import Projeto_Poesia.BackEnd.Service.util.RedFile;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailTemplate {
    
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from:no-reply@poesia.com}")
    private String from;

    public void sendEmailFromTemplate(EmailTemplateDTO emailTemplateDTO) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(from));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from my Springapplication");

        //Read the HMTL template into a String variable(from classpath resources)
        String htmlTemplate = RedFile.redFile("template/email-template.html");

        //Replace placeholders in the HTML template with dynamic values
        htmlTemplate = htmlTemplate.replace("${nome}", emailTemplateDTO.getNome());
        htmlTemplate = htmlTemplate.replace("${mensagen}", emailTemplateDTO.getMensagem());

        //Set the email content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }

}
