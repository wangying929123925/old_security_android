package com.example.ananops_android.db;

public class InspectionCommentRequest {

    /**
     * contents : string
     * inspectionTaskId : 0
     * principalId : 0
     * score : 0
     */

    private String contents;
    private Long inspectionTaskId;
    private Long principalId;
    private Float score;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getInspectionTaskId() {
        return inspectionTaskId;
    }

    public void setInspectionTaskId(Long inspectionTaskId) {
        this.inspectionTaskId = inspectionTaskId;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
