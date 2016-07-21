package vn.edu.fu.veazy.dictionary.service;

import java.util.List;
import java.util.Vector;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.response.LookupWordResponse;
import vn.edu.fu.veazy.core.response.ResponseExample;
import vn.edu.fu.veazy.core.response.ResponseWordMean;
import vn.edu.fu.veazy.core.service.LessonServiceImpl;
import vn.edu.fu.veazy.dictionary.model.ExampleModel;
import vn.edu.fu.veazy.dictionary.model.JaviModel;
import vn.edu.fu.veazy.dictionary.model.VijaModel;
import vn.edu.fu.veazy.dictionary.model.WordMean;

public class DictServiceImpl implements DictService{
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonServiceImpl.class);
	@Autowired
	private GenericDao<JaviModel, Integer> javiDao;
	@Autowired
	private GenericDao<VijaModel, Integer> vijaDao;
	@Autowired
	private GenericDao<ExampleModel, Integer> exampleDao;
	
	@Override
	@Transactional
	public List<LookupWordResponse> lookupJavi(String key) throws Exception {
		JaviModel sample = new JaviModel();
		sample.setWord(key);
		List<JaviModel> javis = javiDao.findByExample(sample);
		List<LookupWordResponse> result = new Vector<>();
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
		List<LookupWordResponse> result = new Vector<>();
		if(vijas != null && !vijas.isEmpty()){
			for (VijaModel vijaModel : vijas) {
				result.add(loadFull(vijaModel.getWord(),vijaModel.getMeans()));
			}
		}
		return result;
	}

	private LookupWordResponse loadFull(String word, List<WordMean> wordMeans) throws Exception {
		LookupWordResponse result = new LookupWordResponse();
		result.setWord(word);
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
}
