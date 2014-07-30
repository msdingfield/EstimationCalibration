package msdingfield.estimationcalibrator.question;

import java.util.ArrayList;
import java.util.Collection;

public class QuestionList extends ArrayList<Question> {
	private static final long serialVersionUID = 323472944857317557L;

	public QuestionList() {
		
	}

	public QuestionList(int initialCapacity) {
		super(initialCapacity);
	}

	public QuestionList(Collection<Question> c) {
		super(c);
	}

}
