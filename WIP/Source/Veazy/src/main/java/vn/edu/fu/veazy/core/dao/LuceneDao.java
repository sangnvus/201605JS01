package vn.edu.fu.veazy.core.dao;

import java.util.List;

public interface LuceneDao<T> {
    List<T> luceneModel(String field, String term) throws ClassNotFoundException;
}
