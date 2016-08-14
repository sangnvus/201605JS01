package vn.edu.fu.veazy.core.response;

public class QuestionsStatsResponse {
    
    private int numberOfQuestions;

    public QuestionsStatsResponse(int numberOfQuestions) {
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
