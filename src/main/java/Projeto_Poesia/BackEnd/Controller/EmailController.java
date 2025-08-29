package Projeto_Poesia.BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Projeto_Poesia.BackEnd.DTO.EmailDTO;
import Projeto_Poesia.BackEnd.Service.EmailTemplate;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class EmailController {
    @Autowired
    private EmailTemplate emailTemplate;
    
    @PostMapping("/api/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO){
        try {
            emailTemplate.sendEmailFromTemplate(emailDTO);
            return ResponseEntity.ok("EMAIL ENVIADO!");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("erro ao enviar email:" + e.getMessage());
            // TODO: handle exception
        }
    }

    
}
