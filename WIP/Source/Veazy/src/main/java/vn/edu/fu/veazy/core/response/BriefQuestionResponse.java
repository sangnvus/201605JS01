package vn.edu.fu.veazy.core.response;

import java.util.ArrayList;
import java.util.List;

public class BriefQuestionResponse {
    
    private Integer questionId;
    private String question;
    private List<String> answer = new ArrayList<>();
    private List<BriefQuestionResponse> listQuestions = new ArrayList<>();
    private String attachment;
    
    public BriefQuestionResponse(Integer questionId, String question, List<String> answer) {
        super();
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
    }
    
    public BriefQuestionResponse(Integer questionId, String question, String attachment,
            List<BriefQuestionResponse> listQuestions) {
        super();
        this.questionId = questionId;
        this.question = question;
        this.listQuestions = listQuestions;
        this.attachment = attachment;
    }

    public BriefQuestionResponse(Integer questionId, String question, List<String> answer, String attachment) {
        super();
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.attachment = attachment;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<BriefQuestionResponse> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<BriefQuestionResponse> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public List<String> getAnswer() {
        return answer;
    }
    
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    
}
