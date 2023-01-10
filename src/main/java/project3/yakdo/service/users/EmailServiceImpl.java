package project3.yakdo.service.users;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
    JavaMailSender emailSender;
	
	public static String ePw = createKey();
	 
    private MimeMessage createMessage(String to)throws Exception{
    	ePw = createKey();
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();
 
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("약도藥道 - 인증번호입니다.");//제목
 
        String msgg="";
        msgg+= "<h1> 약도藥道에서 이메일 인증용 번호를 보내드립니다. </h1><br>";
        msgg+= "<p>아래 코드를 입력해주세요<p><br>";
        msgg+= "<p>감사합니다.<p><br>";
        msgg+= "<div align='center' style='border:1px solid gray; font-family:verdana';>";
        msgg+= "<h3 style='color:#315290;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>" + ePw + "</strong><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("int3jo@gmail.com","약도藥道"));//보내는 사람
 
        return message;
    }
 
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
        	key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

	@Override
	public String sendSimpleMessage(String to) throws Exception {
		MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return String.valueOf((((Integer.parseInt(ePw)*2)+2)*2)+2);
	}

}
