import java.util.*;

public class ScoreList 
{
	private ArrayList<Score> scoreList; //Indexed LinkedList
	private double totalScorePossible;
	private double maxScore;
	private double minScore;
	private double Athreshold;
	private double Bthreshold;
	private double Cthreshold;
	private double Dthreshold;
	
	/**
	 * Constructor for a list of student scores. Sets score thresholds to default values.
	 * @param totalScorePossible The total score possible on the assignment being analyzed.
	 */
	public ScoreList(double totalScorePossible) 
	{
		scoreList = new ArrayList<Score>();
		this.totalScorePossible = totalScorePossible;
		maxScore = Double.MIN_VALUE;
		minScore = Double.MAX_VALUE;
		Athreshold = 90;
		Bthreshold = 80;
		Cthreshold = 70;
		Dthreshold = 60;
	}
	
	/**
	 * This method allows the user to change the letter grade thresholds.
	 * @param newA New minimum score required for an A.
	 * @param newB New minimum score required for an B.
	 * @param newC New minimum score required for an C.
	 * @param newD New minimum score required for an D.
	 */
	public void changeGradeThresholds(double newA, double newB, double newC, double newD) {
		Athreshold = newA;
		Bthreshold = newB;
		Cthreshold = newC;
		Dthreshold = newD;
		for (int i = 0; i<getNumberOfScores(); i++) {
			double percentScore = scoreList.get(i).getPercentScore();
			double percentScoreBasedOnHighestScore = scoreList.get(i).getPercentScoreBasedOnHighestScore();
			String letterGrade = getLetterGrade(percentScore);
			String letterGradeBasedOnHighestScore = getLetterGrade(percentScoreBasedOnHighestScore);
			scoreList.get(i).setLetterGrade(letterGrade);
			scoreList.get(i).setLetterGradeBasedOnHighestScore(letterGradeBasedOnHighestScore);
		}
	}
	
	/**
	 * This method is invoked to add a new score.
	 * @param rawScore The raw score the student received on the assignment.
	 */
	public void add(double rawScore)
	{
		if (rawScore > maxScore) {
			maxScore  = rawScore;
			reMax();
		}
		if (rawScore < minScore)
			minScore = rawScore;
		
		double percentScore = rawScore / totalScorePossible *100.0;
		double percentScoreBasedOnHighestScore= rawScore / maxScore;
		String letterGrade = getLetterGrade(percentScore);
		String letterGradeBasedOnHighestScore = getLetterGrade(percentScoreBasedOnHighestScore);
		Score temp  = new Score(rawScore, percentScore, percentScoreBasedOnHighestScore, letterGrade, letterGradeBasedOnHighestScore);
		scoreList.add(temp);
	}
	
	/**
	 * This method is invoked to delete a score from the list.
	 * @param index The index of the score to be deleted.
	 */
	public void delete(int index) {
		double deletedScore = scoreList.get(index).getRawScore();
		scoreList.remove(index);
		if (deletedScore == minScore)
			findMinScore();
		if (deletedScore == maxScore) {
			findMaxScore();
			reMax();
		}
	}
	
	/**
	 * This method allow the changing / setting of a score.
	 * @param rawScore Raw score  received by the student
	 * @param index The index of the  score to be changed.
	 */
	public void setScore(double rawScore, int index) {
		boolean mustFindNewMax = false;
		if  (scoreList.get(index).getRawScore()==maxScore)
			mustFindNewMax = true;
		
		if (rawScore > maxScore) {
			maxScore  = rawScore;
			reMax();
		}
		if (rawScore < minScore)
			minScore = rawScore;
		
		double percentScore = rawScore / totalScorePossible *100.0;
		double percentScoreBasedOnHighestScore= rawScore / maxScore;
		String letterGrade = getLetterGrade(percentScore);
		String letterGradeBasedOnHighestScore = getLetterGrade(percentScoreBasedOnHighestScore);
		Score temp  = new Score(rawScore, percentScore, percentScoreBasedOnHighestScore, letterGrade, letterGradeBasedOnHighestScore);
		scoreList.set(index, temp);
		if (mustFindNewMax) {
			findMaxScore();
			reMax();
		}
	}
	
	/**
	 * This method will ONLY be invoked if the minimum element is deleted. It finds a new minimum score from scratch.
	 */
	private void findMinScore() {
		minScore = Integer.MAX_VALUE;
		for (int i = 0; i<getNumberOfScores(); i++) {
			if(scoreList.get(i).getRawScore()<minScore)
				minScore = scoreList.get(i).getRawScore();
		}
	}

	/**
	 * This method will ONLY be invoked if the maximum element is deleted or changed. It finds a new maximum score from scratch.
	 */
	private void findMaxScore() {
		maxScore = Integer.MIN_VALUE;
		for (int i = 0; i<getNumberOfScores(); i++) {
			if(scoreList.get(i).getRawScore()>maxScore)
				maxScore = scoreList.get(i).getRawScore();
		}
	}
	
	public double findMedianScore() {
		//NEEDS IMPLEMENTATION
	}
	
	public double findMedianPercentScore() {
		//NEEDS IMPLEMENTATION
	}
	
	public double findMedianPercentScoreBasedOnHighestScore() {
		//NEEDS IMPLEMENTATION
	}
	
