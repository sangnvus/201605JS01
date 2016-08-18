package vn.edu.fu.veazy.core.response;

public class StatsQuestionsResponse {
    
    private int numberOfQuestions;

    public StatsQuestionsResponse(int numberOfQuestions) {
        super();
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

}
