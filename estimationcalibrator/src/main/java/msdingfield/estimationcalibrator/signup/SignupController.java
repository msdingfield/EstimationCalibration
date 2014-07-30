package msdingfield.estimationcalibrator.signup;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msdingfield.estimationcalibrator.webapp.WebSecurityConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
@RequestMapping("/signup")
public class SignupController {
	private static Logger log = LoggerFactory.getLogger(SignupController.class);
	
	private static final String SIGNUP_VIEW = "signup";
	
	private final WebSecurityConfig security;
	
	@Autowired
	public SignupController(final WebSecurityConfig security) {
		this.security = security;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String signup() {
		System.out.println("SIGNUP");
		return SIGNUP_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void signup(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			SignupCommand creds = gson.fromJson(request.getReader(), SignupCommand.class);
			if (security.addUser(creds)) {
				response.setStatus(204);
			} else {
				response.setStatus(409);
				out.write(gson.toJson(new SignupResponse("Username already exists.")));
			}
		} catch (Exception e) {
			log.error("Error adding new user.", e);
			response.setStatus(500);
			if (out != null) {
				out.write(gson.toJson(new SignupResponse("Internal Error")));
			}
		}
	}

}
