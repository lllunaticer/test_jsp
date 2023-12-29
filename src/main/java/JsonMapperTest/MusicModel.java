package JsonMapperTest;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-14
 */
public class MusicModel {
    private String userName;
    private long musicId;
    private long userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getMusicId() {
        return musicId;
    }

    public void setMusicId(long musicId) {
        this.musicId = musicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