	/**
	 * This method is called whenever the maximum score changes, whether by addition of new score, changing a score, or deleting a score. It changes the values of all scores in the list that depend on the max score.
	 */
	public void reMax() {
		for(int i = 0; i<getNumberOfScores(); i++) {
			double rawScore = scoreList.get(i).getRawScore();
			double percentScoreBasedOnHighestScore = rawScore / maxScore * 100.0;
			scoreList.get(i).setPercentScoreBasedOnHighestScore(percentScoreBasedOnHighestScore);
		}
	}

	/**
	 * This method finds a student's letter grade based on percent score and current grade thresholds.
	 * @param percentScore Double value for student's percentage score.
	 * @return String value for student's letter grade
	 */
	private String getLetterGrade(double percentScore) 
	{
		percentScore = Math.round(percentScore);
		if (percentScore >= Athreshold)
			return "A";
		else if (percentScore >= Bthreshold)
			return "B";
		else if (percentScore >= Cthreshold)
			return "C";
		else if (percentScore >= Dthreshold)
			return "D";
		else
			return "E";
	}
	
	/**
	 * Getter for total number of student scores.
	 * @return Integer number of scores.
	 */
	public int getNumberOfScores() {
		return scoreList.size();
	}
	
	/**
	 * Getter for minimum score.
	 * @return Double value for lowest student  score.
	 */
	public double getMinScore() {
		return minScore;
	}
	
	/**
	 * Getter for maximum score.
	 * @return Double value for highest student  score.
	 */
	public double getMaxScore() {
		return maxScore;
	}

	/**
	 * Getter for minimum percentage score.
	 * @return Double value for lowest student percentage score.
	 */
	public double getMinScorePercentage() {
		return minScore/totalScorePossible *100;
	}
	
	/**
	 * Getter for maximum percentage score.
	 * @return Double value for highest student percentage score.
	 */
	public double getMaxScorePercentage() {
		return maxScore/totalScorePossible*100;
	}

	/**
	 * Getter for minimum percentage score using highest score as denominator.
	 * @return Double value for lowest student percentage score using highest score as denominator.
	 */
	public double getMinScorePercentageBasedOnHighestScore() {
		return minScore/maxScore *100;
	}
	
	/**
	 * Getter for maximum percentage score using highest score as denominator.
	 * @return Double value for highest student percentage score using highest score as denominator.
	 */
	public double getMaxScorePercentageBasedOnHighestScore() {
		return maxScore/maxScore *100;
	}
	
	/**
	 * Getter for mean raw score.
	 * @return Double value for mean raw student score.
	 */
	public double getMeanScore() {
		int count = 0;
		double sum = 0;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			count++;
			sum+=scoreList.get(i).getRawScore();
		}
		return sum/count;
	}
	
	/**
	 * Getter for mean percent score.
	 * @return Double value for mean percent student score.
	 */
	public double getMeanPercentScore()
	{
		int count = 0;
		double sum = 0;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			count++;
			sum+=scoreList.get(i).getPercentScore();
		}
		return sum/count;
	}
	
	/**
	 * Getter for mean percent score using highest score as denominator.
	 * @return Double value for mean percent student score using highest score as denominator.
	 */
	public double getMeanPercentScoreBasedOnHighestScore()
	{
		int count = 0;
		double sum = 0;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			count++;
			sum+=scoreList.get(i).getPercentScoreBasedOnHighestScore();
		}
		return sum/count;
	}
	
	/**
	 * Getter for number of student's with an A.
	 * @return Integer number of student's that receive an A.
	 */
	public int getNumberofA() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGrade().equals("A"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an B.
	 * @return Integer number of student's that receive an B.
	 */
	public int getNumberofB() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGrade().equals("B"))
				count++;
		}
		return count;
	}

	/**
	 * Getter for number of student's with an C.
	 * @return Integer number of student's that receive an C.
	 */
	public int getNumberofC() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGrade().equals("C"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an D.
	 * @return Integer number of student's that receive an D.
	 */
	public int getNumberofD() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGrade().equals("D"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an E.
	 * @return Integer number of student's that receive an E.
	 */
	public int getNumberofE() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGrade().equals("E"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an A if highest score is used as denominator.
	 * @return Integer number of student's that receive an A if the highest score is used as denominator.
	 */
	public int getNumberofABasedOnHighestScore() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGradeBasedOnHighestScore().equals("A"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an B if highest score is used as denominator.
	 * @return Integer number of student's that receive an B if the highest score is used as denominator.
	 */
	public int getNumberofBBasedOnHighestScore() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGradeBasedOnHighestScore().equals("B"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an C if highest score is used as denominator.
	 * @return Integer number of student's that receive an C if the highest score is used as denominator.
	 */
	public int getNumberofCBasedOnHighestScore() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGradeBasedOnHighestScore().equals("C"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an D if highest score is used as denominator.
	 * @return Integer number of student's that receive an D if the highest score is used as denominator.
	 */
	public int getNumberofDBasedOnHighestScore() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGradeBasedOnHighestScore().equals("D"))
				count++;
		}
		return count;
	}
	
	/**
	 * Getter for number of student's with an E if highest score is used as denominator.
	 * @return Integer number of student's that receive an E if the highest score is used as denominator.
	 */
	public int getNumberofEBasedOnHighestScore() {
		int count = 0;;
		for (int i = 0; i<getNumberOfScores(); i++) 
		{
			if (scoreList.get(i).getLetterGradeBasedOnHighestScore().equals("E"))
				count++;
		}
		return count;
	}
	
}
