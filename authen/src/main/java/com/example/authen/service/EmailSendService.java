package com.example.authen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String SendCreateAccount(String destinatario, String username){

        String assunto = "Conta criada na Sentinel";
        String mensagem = String.format("Óla %s sua conta foi criada com sucesso", username);

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);

            return "Email enviado";

        }catch (Exception e){

            return "Erro ao tentar enviar" + e.getLocalizedMessage();

        }
    }

    public String SendUserBanned(String destinatario, String username, String status, String motivo){

        String assunto = "Sua conta foi banida";
        String mensagem = String.format("Óla %s sua conta foi %s por: %s",username, status, motivo);

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);

            return "Email enviado";

        }catch (Exception e){

            return "Erro ao tentar enviar" + e.getLocalizedMessage();

        }
    }

    public String ChangePassword(String destinatario, String assunto, String mensagem){
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);

            return "Email enviado";

        }catch (Exception e){

            return "Erro ao tentar enviar" + e.getLocalizedMessage();

        }
    }

    public String ChangeUsername(String destinatario) {

        String assunto = "Alteração de Username";
        String msg = "Voce fez alteração de username na  SentinelAuth";

        try{

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(msg);
            javaMailSender.send(simpleMailMessage);

            return "Email enviado";

        }catch (Exception e){

            return "Erro ao tentar enviar" + e.getLocalizedMessage();

        }

    }

    public String ChangePermission(String destinatario){

        String assunto = "Alteração de permissão";
        String msg = "Voce teve a permissão da sua conta alterada";

        try{

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(msg);
            javaMailSender.send(simpleMailMessage);

            return "Email enviado";

        }catch (Exception e){
            return "Erro ao tentar enviar"+ e.getLocalizedMessage();
        }
    }
}
