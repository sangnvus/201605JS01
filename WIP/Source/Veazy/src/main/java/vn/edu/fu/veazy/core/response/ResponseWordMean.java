package vn.edu.fu.veazy.core.response;

import java.util.List;

public class ResponseWordMean{
	private String kind;
	private String mean;
	private List<ResponseExample> examples;
	
	public ResponseWordMean() {
		super();
	}

	public ResponseWordMean(String kind, String mean, List<ResponseExample> examples) {
		super();
		this.kind = kind;
		this.mean = mean;
		this.examples = examples;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public List<ResponseExample> getExamples() {
		return examples;
	}

	public void setExamples(List<ResponseExample> examples) {
		this.examples = examples;
	}
}