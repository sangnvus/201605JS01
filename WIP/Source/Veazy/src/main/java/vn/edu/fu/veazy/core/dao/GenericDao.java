package vn.edu.fu.veazy.core.dao;

import java.util.List;

public interface GenericDao<Type, ObjectId> {

    void save(Type entity) throws Exception;
    Type findById(ObjectId id) throws Exception;
    List<Type> findByExample(Type exampleInstance) throws Exception;
    List<Type> findByExample(Type exampleInstance, int offset, int limit) throws Exception;
    List<Type> getAll() throws Exception;
    void update(Type entity) throws Exception;
    void delete(Type entity) throws Exception;
    Long getCount() throws Exception;
    Object executeSql(String sql, Class clazz) throws Exception;

}