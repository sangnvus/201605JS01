package vn.edu.fu.veazy.dictionary.service;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.response.LookupWordResponse;
import vn.edu.fu.veazy.core.response.ResponseExample;
import vn.edu.fu.veazy.core.response.ResponseWordMean;
import vn.edu.fu.veazy.core.service.LessonServiceImpl;
import vn.edu.fu.veazy.dictionary.model.ExampleModel;
import vn.edu.fu.veazy.dictionary.model.JaviModel;
import vn.edu.fu.veazy.dictionary.model.VijaModel;
import vn.edu.fu.veazy.dictionary.model.WordMean;
/**
 * @author CuHo
 *
 */
@Service
public class DictServiceImpl implements DictService{
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonServiceImpl.class);
	
	/** japanese-vietnamese dictionary dao*/
	@Autowired
	private GenericDao<JaviModel, Integer> javiDao;
	
	/** vietnamese-japanese dictionary dao*/
	@Autowired
	private GenericDao<VijaModel, Integer> vijaDao;
	
	/** example dao */
	@Autowired
	private GenericDao<ExampleModel, Integer> exampleDao;
	
	
	@Override
	@Transactional
	public List<LookupWordResponse> lookupJavi(String key) throws Exception {
		JaviModel sample = new JaviModel();
		sample.setWord(key);
		List<JaviModel> javis = javiDao.findByExample(sample);
		List<LookupWordResponse> result = new Vector<LookupWordResponse>();
		if(javis != null && !javis.isEmpty()){
			for (JaviModel javiModel : javis) {
				result.add(loadFull(javiModel.getWord(),javiModel.getMeans()));
			}
		}
		return result;
	}

	@Override
	@Transactional
	public List<LookupWordResponse> lookupVija(String key) throws Exception {
		VijaModel sample = new VijaModel();
		sample.setWord(key);
		List<VijaModel> vijas = vijaDao.findByExample(sample);
		List<LookupWordResponse> result = new Vector<LookupWordResponse>();
		if(vijas != null && !vijas.isEmpty()){
			for (VijaModel vijaModel : vijas) {
				result.add(loadFull(vijaModel.getWord(),vijaModel.getMeans()));
			}
		}
		return result;
	}

	private LookupWordResponse loadFull(String word, String jsonMean) throws Exception {
		LookupWordResponse result = new LookupWordResponse();
		result.setWord(word);
		WordMean[] wordMeans = json2WordMean(jsonMean);
		if(wordMeans != null){
			List<ResponseWordMean> means = new Vector<ResponseWordMean>();
			for (WordMean m : wordMeans) {
				ResponseWordMean mean = new ResponseWordMean();
				mean.setKind(m.getKind());
				mean.setMean(m.getMean());
				if(m.getExamples() != null){
					mean.setExamples(loadFullExamples(m.getExamples()));
				}
				means.add(mean);
			}
			result.setMeans(means);
		}
		return result;
	}

	
	private List<ResponseExample> loadFullExamples(List<Integer> examples) throws Exception {
		List<ResponseExample> result = new Vector<ResponseExample>();
		for(int id:examples){
			ExampleModel model = exampleDao.findById(id);
			ResponseExample rExample = new ResponseExample(model.getVi(),model.getJa());
			result.add(rExample);
		}
		return result;
	}
	
	private WordMean[] json2WordMean(String json) throws JsonParseException, JsonMappingException, IOException{
		if(json == null || json.isEmpty()){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WordMean[].class);
	}
}
