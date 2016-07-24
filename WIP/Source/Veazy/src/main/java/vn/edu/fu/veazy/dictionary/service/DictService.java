package vn.edu.fu.veazy.dictionary.service;

import java.util.List;

import vn.edu.fu.veazy.core.response.LookupWordResponse;

public interface DictService {
	List<LookupWordResponse> lookupJavi(String key) throws Exception;
	List<LookupWordResponse> lookupVija(String key) throws Exception;
}
