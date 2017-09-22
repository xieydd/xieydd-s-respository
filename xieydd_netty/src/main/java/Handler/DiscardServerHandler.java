package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;



/**
 * 

* @ClassName: DiscardServerHandler 

* @Description: TODO(处理服务端Channel,一个协议将丢掉所有收到的数据不响应，即忽略所有收到的数据) 
修改1:将客户端输入数据打印到控制台
修改2:实现echo服务器功能，将输入数据打印到客户端
* @author xieydd xieydd@gmail.com  

* @date 2017-9-20 上午11:37:04 

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
		//((ByteBuf)msg).release();
		//第一次修改,将接受到的数据打印到控制台
		/*ByteBuf in = ((ByteBuf)msg);
		
		try{
			while(in.isReadable()) {
				System.out.print((char)in.readByte());
				System.out.flush();
			}
		}finally{
			ReferenceCountUtil.release(msg);
		}*/
		
		//第二次修改 应答服务器，echo服务器用于测试，即将输入的内容显示在客户端，注意和第一次修改不能同时用
		//ChannelHandlerContext,提供能够触发各种各样的I/O事件和操作，注意这里并没有用finally{ReferenceCountUtil.release(msg);}因为Netty自动释放了
		//write并未将消息卸载通道中，缓冲到内部，需要调用flush将缓冲区的数据强行输出两者可以合并为cxt.writeAndFlush(msg)
		ctx.write(msg);
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO 出现Throwable对象才会被调用就打印(这里当然处理方法会根据不同情况选择不同的处理方法)然后关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
