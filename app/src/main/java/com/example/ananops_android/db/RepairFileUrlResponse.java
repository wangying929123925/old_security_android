package com.example.ananops_android.db;

import java.util.List;

public class RepairFileUrlResponse {

    /**
     * code : 200
     * message : 操作成功
     * result : [{"status":2,"elementImgUrlDtoList":[{"url":"http://file.ananops.com/ananops%2Fmdmc%2F838245741926286336%2FmdmcTaskAndroid%2F841892510451110912.png?e=1583588109&token=9BaGusa9xE_kgQpuHRqjU7-wVyhnVVyL14dUvG1B:mcRQfn67QwXGZbAEgP7K4ggASsc=","name":"841892510451110912.png","attachmentId":"841892525449942016"},{"url":"http://file.ananops.com/ananops%2Fmdmc%2F838245741926286336%2FmdmcTaskAndroid%2F841892510451110913.png?e=1583588109&token=9BaGusa9xE_kgQpuHRqjU7-wVyhnVVyL14dUvG1B:vDp2qbTikNqFfShQC-IYG6UsymM=","name":"841892510451110913.png","attachmentId":"841892528830551040"}]}]
     */

    private String code;
    private String message;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * status : 2
         * elementImgUrlDtoList : [{"url":"http://file.ananops.com/ananops%2Fmdmc%2F838245741926286336%2FmdmcTaskAndroid%2F841892510451110912.png?e=1583588109&token=9BaGusa9xE_kgQpuHRqjU7-wVyhnVVyL14dUvG1B:mcRQfn67QwXGZbAEgP7K4ggASsc=","name":"841892510451110912.png","attachmentId":"841892525449942016"},{"url":"http://file.ananops.com/ananops%2Fmdmc%2F838245741926286336%2FmdmcTaskAndroid%2F841892510451110913.png?e=1583588109&token=9BaGusa9xE_kgQpuHRqjU7-wVyhnVVyL14dUvG1B:vDp2qbTikNqFfShQC-IYG6UsymM=","name":"841892510451110913.png","attachmentId":"841892528830551040"}]
         */

        private int status;
        private List<ElementImgUrlDtoListBean> elementImgUrlDtoList;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ElementImgUrlDtoListBean> getElementImgUrlDtoList() {
            return elementImgUrlDtoList;
        }

        public void setElementImgUrlDtoList(List<ElementImgUrlDtoListBean> elementImgUrlDtoList) {
            this.elementImgUrlDtoList = elementImgUrlDtoList;
        }

        public static class ElementImgUrlDtoListBean {
            /**
             * url : http://file.ananops.com/ananops%2Fmdmc%2F838245741926286336%2FmdmcTaskAndroid%2F841892510451110912.png?e=1583588109&token=9BaGusa9xE_kgQpuHRqjU7-wVyhnVVyL14dUvG1B:mcRQfn67QwXGZbAEgP7K4ggASsc=
             * name : 841892510451110912.png
             * attachmentId : 841892525449942016
             */

            private String url;
            private String name;
            private String attachmentId;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAttachmentId() {
                return attachmentId;
            }

            public void setAttachmentId(String attachmentId) {
                this.attachmentId = attachmentId;
            }
        }
    }
}
