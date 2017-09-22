package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 

* @ClassName: TimeServerHandler 

* @Description: TODO(时间服务器:不接受请求的情况下将32位的消息发送到客户端，消息一旦发送就立即关闭连接) 

* @author xieydd xieydd@gmail.com  

* @date 2017-9-22 上午11:56:36 

*
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{
	
	//忽略所有接受的数据,所以不用再用ChannelRead
	//channelActive会在连接建立准备通信时被调用
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//分配一个包含这个时间消息(32位所以至少4字节)的新的缓存ByteBufAllocator.buffer(4)
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
		
		//这里没有用java.nio.ByteBuffer.filp(),因为这里有两个指针一个读指针一个写指针，向BYteBUf中写入的时候写指针变化但是读指针不变，这样读指针和写指针就是数据的开始和结尾
		//ChannelFuture是没有进行IO操作的,由于Netty是异步操作，所以可能在消息被发出去之前就关闭连接，因此需要在write()或WriteAndFlush()方法返回ChannelFuture之后调用close()关闭连接这里要设置一个监听
		final ChannelFuture f = ctx.writeAndFlush(time);
		
		//可以改写成f.addListener(ChannelFutureListener.CLOSE)
		f.addListener(new ChannelFutureListener() {
			
			public void operationComplete(ChannelFuture future) throws Exception {
				// TODO Auto-generated method stub
				assert f == future;
				ctx.close();
			}
		});
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
}
