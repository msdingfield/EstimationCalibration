package msdingfield.estimationcalibrator.question;

import java.util.List;

public interface QuestionDao {
	List<Question> getQuestionsForOwner(final String username);

	Question save(Question question);

	void remove(String questionId);
}
