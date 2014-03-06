package checkmail;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
//import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;



public class CheckMail {
    
    CheckMail(){
        Properties props=new Properties();
        Session s=Session.getDefaultInstance(props, null);
        
        try{
            Store store=s.getStore("pop3");
            String server="pop.asia.secureserver.net";
            int port=110;
            String user="priti@airec.co.in";
            String pass="test123";
            store.connect(server, port, user, pass);
            Folder inbox=store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            Message[] messages=inbox.getMessages();
            
            for(Message message:messages){
                for(Address a:message.getFrom()){
                    System.out.println("From: "+a);
                    System.out.println("Subject: "+message.getSubject());
                    //System.out.println("Content: "+message.getContent());
                    Multipart mp=(Multipart)message.getContent();                                        
                    for(int i=0;i<mp.getCount();i++) {
                        BodyPart bodyPart = mp.getBodyPart(i);
                        if (bodyPart.isMimeType("text/*")) {
                        String cont = (String) bodyPart.getContent();
                        System.out.println("Content: "+cont);
                        }
                    }
                    //System.out.println("Content: "+cont);
                    System.out.println("-----\n");
                }
            }
            
            inbox.close(true);
            store.close();
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CheckMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(CheckMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String args[]){
        new CheckMail();
    }
    /*private boolean textIsHtml = false;
    private String getText(Part p) throws
                MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }*/    
}