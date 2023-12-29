package DBTest.DO;

import java.util.Date;

import oracle.jdbc.driver.DatabaseError;

/**
 * @author Xingjian LONG
 * Created on 2021-06-30
 */
public class PartnerDOV2 {
    private long userId;
    private String bucket;
    private long basePrice;
    private String currencyCode;
    private Date beginTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}
