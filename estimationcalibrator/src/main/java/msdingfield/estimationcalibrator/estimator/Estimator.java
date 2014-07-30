package msdingfield.estimationcalibrator.estimator;

import java.util.Locale;

import msdingfield.estimationcalibrator.language.Language;

public class Estimator {
	private String username;
	private Language language;
	private Long nextQuestionId;
	
	public Estimator() {
		this.username = null;
		this.language = new Language(Locale.FRENCH);
		this.nextQuestionId = 0L;
	}
	
	public Estimator(final String username) {
		this.username = username;
		this.language = new Language(Locale.FRENCH);
		this.nextQuestionId = 0L;
	}
	public String getUsername() {
		return username;
	}
	public Language getLanguage() {
		return language;
	}
	public Long getNextQuestionId() {
		return nextQuestionId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public void setNextQuestionId(Long nextQuestionId) {
		this.nextQuestionId = nextQuestionId;
	}
}
