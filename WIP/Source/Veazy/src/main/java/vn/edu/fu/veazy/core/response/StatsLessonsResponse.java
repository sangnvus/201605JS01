package vn.edu.fu.veazy.core.response;

public class StatsLessonsResponse {
    
    private int numberOfLessons;

    public StatsLessonsResponse(int numberOfQuestions) {
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
