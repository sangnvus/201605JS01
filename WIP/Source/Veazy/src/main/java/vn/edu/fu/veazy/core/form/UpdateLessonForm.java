package vn.edu.fu.veazy.core.form;

public class UpdateLessonForm {
	/**
	 * レッソンのＩＤ
	 */
	private Integer lessonId;
	
	/**
	 * レベルのＩＤ
	 */
	private Integer courseId;
	
	/**
	 * レッソンの表題
	 */
	private String lessonTitle;
	
	/**
	 * レッソンの説明
	 */
	private String description;
	
	/**
	 * レッソンの語彙
	 */
	private String vocabulary;
	
	/**
	 * レッソンの文法
	 */
	private String grammar;
	
	/**
	 * レッソンの読解
	 */
	private String reading;
	
	/**
	 * レッソンの聴解
	 */
	private String listening;
	
	/**
	 * レッソンの練習
	 */
	private String practice;
	
	/**
	 * レッソンの記事
	 */
	private String article;
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getLessonTitle() {
		return lessonTitle;
	}
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVocabulary() {
		return vocabulary;
	}
	public void setVocabulary(String vocabulary) {
		this.vocabulary = vocabulary;
	}
	public String getGrammar() {
		return grammar;
	}
	public void setGrammar(String grammar) {
		this.grammar = grammar;
	}
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public String getListening() {
		return listening;
	}
	public void setListening(String listening) {
		this.listening = listening;
	}
	public String getPractice() {
		return practice;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}

}
