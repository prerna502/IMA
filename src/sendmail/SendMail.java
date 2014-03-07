package sendmail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	
	SendMail(String host,String user,String pass,String[] to,String subject,String content) throws AddressException, MessagingException{
		
            try{
		//String host="smtp.gmail.com";
                //String host2="smtp.mail.yahoo.com";
                host="smtpout.asia.secureserver.net";
		//String user="binoydalal93@gmail.com";
                //String user2="testingmyjavamail@yahoo.com";
                user="";
		//String pass=".hack%//sign66";
                //String pass2="Longlongroadon66!";
                pass="";
		Properties p=System.getProperties();
                //Properties p2=System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.user", user);
		p.put("mail.smtp.password", pass);
		//p.put("mail.smtp.port", "587"); // Gmail
                //p.put("mail.smtp.port","465");
                p.put("mail.smtp.port","3535");
		p.put("mail.smtp.auth", "true");
                
                /*p2.put("mail.smtp.starttls.enable", "true");
		p2.put("mail.smtp.host", host2);
		p2.put("mail.smtp.user", user2);
		p2.put("mail.smtp.password", pass2);
		p2.put("mail.smtp.port", "587"); // Gmail
                //p.put("mail.smtp.port","465");
		p2.put("mail.smtp.auth", "true");*/

                //String[] to={"binoydalal93@gmail.com","lttazz99@gmail.com","prerna502@yahoo.co.in","naveenlk.19@gmail.com"};
		
		Session s=Session.getDefaultInstance(p, null);
		/*MimeMessage m=new MimeMessage(s);
		m.setFrom(new InternetAddress(user));
				
                m.setHeader("X-Confirm-Reading-To", user);
                m.setHeader("Disposition-Notification-To", user);
                m.setHeader("Return-Receipt-To", user);
                m.setHeader("X-Errors-To", user);
                
		m.setSubject(subject);*/
                //m.setContent(content,"text/html");		
                //m.setContent("<html><p><h1>This is my first java mail with an image.</h1></p><p><img src=\"http://www.airecenergyindia.com/test2/Untitled12.jpg\"></p> This mail is a bit different</html>","text/html");
                //m.setContent("<html><p>This is my first java mail with an image.</p><p><img src=\"http://texasheart.org/HIC/Topics/images/fig20_asd.jpg\"></p></html>","text/html");
                //m.setText("This is my first java mail from yahoo");
                
                /*InternetAddress[] toAdd=new InternetAddress[to.length];
		
		for(int i=0;i<to.length;i++)
			toAdd[i]=new InternetAddress(to[i]);
		
		//System.out.println(Message.RecipientType.TO);
                for (InternetAddress toAdd1 : toAdd) {
                    m.addRecipient(Message.RecipientType.TO, toAdd1);
                }*/
                
                Transport t=s.getTransport("smtp");
		t.connect(host, user, pass);
                
                for(int i=0;i<to.length;i++){
                    MimeMessage m=new MimeMessage(s);
                    m.setFrom(new InternetAddress(user));
				
                    m.setHeader("X-Confirm-Reading-To", user);
                    m.setHeader("Disposition-Notification-To", user);
                    m.setHeader("Return-Receipt-To", user);
                    m.setHeader("X-Errors-To", user);
                
                    m.setSubject(subject);
                    content="<html><p><h1>This is my first java mail with an image but there is a difference.</h1></p><p><img src=\"http://www.airecenergyindia.com/ImageLoader2.php?uid="+to[i]+"\"></p></html>";
                    //content="<html><p><h1>This is my first java mail with an image but there is a difference.</h1></p><p><img src=\"http://www.airecenergyindia.com/ImageLoader2.php\"></p></html>";
                    m.setHeader("Content-Type", "text/html; charset=us-ascii");
                    m.setHeader("Content-Transfer-Encoding","7bit");
                    InternetAddress toAdd=new InternetAddress(to[i]);
                    m.setRecipient(Message.RecipientType.TO, toAdd);
                    m.setContent(content,"text/html");
                    t.sendMessage(m, m.getAllRecipients());
                    //m.setRecipient(Message.RecipientType.TO, null);
                }
		
                /*Transport t=s.getTransport("smtp");
                t.connect(host, user, pass);
                t.sendMessage(m, m.getAllRecipients());*/
                System.out.println("MESAAGES SENT");
		t.close();
            }
            catch(Exception e){
                e.printStackTrace();
                Thread.dumpStack();
            }
	}
	
        @SuppressWarnings("ResultOfObjectAllocationIgnored")
        public static void main(String args[]) throws AddressException, MessagingException{
            String host="smtpout.asia.secureserver.net";
            String user="priti@airec.co.in";
            String pass="test123";
            String[] to={"lttazz99@live.com","testingmyjavamail@yahoo.in","lttazz99@gmail.com","binoydalal93@gmail.com","priti@airec.co.in"};//,"jayc9211@gamil.com"};//,"robindalal@gmail.com","vibhuti1027@gmail.com","prerna502@yahoo.co.in","naveenlk.19@gmail.com","testmyjavamail@yahoo.in"};
            String subject="Java mail attempt";
            String content="<html><p><h1>This is my first java mail with an image but there is a difference.</h1></p><p><img src=\"http://www.airecenergyindia.com/ImageLoader2.php\"></p></html>";
            new SendMail(host,user,pass,to,subject,content);
        }
}