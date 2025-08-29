package Projeto_Poesia.BackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.EmailDTO;
import Projeto_Poesia.BackEnd.Service.util.RedFile;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailTemplate {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.from:no-reply@example.com}")
    private String from;

    public void sendEmailFromTemplate(EmailDTO emailDTO) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(from));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from my Springapplication");

        String htmlTemplate = RedFile.redFile("templates/email-template.html");

        htmlTemplate = htmlTemplate.replace("${nome}",emailDTO.getNome());
        htmlTemplate = htmlTemplate.replace("${mensagem}",emailDTO.getMensagem());

        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }
    
}
