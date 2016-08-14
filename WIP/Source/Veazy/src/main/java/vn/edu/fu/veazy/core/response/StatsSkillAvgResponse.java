package vn.edu.fu.veazy.core.response;

public class StatsSkillAvgResponse {

    private Double listening = 0d;
    private Double reading = 0d;
    private Double vocabulary = 0d;
    private Double grammar = 0d;
    
    public StatsSkillAvgResponse() {
        super();
    }

    public StatsSkillAvgResponse(Double listening, Double reading, Double vocabulary, Double grammar) {
        super();
        this.listening = listening;
        this.reading = reading;
        this.vocabulary = vocabulary;
        this.grammar = grammar;
    }

    public Double getListening() {
        return listening;
    }

    public void setListening(Double listening) {
        this.listening = listening;
    }

    public Double getReading() {
        return reading;
    }

    public void setReading(Double reading) {
        this.reading = reading;
    }

    public Double getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Double vocabulary) {
        this.vocabulary = vocabulary;
    }

    public Double getGrammar() {
        return grammar;
    }

    public void setGrammar(Double grammar) {
        this.grammar = grammar;
    }

}
