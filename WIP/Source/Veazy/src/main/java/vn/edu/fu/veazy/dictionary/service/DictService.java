package vn.edu.fu.veazy.dictionary.service;

import java.util.List;

import vn.edu.fu.veazy.core.response.LookupWordResponse;

/**
 * @author CuHo
 *
 */
public interface DictService {
	/**
	 * 和越辞書の検索
	 * @param key 検索の言葉
	 * @return　適当な言葉
	 * @throws Exception
	 */
	List<LookupWordResponse> lookupJavi(String key) throws Exception;
	
	/**
	 * 越和辞書の検索
	 * @param key 検索の言葉
	 * @return　適当な言葉
	 * @throws Exception
	 */
	List<LookupWordResponse> lookupVija(String key) throws Exception;
}
