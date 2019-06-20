
public class Score 
{
	private double rawScore;
	private double percentScore;
	private double percentScoreBasedOnHighestScore;
	private String letterGrade;
	private String letterGradeBasedOnHighestScore;
	
	/**
	 * This is the constructor for the Score class.
	 * @param rawScore The raw grade the student received on the assignment.
	 * @param percentScore The percentage score the student received on the assignment.
	 * @param percentScoreBasedOnHighestScore The percentage score the student receives if the denominator is changed to the highest score.
	 * @param letterGrade The letter grade the student receives based on the percentage score.
	 * @param letterGradeBasedOnHighestScore The letter grade the student receives if the denominator is changed to the highest score.
	 */
	public Score(double rawScore, double percentScore, double percentScoreBasedOnHighestScore, String letterGrade, String letterGradeBasedOnHighestScore) {
		this.rawScore = rawScore;
		this.percentScore = percentScore;
		this.percentScoreBasedOnHighestScore = percentScoreBasedOnHighestScore;
		this.letterGrade = letterGrade;
		this.letterGradeBasedOnHighestScore = letterGradeBasedOnHighestScore;
	}
	
	/**
	 * Setter for a score's corresponding letter grade. This method is invoked ONLY if score thresholds are changed.
	 * @param letterGrade String representing student grade.
	 */
	void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	
	/**
	 * Setter for a score's corresponding letter grade if the denominator is changed to the highest score. This method is invoked ONLY if score thresholds are changed.
	 * @param letterGradeBasedOnHighestScore String representing student grade.
	 */
	void setLetterGradeBasedOnHighestScore(String letterGradeBasedOnHighestScore) {
		this.letterGradeBasedOnHighestScore = letterGradeBasedOnHighestScore;
	}
	
	/**
	 * Setter for a score's percentage if the denominator is changed to the highest score. This method is invoked ONLY if the maximum score changes.
	 * @param percentScoreBasedOnHighestScore Double value representing score percentage.
	 */
	void setPercentScoreBasedOnHighestScore(double percentScoreBasedOnHighestScore) {
		this.percentScoreBasedOnHighestScore  = percentScoreBasedOnHighestScore;
	}
	
	/**
	 * Getter for a student's raw score.
	 * @return Double value for a student's raw score.
	 */
	public double getRawScore() {
		return rawScore;
	}
	
	/**
	 * Getter for a student's percentage score.
	 * @return Double value for a student's percentage score.
	 */
	public double getPercentScore() {
		return percentScore;
	}
	
	/**
	 * Getter for a student's percentage score if the denominator is changed to the highest score.
	 * @return Double value for a student's percentage score if the denominator is changed to the highest score.
	 */
	public double getPercentScoreBasedOnHighestScore() {
		return percentScoreBasedOnHighestScore;
	}
	
	/**
	 * Getter for a student's letter grade.
	 * @return String value representing the student's letter grade.
	 */
	public String getLetterGrade() {
		return letterGrade;
	}
	
	/**
	 * Getter for a student's letter grade if the denominator is changed to the highest score.
	 * @return String value representing the student's letter grade letter grade if the denominator is changed to the highest score.
	 */
	public String getLetterGradeBasedOnHighestScore() {
		return letterGradeBasedOnHighestScore;
	}
	
}
