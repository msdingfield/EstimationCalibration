package msdingfield.estimationcalibrator.language;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageController {

	@RequestMapping(method = RequestMethod.GET)
	public LanguageList listLanguages() throws IOException {
		return Languages.allLanguages();
	}
}
