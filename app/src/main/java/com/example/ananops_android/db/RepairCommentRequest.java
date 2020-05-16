package com.example.ananops_android.db;

public class RepairCommentRequest {

    /**
     * contents : string
     * principalId : 0
     * score : 0
     * taskId : 0
     * userId : 0
     */

    private String contents;
    private Long principalId;
    private int score;
    private Long taskId;
    private Long userId;
    private String checkContens;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCheckContens() {
        return checkContens;
    }

    public void setCheckContens(String checkContens) {
        this.checkContens = checkContens;
    }
}
