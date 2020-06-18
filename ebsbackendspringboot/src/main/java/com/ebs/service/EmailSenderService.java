package com.ebs.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service for sending mails asynchronously
 * 
 * @author Poonamchand Sahu
 *
 */
@Service("emailSenderService")
public class EmailSenderService {

	private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
	private JavaMailSender javaMailSender;
	public static int noOfQuickServiceThreads = 20;

	/**
	 * Creates a threadpool of `noOfQuickServiceThreads` threads
	 */
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	@Autowired
	public EmailSenderService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Async
	public void sendASynchronousMail(SimpleMailMessage email) {
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					javaMailSender.send(email);
				} catch (Exception e) {
					logger.error("Exception occur while send a mail : ", e);
				}
			}
		});
	}
}