package vn.edu.fu.veazy.core.response;

public class LessonsStatsResponse {
    
    private int numberOfLessons;

    public LessonsStatsResponse(int numberOfQuestions) {
        super();
        this.numberOfLessons = numberOfQuestions;
    }

    public int getNumberOfLessons() {
        return numberOfLessons;
    }

    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

}
