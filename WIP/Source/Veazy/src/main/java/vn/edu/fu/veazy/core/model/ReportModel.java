package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import vn.edu.fu.veazy.core.form.ReportForm;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Report")
public class ReportModel extends BasicModel {

    @Column(name = "senderId")
    private Integer senderId;
    @Column(name = "isRead", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean readFlag;
    @Column(name = "content")
    private String content;
    @Column(name = "lessonId")
    private Integer lessonId;
    @Column(name = "questionId")
    private Integer questionId;

    public ReportModel() {
        super();
    }

    public ReportModel(ReportForm form, Integer senderId, Integer questionId) {
        super();
        this.content = form.getContent();
        this.senderId = senderId;
        this.questionId = questionId;
    }

    public ReportModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag,
            Integer senderId, boolean readFlag, String content, Integer lessonId, Integer questionId) {
        super(id, createDate, updateDate, deleteDate, deleteFlag);
        this.senderId = senderId;
        this.readFlag = readFlag;
        this.content = content;
        this.lessonId = lessonId;
        this.questionId = questionId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public boolean isReadFlag() {
        return readFlag;
    }

    public void setReadFlag(boolean readFlag) {
        this.readFlag = readFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

}
