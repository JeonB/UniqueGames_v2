package com.uniqueGames.service;


import com.uniqueGames.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Component
public class MailSendService {

	private JavaMailSender mailSender;
	private Environment environment;
	@Value("${email-address}")
	private String EMAIL_ADDRESS;

	@Autowired
	public MailSendService(JavaMailSender javaMailSender, Environment environment) {
		this.mailSender = javaMailSender;
		this.environment = environment;
	}

	private int authNumber;

	public void makeRandomNumber() {

		Random r = new Random();
		int CheckNum = r.nextInt(888888) + 111111;
		System.out.println("checkNum="+ CheckNum);
		authNumber = CheckNum;
	}

	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = EMAIL_ADDRESS;
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" +
                "인증 번호는 " + authNumber + "입니다." +
                "<br>" +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        mailSend(setFrom, toMail, title, content);
		System.out.println("인증번호="+authNumber);
        return Integer.toString(authNumber);
	}

	public String reportEmail(Comment comment) {
		String setFrom = "uniquegames@uniquegames.com";
		String toMail = environment.getProperty("EMAIL_COMMENT_TO_ADDRESS");
		String title = "신고 내역";
		String content =
				"댓글 번호 : " + comment.getId() +
						"<br><br>" +
						"댓글 내용 : " + comment.getCommentContent() +
						"<br>" +
						"신고 사유 : " + comment.getReason() +
						"<br>" +
						"신고자 : " + comment.getMId();
		mailSend(setFrom, toMail, title, content);
		return "Email Send Success";
	}

	public void mailSend(String setFrom, String toMail, String title, String content) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}


}
