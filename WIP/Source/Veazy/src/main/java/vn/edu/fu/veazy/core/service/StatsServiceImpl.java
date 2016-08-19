package vn.edu.fu.veazy.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.CourseModel;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.response.StatsCourseAvgResponse;
import vn.edu.fu.veazy.core.response.StatsLastExamResponse;
import vn.edu.fu.veazy.core.response.StatsSkillAvgResponse;

@Service
public class StatsServiceImpl implements StatsService {
    
    @Autowired
    private GenericDao<ExamModel, Integer> examDao;
    @Autowired
    private GenericDao<CourseModel, Integer> courseDao;

    @Override
    @Transactional
    public StatsSkillAvgResponse getSkillAvg(Integer userId) throws Exception {
        StatsSkillAvgResponse result = new StatsSkillAvgResponse();
        ExamModel e = new ExamModel();
        e.setUserId(userId);
        e.setFinishState(true);
        
        Double avg = 0d;
        e.setQuestionSkill(Const.QUESTIONSKILL_LISTENING);
        List<ExamModel> listExam = examDao.findByExample(e);
        if (listExam != null && listExam.size() > 0) {
            Double sum = 0d;
            for (ExamModel exam : listExam) {
                sum += exam.getResult();
            }
            avg = sum / listExam.size();
        }
        result.setListening(avg);
        listExam.clear();
        
        avg = 0d;
        e.setQuestionSkill(Const.QUESTIONSKILL_VOCABULARY);
        listExam = examDao.findByExample(e);
        if (listExam != null && listExam.size() > 0) {
            Double sum = 0d;
            for (ExamModel exam : listExam) {
                sum += exam.getResult();
            }
            avg = sum / listExam.size();
        }
        result.setVocabulary(avg);
        listExam.clear();
        
        avg = 0d;
        e.setQuestionSkill(Const.QUESTIONSKILL_READING);
        listExam = examDao.findByExample(e);
        if (listExam != null && listExam.size() > 0) {
            Double sum = 0d;
            for (ExamModel exam : listExam) {
                sum += exam.getResult();
            }
            avg = sum / listExam.size();
        }
        result.setReading(avg);
        listExam.clear();
        
        avg = 0d;
        e.setQuestionSkill(Const.QUESTIONSKILL_GRAMMAR);
        listExam = examDao.findByExample(e);
        if (listExam != null && listExam.size() > 0) {
            Double sum = 0d;
            for (ExamModel exam : listExam) {
                sum += exam.getResult();
            }
            avg = sum / listExam.size();
        }
        result.setGrammar(avg);
        listExam.clear();
        
        return result;
    }

    @Override
    @Transactional
    public List<StatsLastExamResponse> getLastExamResult(Integer userId, Integer number) throws Exception {
        List<StatsLastExamResponse> result = new ArrayList<>();
        ExamModel e = new ExamModel();
        e.setUserId(userId);
        List<ExamModel> listExam = examDao.findByExample(e);
        if (listExam != null && listExam.size() > 0) {
            listExam = listExam.subList(listExam.size() - number, listExam.size());
            for (ExamModel exam : listExam) {
                result.add(new StatsLastExamResponse(exam.getResult()));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<StatsCourseAvgResponse> getCourseAvg(Integer userId) throws Exception {
        List<StatsCourseAvgResponse> result = new ArrayList<>();
        List<CourseModel> courses = courseDao.getAll();
        if (courses != null && courses.size() > 0) {
            for (CourseModel c : courses) {
                ExamModel e = new ExamModel();
                e.setUserId(userId);
                e.setCourseId(c.getId());
                List<ExamModel> listExam = examDao.findByExample(e);
                Double avg = 0d;
                if (listExam != null && listExam.size() > 0) {
                    Double sum = 0d;
                    for (ExamModel exam : listExam) {
                        sum += exam.getResult();
                    }
                    avg = sum / listExam.size();
                }
                result.add(new StatsCourseAvgResponse(c.getId(), avg));
            }
        }
        return result;
    }

}
