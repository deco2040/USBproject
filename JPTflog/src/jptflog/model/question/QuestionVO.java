package jptflog.model.question;

public class QuestionVO {
	private int qidx;
	private int part;
	private String question;
	private String[] choice = new String[4];
	private String answer;
	private String commentary;
	private int difficulty;
	private String weekpoint;
	private String favorites;
	private String responses;
	private String reading;
	
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public int getQidx() {
		return qidx;
	}
	public void setQidx(int qidx) {
		this.qidx = qidx;
	}
	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		this.part = part;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getChoice() {
		return choice;
	}
	public void setChoice(String[] choice) {
		this.choice = choice;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getWeekpoint() {
		return weekpoint;
	}
	public void setWeekpoint(String weekpoint) {
		this.weekpoint = weekpoint;
	}
	public String getFavorites() {
		return favorites;
	}
	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}
	public String getResponses() {
		return responses;
	}
	public void setResponses(String responses) {
		this.responses = responses;
	}
}
