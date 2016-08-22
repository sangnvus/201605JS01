/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.utils.HttpUtils;
import vn.edu.fu.veazy.core.model.UserModel;

/**
 *
 * @author Hoang Linh
 */
public class Mail {

    private String senderEmailAddress;
    private String senderPassword;
    private String receiverEmailAddress;
    private String emailSubject;
    private String emailContent;
    private String emailContentType;

    public Mail() {
    }

    public Mail(UserModel user, HttpServletRequest req) {
        StringTokenizer tokenizer = new StringTokenizer(user.getId() + "" + System.currentTimeMillis());
        String sentlink = HttpUtils.getDomainUrl(req) + "?token=" + tokenizer.nextToken();

        this.setSenderEmailAddress(Const.EMAIL_ADDRESS);
        this.setSenderPassword(Const.EMAIL_PASSWORD);
        this.setReceiverEmailAddress(user.getEmail());
        this.setEmailSubject(Const.MAIL_SUBJECT);
        this.setEmailContent(Const.MAIL_CONTENT_PRE
                + user.getUserName()
                + Const.MAIL_CONTENT_PRE2
                + Const.LINK_CONTENT_PRE
                + sentlink
                + Const.LINK_CONTENT_POST
                + Const.MAIL_CONTENT_POST);
        this.setEmailContentType("text/html; charset=utf-8");
    }

    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    public String getReceiverEmailAddress() {
        return receiverEmailAddress;
    }

    public void setReceiverEmailAddress(String receiverEmailAddress) {
        this.receiverEmailAddress = receiverEmailAddress;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getEmailContentType() {
        return emailContentType;
    }

    public void setEmailContentType(String emailContentType) {
        this.emailContentType = emailContentType;
    }

}
