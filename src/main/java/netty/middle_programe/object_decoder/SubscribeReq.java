package netty.middle_programe.object_decoder;

import java.io.Serializable;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-12
 */
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subReqID;
    private String userName;
    private String productName;
    private String photoNumber;
    private String address;

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subReqID=" + subReqID +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", photoNumber='" + photoNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(String photoNumber) {
        this.photoNumber = photoNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
