package vn.edu.fu.veazy.core.response;

public class StatsLastExamResponse {

    private Long date = 0l;

    private Double result = 0d;

    public StatsLastExamResponse(Double result, Long date) {
        super();
        this.result = result;
        this.date = date;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
    
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

}
