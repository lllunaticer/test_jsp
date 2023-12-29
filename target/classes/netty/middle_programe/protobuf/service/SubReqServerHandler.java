package netty.middle_programe.protobuf.service;

import java.nio.charset.StandardCharsets;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.middle_programe.protobuf.message.SubscribeReqProto.SubscribeReq;
import netty.middle_programe.protobuf.message.SubscribeRespProto.SubscribeResp;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-12
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("longxingjian".equalsIgnoreCase(req.getUserName())) {
            System.out.println(
                    "Service accept client subscribe req : [" + new String(req.toByteArray(), StandardCharsets.UTF_8)
                            + "]");
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
    }

    private SubscribeResp resp(int subReqID) {
        return SubscribeResp.newBuilder()
                .setSubReqId(subReqID)
                .setRespCode(1)
                .setDesc("Netty book order succeed, 3 days later, sent to the ignated address")
                .build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
