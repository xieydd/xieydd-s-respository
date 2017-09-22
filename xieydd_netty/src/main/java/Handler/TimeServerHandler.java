package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 

* @ClassName: TimeServerHandler 

* @Description: TODO(ʱ�������:���������������½�32λ����Ϣ���͵��ͻ��ˣ���Ϣһ�����;������ر�����) 

* @author xieydd xieydd@gmail.com  

* @date 2017-9-22 ����11:56:36 

*
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{
	
	//�������н��ܵ�����,���Բ�������ChannelRead
	//channelActive�������ӽ���׼��ͨ��ʱ������
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//����һ���������ʱ����Ϣ(32λ��������4�ֽ�)���µĻ���ByteBufAllocator.buffer(4)
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
		
		//����û����java.nio.ByteBuffer.filp(),��Ϊ����������ָ��һ����ָ��һ��дָ�룬��BYteBUf��д���ʱ��дָ��仯���Ƕ�ָ�벻�䣬������ָ���дָ��������ݵĿ�ʼ�ͽ�β
		//ChannelFuture��û�н���IO������,����Netty���첽���������Կ�������Ϣ������ȥ֮ǰ�͹ر����ӣ������Ҫ��write()��WriteAndFlush()��������ChannelFuture֮�����close()�ر���������Ҫ����һ������
		final ChannelFuture f = ctx.writeAndFlush(time);
		
		//���Ը�д��f.addListener(ChannelFutureListener.CLOSE)
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
