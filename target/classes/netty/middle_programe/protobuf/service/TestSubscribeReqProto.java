package netty.middle_programe.protobuf.service;

import java.util.Arrays;

import com.google.protobuf.InvalidProtocolBufferException;

import netty.middle_programe.protobuf.message.SubscribeReqProto.SubscribeReq;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-17
 */
public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReq decode(byte[] bytes) throws InvalidProtocolBufferException {
        return SubscribeReq.parseFrom(bytes);
    }

    private static SubscribeReq createInstance() {
        return SubscribeReq.newBuilder()
                .addAddress("北京市海淀区")
                .addAddress("上海市浦东新区")
                .setSubReqId(1)
                .setProductName("存在主义咖啡馆")
                .setUserName("龙行健")
                .build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReq instance = createInstance();
        System.out.println("before encode : [" + instance + "]");
        byte[] encode = encode(instance);
        System.out.println("after encode, length : [" + encode.length + "]");
        System.out.println(Arrays.toString(encode));
        System.out.println("decode from encode : [" + decode(encode) + "]");
    }
}
