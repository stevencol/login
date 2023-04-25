package com.libros.libros.mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.UUID;

@Service
public class MailSender {

    @Autowired
    private JavaMailSender senMail;

    @Autowired
    private TemplateEngine templateEngine;

   //Correo Simple
    public void sendMailSimple(String toMail, String texto){
        SimpleMailMessage mensajeSimple = new SimpleMailMessage();
        mensajeSimple.setSubject("Registro Completo");
        mensajeSimple.setText(getCode());
        mensajeSimple.setTo(toMail);
        senMail.send(mensajeSimple);
    }


    //ConPlantillaYAdjuntos
    public String sendCodeVerification(String destino, String nombre) throws  MessagingException {

  ;
            String code= getCode();
        MimeMessage mimeMessage = senMail.createMimeMessage();

        Context contedio = new Context();
        contedio.setVariable("nombre" , nombre);
        contedio.setVariable("code" ,    code);
        contedio.setVariable("img",  new FileSystemResource(new File("E:\\Datos\\Imágenes\\gifs\\cc58774aae085c1c84fd39bc4cd4084d586c7afdr1-320-240_00.gif")));
        String plantilla = templateEngine.process("code" ,contedio);
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true , "UTF-8" );

        message.setTo(destino);
        message.setSubject("Link de Activacion");
        message.setText(plantilla, true);


        senMail.send(mimeMessage);
  return  code;
    }



    private String getCode(){
        String g = "";

        while (g.length()<=10){
            g += ( (int) (Math.random() *(9-1+1)+1));
        }

        return g+UUID.randomUUID();
    }
}
