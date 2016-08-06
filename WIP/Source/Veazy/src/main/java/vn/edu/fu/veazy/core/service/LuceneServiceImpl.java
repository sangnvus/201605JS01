package vn.edu.fu.veazy.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.LuceneDao;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private LuceneDao<QuestionModel> luceneQuestion;
    @Autowired
    private LuceneDao<AnswerModel> luceneQuestionAnswer;
    @Autowired
    private LuceneDao<LessonVersionModel> luceneLesson;

    @Transactional
    @Override
    public List<QuestionModel> searchQuestion(String text) throws ClassNotFoundException {
        List<QuestionModel> list = luceneQuestion.luceneModel("question", text);
        List<AnswerModel> listAns = luceneQuestionAnswer.luceneModel("answer", text);
        for (AnswerModel ans : listAns) {
            QuestionModel model = ans.getQuestion();
            if (model != null && !list.contains(model)) {
                list.add(model);
            }
        }
        return list;
    }

    @Transactional
    @Override
    public List<LessonVersionModel> searchLesson(String text) {
        // TODO Auto-generated method stub
        return null;
    }

}
