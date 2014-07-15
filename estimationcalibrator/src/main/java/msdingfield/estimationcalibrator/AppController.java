package msdingfield.estimationcalibrator;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/profile")
	public void test(@ModelAttribute UserProfile user, Principal principal) {
		user.setUsername(principal.getName());
	}
}
