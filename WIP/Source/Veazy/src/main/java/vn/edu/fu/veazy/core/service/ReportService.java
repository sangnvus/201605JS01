/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import vn.edu.fu.veazy.core.model.ReportModel;

/**
 *
 * @author Hoang Linh
 */
public interface ReportService {
    
    public void saveReport(ReportModel report) throws Exception;
    public ReportModel findReportById(Integer id) throws Exception;
    public List<ReportModel> findAllReport() throws Exception;
}
