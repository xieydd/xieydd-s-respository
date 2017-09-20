package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.ietf.jgss.ChannelBinding;

/**
 * 

* @ClassName: DiscardServerHandler 

* @Description: TODO(��������Channel,һ��Э�齫���������յ������ݲ���Ӧ�������������յ�������) 

* @author xieydd xieydd@gmail.com  

* @date 2017-9-20 ����11:37:03 

*
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/**
		 * ͨ��ChannelRead����Ϊ
		 * try{
		 * 	����msg
		 * }finally{
		 * //���ü������󹤾��ͷ�
		 * 	ReferenceCountUtils.release(msg);
		 * }
		 */
		// TODO �������е����� ByteBuf�����ü�������,��Ҫ��ʽ�ĵ���release�������ͷ�(ע�⴦�����ͷŷ��Ǵ��ݵ������������ü�������)
		((ByteBuf)msg).release();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO ����Throwable����Żᱻ���þʹ�ӡ(���ﵱȻ����������ݲ�ͬ���ѡ��ͬ�Ĵ�����)Ȼ��ر�����
		cause.printStackTrace();
		ctx.close();
	}
}
