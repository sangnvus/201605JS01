package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

public interface LuceneService {
    List<QuestionModel> searchQuestion(String text) throws ClassNotFoundException;
    List<LessonVersionModel> searchLesson(String text);
}
