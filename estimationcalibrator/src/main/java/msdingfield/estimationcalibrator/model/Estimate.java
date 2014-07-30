package msdingfield.estimationcalibrator.model;

public class Estimate {
	private String estimatorUsername;
	private Long questionId;
	private int lowerBound;
	private int upperBound;
	public String getEstimatorUsername() {
		return estimatorUsername;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setEstimatorUsername(String estimatorUsername) {
		this.estimatorUsername = estimatorUsername;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
}
