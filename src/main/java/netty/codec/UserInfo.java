package netty.codec;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-12
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private long userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public static final class UserInfoBuilder {
        private String userName;
        private long userId;

        private UserInfoBuilder() {
        }

        public static UserInfoBuilder anUserInfo() {
            return new UserInfoBuilder();
        }

        public UserInfoBuilder buildUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserInfoBuilder buildUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserInfo build() {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(userName);
            userInfo.setUserId(userId);
            return userInfo;
        }
    }

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putLong(this.userId);
        buffer.flip();
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}
