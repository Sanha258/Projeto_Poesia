package Projeto_Poesia.BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Projeto_Poesia.BackEnd.DTO.EmailTemplateDTO;
import Projeto_Poesia.BackEnd.Service.EmailTemplate;
import jakarta.mail.MessagingException;

@RestController
public class EmailController {
    
    @Autowired
    private EmailTemplate emailTemplate;

    @PostMapping("/api/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailTemplateDTO emailTemplateDTO){
        try {
            emailTemplate.sendEmailFromTemplate(emailTemplateDTO);
            return ResponseEntity.ok("Email enviado com sucesso!");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Erro ao enviar email: " + e.getMessage());
        }
    }

}
