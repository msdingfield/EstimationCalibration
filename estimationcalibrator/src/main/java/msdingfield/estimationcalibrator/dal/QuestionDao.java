package msdingfield.estimationcalibrator.dal;

import java.util.List;

import msdingfield.estimationcalibrator.model.Question;

public interface QuestionDao {
	List<Question> getQuestionsForOwner(final String username);
}
