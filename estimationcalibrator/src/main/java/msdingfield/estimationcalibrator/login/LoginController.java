package msdingfield.estimationcalibrator.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private static final String LOGIN_VIEW = "login";

	@RequestMapping(method = RequestMethod.GET)
	public String login(
			@RequestParam(required=false, value="success") String success,
			@RequestParam(required=false, value="error") String error,
			Model model) {
		model.addAttribute("success", success != null);
		model.addAttribute("error", error != null);
		return LOGIN_VIEW;
	}

}
