package com.socgen.stockmarketcharting;


import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.stockmarketcharting.dao.PlatformUserRepository;
import com.socgen.stockmarketcharting.entity.PlatformUser;

@RestController
public class UserController {
	@Autowired
	PlatformUserRepository userrepo;
	@Autowired 
	EntityManager em;

	@CrossOrigin(origins ="http://localhost:3000")

	@RequestMapping(value = "/setuserapi",method=RequestMethod.POST)
	
	public String Stringreactuserapi(@RequestBody PlatformUser user) throws AddressException, MessagingException {	
	
		PlatformUser usrsaved = userrepo.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "UserController");
		headers.add("Access-Control-Allow-Origin", "*");
		sendemail(user.getId()) ;
		
		return user.toString();
	}

	public void sendemail(Long userid) throws AddressException, MessagingException {

		PlatformUser user = userrepo.getById(userid);

		final String username = "nishant4755@gmail.com";
		final String password = "nishantagrawal_2303";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = (Message) new MimeMessage(session);
			((MimeMessage) message).setFrom(new InternetAddress("nishant4755@gmail.com"));
			// message.setRecipients(
			// Message.RecipientType.TO,
			// InternetAddress.parse("sftrainerram@gmail.com")
			// );
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			((MimeMessage) message).setSubject("USer confirmation email");
			// message.setText("Dear Mail Crawler,"
			// + "\n\n Please do not spam my email!");
			((MimeMessage) message).setContent(
					"<h1><a href =\"http://127.0.0.1:8080/confirmuser/" + userid + "/\"> Click to confirm </a></h1>",
					"text/html");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/confirmuser/{userid}", method = RequestMethod.GET)
	public String welcomepage(@PathVariable Long userid) {
		Optional<PlatformUser> userlist = userrepo.findById(userid);
		// do a null check for home work
		PlatformUser usr = new PlatformUser();
		usr = userrepo.getById(userid);
		usr.setConfirmed(true);
		userrepo.save(usr);
		return "User confirmed" + usr.getName();
	}
	@RequestMapping(value="/finduser",method=RequestMethod.POST)
	public PlatformUser finduser(@RequestBody Map<String,String>text) {
		Query query = em.createNamedQuery("PlatformUser.finduserbyemail");
		query.setParameter("email", text.get("email"));
		query.setParameter("password", text.get("password"));
		PlatformUser c = (PlatformUser) query.getSingleResult();
		return c;
	}

}
