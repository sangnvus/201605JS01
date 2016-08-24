/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.model.ReportModel;

/**
 *
 * @author Hoang Linh
 */
public class GetReportDataResponse {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private Boolean readFlag;
    private String content;
    private Integer lessonId;
    private Integer questionId;
    private String lesonTitle;
    private String username;
    private Long createDate;

    public GetReportDataResponse() {
    }

    public GetReportDataResponse(ReportModel report) {
        this.id = report.getId();
        this.senderId = report.getSenderId();
        this.receiverId = report.getReceiverId();
        this.readFlag = report.isReadFlag();
        this.content = report.getContent();
        this.lessonId = report.getLessonId();
        this.questionId = report.getQuestionId();
        this.createDate = report.getCreateDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Boolean getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Boolean readFlag) {
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

    public String getLesonTitle() {
        return lesonTitle;
    }

    public void setLesonTitle(String lesonTitle) {
        this.lesonTitle = lesonTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
    
    
}
