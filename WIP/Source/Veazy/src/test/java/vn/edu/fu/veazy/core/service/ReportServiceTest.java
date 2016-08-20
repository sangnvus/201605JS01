/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;
/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
public class ReportServiceTest {
    
    private ReportModel report;
    private UserModel sender;
    private UserModel receiver;
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
    private Integer reportId;
    private Integer senderId;
    private Integer receiverId;

    private ReportModel setUpReport(String content) throws Exception {
        ReportModel report = new ReportModel();
        report.setContent(content);
        report.setSenderId(senderId);
        report.setReceiverId(receiverId);
        report.setDeleteFlag(false);
        report.setReadFlag(false);
        reportService.saveReport(report);
        return report;
    }

    public UserModel setUpUser(String userName) throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUsername(userName);
        form.setEmail(userName);
        form.setPassword(userName);
        userService.saveUser(form);
        UserModel user = userService.findUserByUsername(userName);
        return user;
    }
    @Before
    public void setUp() throws Exception {
        sender = setUpUser("user1");
        senderId = sender.getId();
        receiver = setUpUser("user2");
        receiverId = receiver.getId();

        report = setUpReport("report1");
        reportId = report.getId();
    }

    @After
    public void tearDown() {
        try {
            userService.delete(receiver);
            userService.delete(sender);
			reportService.deleteReport(reportId);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testSaveReport() throws Exception {
        reportService.saveReport(report);
        ReportModel report = reportService.getReport(reportId);
        Assert.assertNotNull(report);
    }

    @Test(expected=Exception.class)
    public void testSaveReport2() throws Exception {
        reportService.saveReport(null);
    }

    @Test
    public void testGetReport() throws Exception {
        ReportModel report = reportService.getReport(reportId);
        Assert.assertNotNull(report);
    }

    @Test
    public void testGetReport2() throws Exception {
        ReportModel report = reportService.getReport(-1);
        Assert.assertNull(report);
    }

    @Test
    public void testGetReport3() throws Exception {
        ReportModel report = reportService.getReport(null);
        Assert.assertNull(report);
    }

    @Test
    public void testGetAllReport() throws Exception {
        List<ReportModel> reports = reportService.getAllReports(receiverId);
        Assert.assertNotNull(reports);
        Assert.assertNotEquals(0, reports.size());
    }
    

    @Test
    public void testGetAllReport2() throws Exception {
        List<ReportModel> reports = reportService.getAllReports(-1);
        Assert.assertNull(reports);
    }

    @Test
    public void testGetAllReport3() throws Exception {
        List<ReportModel> reports = reportService.getAllReports(null);
        Assert.assertNull(reports);
    }

    @Test
    public void testReadReport() throws Exception {
        reportService.readReport(reportId);
        ReportModel report = reportService.getReport(reportId);
        Assert.assertEquals(true, report.isReadFlag());
    }

//    @Test
//    public void testReadReport2() throws Exception {
//        reportService.readReport(-1);
//    }
//
//    @Test
//    public void testReadReport3() throws Exception {
//        reportService.readReport(null);
//    }

    @Test
    public void testDeleteReport() throws Exception {
        reportService.deleteReport(reportId);
        ReportModel report = reportService.getReport(reportId);
        Assert.assertNull(report);
    }

//    @Test
//    public void testDeleteReport2() throws Exception {
//        reportService.deleteReport(null);
//    }
    
}
