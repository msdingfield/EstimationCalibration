package msdingfield.estimationcalibrator.question;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping(value="/question", headers="Accept=application/json", produces="application/json")
public class QuestionRestController {

	@Autowired
	private QuestionDao dao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Question> get(@RequestParam String username) {
		return dao.getQuestionsForOwner(username);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Question put(@RequestBody Question question) {
		return dao.save(question);
	}
	
	@RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE)
	public void del(@PathVariable String questionId) {
		dao.remove(questionId);
	}
}
