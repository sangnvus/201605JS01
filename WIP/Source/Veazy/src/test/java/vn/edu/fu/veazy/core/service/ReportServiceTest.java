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
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
@Transactional
public class ReportServiceTest {
    
    @Autowired
    private ReportService reportService;
    @Autowired
    private GenericDao<ReportModel, Integer> reportDao;
    @Autowired
    private GenericDao<UserModel, Integer> userDao;

    private ReportModel initReport;
    private UserModel sender;
    private UserModel receiver;
    
    @Before
    public void setUp() throws Exception {
        sender = new UserModel("sender@gmail.com", "sender", "123456",
                System.currentTimeMillis());
        userDao.save(sender);
        receiver = new UserModel("receiver@gmail.com", "receiver", "123456",
                System.currentTimeMillis());
        userDao.save(receiver);
        
        initReport = new ReportModel();
        initReport.setContent("Content");
        initReport.setSenderId(sender.getId());
        initReport.setReceiverId(receiver.getId());
        initReport.setDeleteFlag(false);
        initReport.setReadFlag(false);
        reportDao.save(initReport);
    }

    @After
    public void tearDown() {
        try {
            if (sender != null) userDao.delete(sender);
            if (receiver != null) userDao.delete(receiver);
            List<ReportModel> listReport = reportDao.getAll();
            if (listReport != null && listReport.size() > 0) {
                for (ReportModel m : listReport) {
                    reportDao.delete(m);
                }
            }
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testSaveReport() throws Exception {
        ReportModel m = new ReportModel();
        m.setContent("sfdsfsdfwe");
        m.setSenderId(sender.getId());
        m.setReceiverId(receiver.getId());
        m.setDeleteFlag(false);
        m.setReadFlag(false);
        reportService.saveReport(m);
        Assert.assertNotNull(m.getId());
    }

    @Test(expected=Exception.class)
    public void testSaveReport2() throws Exception {
        reportService.saveReport(null);
    }

    @Test
    public void testGetReportById() throws Exception {
        ReportModel report = reportService.getReport(initReport.getId());
        Assert.assertNotNull(report);
    }

    @Test
    public void testGetReportById2() throws Exception {
        ReportModel report = reportService.getReport(-1);
        Assert.assertNull(report);
    }

    @Test(expected = Exception.class)
    public void testGetReportById3() throws Exception {
        ReportModel report = reportService.getReport(null);
        Assert.assertNull(report);
    }

    @Test
    public void testGetAllReport() throws Exception {
        List<ReportModel> reports = reportService.getAllReports(receiver.getId());
        Assert.assertNotNull(reports);
        Assert.assertTrue(reports.size() > 0);
    }
    
    @Test
    public void testGetAllReport2() throws Exception {
        List<ReportModel> reports = reportService.getAllReports(-1);
        Assert.assertNotNull(reports);
        Assert.assertTrue(reports.size() == 0);
    }

    @Test
    public void testGetAllReport3() throws Exception {
        List<ReportModel> reports = reportService.getAllReports(null);
        Assert.assertNotNull(reports);
        Assert.assertTrue(reports.size() > 0);
    }

    @Test
    public void testReadReport() throws Exception {
        reportService.readReport(initReport.getId());
        Assert.assertTrue(initReport.isReadFlag());
    }

    @Test(expected = Exception.class)
    public void testReadReport2() throws Exception {
        reportService.readReport(-1);
    }

    @Test
    public void testDeleteReport() throws Exception {
        reportService.deleteReport(receiver.getId(), initReport.getId());
        Assert.assertTrue(initReport.isDeleteFlag());
    }
    
}