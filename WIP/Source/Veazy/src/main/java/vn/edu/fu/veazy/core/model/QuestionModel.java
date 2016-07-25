/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Question`")
public class QuestionModel extends BasicModel {

    @Column(name = "questionCode", nullable = false)
    @SequenceGenerator(name="code_sequence", sequenceName="question_code_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="code_sequence")
    private String questionCode;
    @Column(name = "creatorId", nullable = false)
    private String creatorId;
    @Column(name = "questionAnswerType", columnDefinition="INT DEFAULT 1", nullable = false)
    private Integer questionAnswerType = 1;
    @Column(name = "questionType", columnDefinition="INT DEFAULT 1", nullable = false)
    private Integer questionType = 1;
    @Column(name = "questionSkill", columnDefinition="INT DEFAULT 1", nullable = false)
    private Integer questionSkill = 1;
    @Column(name = "numberOfQuestion", columnDefinition="INT DEFAULT 1", nullable = false)
    // numberOfQuestion = 1 if Singular 
    // >1 if Group(the origin question)
    // =0 if is a question of Group
    private Integer numberOfQuestion;
    @Column(name = "courseid", nullable = false)
    private String courseId;
    @Column(name = "question", nullable = false)
    private String question;
    @OneToMany(mappedBy = "question")
    @Column(name = "listAnswers", nullable = false)
    private List<Answer> listAnswers;
    @ElementCollection
    @Column(name = "content", nullable = true)
    private List<String> content;
    @Column(name = "state", columnDefinition="INT DEFAULT 1", nullable = false)
    private Integer state;
    @Column(name = "attachment", nullable = true)
    private String attachment;

    public QuestionModel() {
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getQuestionAnswerType() {
        return questionAnswerType;
    }

    public void setQuestionAnswerType(Integer questionAnswerType) {
        this.questionAnswerType = questionAnswerType;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionSkill() {
        return questionSkill;
    }

    public void setQuestionSkill(Integer questionSkill) {
        this.questionSkill = questionSkill;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<Answer> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}
