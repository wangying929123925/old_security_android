package com.example.ananops_android.db;

public class ConfirmWorkOrderRequest {

    /**
     * decision : 1
     * workOrderQueryDto : {"id":800607631264714752,"pageNum":0,"pageSize":0,"type":"inspection"}
     */

    private int decision;
    private WorkOrderQueryDtoBean workOrderQueryDto;

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public WorkOrderQueryDtoBean getWorkOrderQueryDto() {
        return workOrderQueryDto;
    }

    public void setWorkOrderQueryDto(WorkOrderQueryDtoBean workOrderQueryDto) {
        this.workOrderQueryDto = workOrderQueryDto;
    }

    public static class WorkOrderQueryDtoBean {
        /**
         * id : 800607631264714752
         * pageNum : 0
         * pageSize : 0
         * type : inspection
         */

        private long id;
        private int pageNum;
        private int pageSize;
        private String type;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
