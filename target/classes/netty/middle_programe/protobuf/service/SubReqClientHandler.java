package netty.middle_programe.protobuf.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.middle_programe.protobuf.message.SubscribeReqProto.SubscribeReq;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-12
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    public SubReqClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i) {
        return SubscribeReq.newBuilder()
                .addAddress("北京市海淀区")
                .addAddress("上海市浦东新区")
                .setSubReqId(i)
                .setProductName("存在主义咖啡馆")
                .setUserName("longxingjian")
                .build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
