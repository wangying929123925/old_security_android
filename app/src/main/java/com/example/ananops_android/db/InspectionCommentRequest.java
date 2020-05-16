package com.example.ananops_android.db;

public class InspectionCommentRequest {

    /**
     * checkContens : string
     * contents : string
     * inspectionTaskId : 0
     * principalId : 0
     * score : 0
     * status : 0
     */

    private String checkContens;
    private String contents;
    private Long inspectionTaskId;
    private Long principalId;
    private int score;
    private int status;

    public String getCheckContens() {
        return checkContens;
    }

    public void setCheckContens(String checkContens) {
        this.checkContens = checkContens;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
