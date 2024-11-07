package com.hola.crawling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MailService {

	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String MY_EMAIL_ADDRESS;

	private static final String HOLA_ADDRESS = "https://holaworld.io";
	
	private static final long DELAY_BETWEEN_EMAILS = 1000;

	public void notifyNewPosts(List<String> ids) {
		ids.forEach(id -> {
			try {
				sendSimpleMailMessage(id);
				Thread.sleep(DELAY_BETWEEN_EMAILS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException("메일 전송 중 인터럽트 발생", e);
			}
		});
	}

	private void sendSimpleMailMessage(String id) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		try {
			simpleMailMessage.setTo(MY_EMAIL_ADDRESS + "@gmail.com");
			simpleMailMessage.setSubject("홀라의 새로운 백엔드 공고글이 등록되었습니다.");
			simpleMailMessage.setText(HOLA_ADDRESS + "/study/" + id);

			javaMailSender.send(simpleMailMessage);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
