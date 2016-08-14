package vn.edu.fu.veazy.core.response;

public class StatsCourseAvgResponse {

    private Integer courseId;
    private Double avgResult = 0d;
    
    public StatsCourseAvgResponse(Integer courseId, Double avgResult) {
        super();
        this.courseId = courseId;
        this.avgResult = avgResult;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getAvgResult() {
        return avgResult;
    }

    public void setAvgResult(Double avgResult) {
        this.avgResult = avgResult;
    }

}
