package msdingfield.estimationcalibrator.estimator;

import java.security.Principal;

import msdingfield.estimationcalibrator.language.Language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estimator")
public class EstimatorController {
	
	@Autowired
	private EstimatorDao dao;
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Estimator currentEstimator(Principal principal) {
		Estimator estimator = dao.loadEstimatorByUsername(principal.getName());
		
		if (estimator == null) {
			estimator = new Estimator(principal.getName());
			dao.createEstimator(estimator);
		}
		
		return estimator;
	}
	
	@RequestMapping(value = "/{username}/language", method = RequestMethod.PUT)
	public void putEstimatorLanguage(@PathVariable String username, @RequestBody Language language) {
		dao.setLanguage(username, language);
	}
}
