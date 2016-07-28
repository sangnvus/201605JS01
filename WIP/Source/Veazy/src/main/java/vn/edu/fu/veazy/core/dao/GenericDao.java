package vn.edu.fu.veazy.core.dao;

import java.util.List;

public interface GenericDao<Type, ObjectId> {
	/**
	 * モデルをサーブ
	 * @param entity　モデル
	 * @throws Exception
	 */
    void save(Type entity) throws Exception;
    
    /**
     * ＩＤでモデルをとる
     * @param id　モデルのＩＤ
     * @return　モデル
     * @throws Exception
     */
    Type findById(ObjectId id) throws Exception;
    
    /**
     * サンプルでモデルを検索
     * @param exampleInstance　サンプル
     * @return　全部の適当なモデル
     * @throws Exception
     */
    List<Type> findByExample(Type exampleInstance) throws Exception;
    
    /**
     * サンプルでモデルを検索
     * @param exampleInstance サンプル
     * @param offset 開始の順序
     * @param limit 限界の数
     * @return 全部の適当なモデル
     * @throws Exception
     */
    List<Type> findByExample(Type exampleInstance, int offset, int limit) throws Exception;
    
    /**
     * 全部のモデルをとる
     * @return　全部のモデル
     * @throws Exception
     */
    List<Type> getAll() throws Exception;
    
    /**
     * モデルをアップデート
     * @param entity　モデル
     * @throws Exception
     */
    void update(Type entity) throws Exception;
    
    /**
     * モデルを消す
     * @param entity　モデル
     * @throws Exception
     */
    void delete(Type entity) throws Exception;
    Long getCount() throws Exception;

}