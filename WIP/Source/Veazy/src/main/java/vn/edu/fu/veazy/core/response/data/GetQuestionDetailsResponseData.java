/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.ArrayList;
import java.util.List;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public class GetQuestionDetailsResponseData extends GetQuestionResponseData {

    private Integer numberOfQuestion;
    private List<AnswerResponseData> listAnswerDatas = new ArrayList<>();
    private List<GetQuestionDetailsResponseData> listQuestions = new ArrayList<>();
    private String attachment;

    public GetQuestionDetailsResponseData() {
        super();
    }

    //for singular question
    public GetQuestionDetailsResponseData(QuestionModel question) {
        super(question);
        this.attachment = question.getAttachment();
        this.numberOfQuestion = question.getNumberOfQuestion();
        List<AnswerModel> listAnswers = question.getListAnswers();
        for (AnswerModel answer : listAnswers) {
            AnswerResponseData data = new AnswerResponseData(answer);
            this.listAnswerDatas.add(data);
        }
    }

    //for group question
    public GetQuestionDetailsResponseData(QuestionModel question, List<QuestionModel> subQuestions) {
        this(question);
        for(QuestionModel subQuestion: subQuestions){
            GetQuestionDetailsResponseData data = new GetQuestionDetailsResponseData(subQuestion);
            listQuestions.add(data);
        }
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public List<AnswerResponseData> getListAnswerDatas() {
        return listAnswerDatas;
    }

    public void setListAnswerDatas(List<AnswerResponseData> listAnswerDatas) {
        this.listAnswerDatas = listAnswerDatas;
    }

    public List<GetQuestionDetailsResponseData> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<GetQuestionDetailsResponseData> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}
