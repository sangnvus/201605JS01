package vn.edu.fu.veazy.core.response;

public class StatsLastExamResponse {

    private Double result = 0d;

    public StatsLastExamResponse(Double result) {
        super();
        this.result = result;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

}
