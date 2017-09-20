package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.ietf.jgss.ChannelBinding;

/**
 * 

* @ClassName: DiscardServerHandler 

* @Description: TODO(处理服务端Channel,一个协议将丢掉所有收到的数据不响应，即忽略所有收到的数据) 

* @author xieydd xieydd@gmail.com  

* @date 2017-9-20 上午11:37:03 

*
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/**
		 * 通常ChannelRead方法为
		 * try{
		 * 	处理msg
		 * }finally{
		 * //引用计数对象工具释放
		 * 	ReferenceCountUtils.release(msg);
		 * }
		 */
		// TODO 丢掉所有的数据 ByteBuf是引用计数对象,需要显式的调用release方法来释放(注意处理器释放法是传递到处理器的引用计数对象)
		((ByteBuf)msg).release();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO 出现Throwable对象才会被调用就打印(这里当然处理方法会根据不同情况选择不同的处理方法)然后关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
