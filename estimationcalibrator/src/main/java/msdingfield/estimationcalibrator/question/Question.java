package msdingfield.estimationcalibrator.question;

import javax.validation.constraints.NotNull;

import msdingfield.estimationcalibrator.language.Language;

public class Question {
	private String questionId;
	
	@NotNull
	private String prose;
	private double exactAnswer;
	private Language language;
	
	@NotNull
	private String ownerUsername;
	
	public Question() {
		
	}
	
	public Question(final Question other) {
		this.questionId = other.questionId;
		this.prose = other.prose;
		this.exactAnswer = other.exactAnswer;
		this.language = new Language(other.language);
		this.ownerUsername = other.ownerUsername;
	}
	
	public String getQuestionId() {
		return questionId;
	}
	public String getProse() {
		return prose;
	}
	public double getExactAnswer() {
		return exactAnswer;
	}
	public Language getLanguage() {
		return language;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public void setProse(String prose) {
		this.prose = prose;
	}
	public void setExactAnswer(double d) {
		this.exactAnswer = d;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", prose=" + prose
				+ ", exactAnswer=" + exactAnswer + ", language=" + language
				+ ", ownerUsername=" + ownerUsername + "]";
	}
}
