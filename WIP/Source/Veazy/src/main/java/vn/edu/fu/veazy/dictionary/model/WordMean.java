package vn.edu.fu.veazy.dictionary.model;

import java.util.List;

public class WordMean {
	private String kind;
	private String mean;
	private List<Integer> examples;
	
	public WordMean(String kind, String mean, List<Integer> examples) {
		super();
		this.kind = kind;
		this.mean = mean;
		this.examples = examples;
	}
	
	public WordMean() {
		super();
	}
	
	public WordMean(String kind, String mean) {
		super();
		this.kind = kind;
		this.mean = mean;
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
	public List<Integer> getExamples() {
		return examples;
	}
	public void setExamples(List<Integer> examples) {
		this.examples = examples;
	}
}
