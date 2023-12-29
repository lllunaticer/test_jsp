package DBTest.DO;

/**
 * @author Xingjian LONG
 * Created on 2021-07-09
 */
public class UserNotActiveModel {
    private long id;
    private long userId;
    private String date;
    private int pushStatus; // 0 - 未发送成功 1 - 发送成功

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(int pushStatus) {
        this.pushStatus = pushStatus;
    }

    @Override
    public String toString() {
        return "UserNotActiveModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", pushStatus=" + pushStatus +
                '}';
    }
}