package vn.edu.fu.veazy.core.response;

import java.util.List;

public class LookupWordResponse {
	private String word;
	private List<ResponseWordMean> means;
	
	public LookupWordResponse() {
		super();
	}

	public LookupWordResponse(String word, List<ResponseWordMean> means) {
		super();
		this.word = word;
		this.means = means;
	}
	
	

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<ResponseWordMean> getMeans() {
		return means;
	}

	public void setMeans(List<ResponseWordMean> means) {
		this.means = means;
	}



	
	
	
}


