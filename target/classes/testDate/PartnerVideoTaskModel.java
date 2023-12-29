package testDate;

import java.util.List;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-10
 */
public class PartnerVideoTaskModel {
    private int date;
    private List<BucketTask> tasks;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public List<BucketTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<BucketTask> tasks) {
        this.tasks = tasks;
    }

    public static class BucketTask {
        private String bucket;
        private List<String> mediaTypes;

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public List<String> getMediaTypes() {
            return mediaTypes;
        }

        public void setMediaTypes(List<String> mediaTypes) {
            this.mediaTypes = mediaTypes;
        }
    }
}
