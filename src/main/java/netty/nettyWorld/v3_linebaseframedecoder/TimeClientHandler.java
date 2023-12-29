package netty.nettyWorld.v3_linebaseframedecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-05
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    private final byte[] req;

    // 因为使用了lineBasedFrameDecoder, 所以在发送消息的时候需要用换行符来标示一个消息的结束
    public TimeClientHandler() {
        req = ("query time order" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String body = (String) msg;
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
