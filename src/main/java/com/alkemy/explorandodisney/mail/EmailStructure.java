package com.alkemy.explorandodisney.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;


import java.io.IOException;


public class EmailStructure {

    public static void send(String posting, String token) throws IOException {
        Email from = new Email ("zabalaflorencia.it@gmail.com");
        Email to = new Email(posting);
        String subject = "Welcome to exploring Disney World!";

        Template template = new Template();
        String htmlTemplate = template.getHtmlTemplate(token);
        Content content = new Content("text/html",htmlTemplate);
        Mail mail = new Mail(from,subject,to,content);
        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }catch (IOException ex){
            throw ex;
        }
    }
}
