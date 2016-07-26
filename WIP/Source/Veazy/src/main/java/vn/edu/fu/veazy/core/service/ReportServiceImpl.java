/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.ReportModel;

/**
 *
 * @author Hoang Linh
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private GenericDao<ReportModel, Integer> reportDao;

    @Override
    @Transactional
    public void saveReport(ReportModel report) throws Exception {
        try {
            reportDao.save(report);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public ReportModel findReportById(Integer id) throws Exception {
        try {
            ReportModel report = reportDao.findById(id);
            if (report != null) {
                return report;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<ReportModel> findAllReport() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
