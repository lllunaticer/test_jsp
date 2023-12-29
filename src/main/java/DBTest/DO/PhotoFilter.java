package DBTest.DO;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-24
 */
public class PhotoFilter {

    // 系统临时隐藏，人工确认后需要恢复的一种状态
    public static final int STATUS_HIDE_BY_SYS = 6;
    public static final int STATUS_HIDE_BY_REPORT = 5;
    public static final int STATUS_DELETED = 4;
    public static final int STATUS_GOOD_BY_ADMIN = 3;
    public static final int STATUS_HIDE_BY_ADMIN = 2;
    public static final int STATUS_GOOD_INIT = 0;

    private long id;
    private long photoId;
    private long userId;
    private int reportCount;
    private int photoStatus;
    private Date time;
    private String reporterIds;
    private String extParams;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public int getPhotoStatus() {
        return photoStatus;
    }

    public void setPhotoStatus(int photoStatus) {
        this.photoStatus = photoStatus;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReporterIds() {
        return reporterIds;
    }

    public void setReporterIds(String reporterIds) {
        this.reporterIds = reporterIds;
    }

    public String getExtParams() {
        return extParams;
    }

    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static boolean isGood(int status) {
        return status == STATUS_GOOD_BY_ADMIN || status == STATUS_GOOD_INIT;
    }
}