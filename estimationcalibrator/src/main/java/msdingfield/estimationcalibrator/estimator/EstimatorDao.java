package msdingfield.estimationcalibrator.estimator;

import msdingfield.estimationcalibrator.language.Language;

public interface EstimatorDao {
	void createEstimator(Estimator estimator);
	
	Estimator loadEstimatorByUsername(String name);

	void setLanguage(String username, Language language);
}
