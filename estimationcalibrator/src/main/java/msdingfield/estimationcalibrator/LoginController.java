package msdingfield.estimationcalibrator;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	WebSecurityConfig securityConfig;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(required=false) Boolean signupSuccess, Model model) {
		model.addAttribute("signupSuccess", signupSuccess);
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute Credentials credentials, Model model, HttpServletResponse response) throws Exception {
		if (securityConfig.addUser(credentials)) {
			response.sendRedirect("/login?signupSuccess=true");
		} else {
			model.addAttribute("signupFailed", "duplicate");
		}
		return "signup";
	}

	@RequestMapping(value = "/*")
	public void any(HttpServletResponse response) throws IOException {
		response.sendRedirect("/profile");
	}
	
}
