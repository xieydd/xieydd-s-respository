package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;



/**
 * 

* @ClassName: DiscardServerHandler 

* @Description: TODO(��������Channel,һ��Э�齫���������յ������ݲ���Ӧ�������������յ�������) 
�޸�1:���ͻ����������ݴ�ӡ������̨
�޸�2:ʵ��echo���������ܣ����������ݴ�ӡ���ͻ���
* @author xieydd xieydd@gmail.com  

* @date 2017-9-20 ����11:37:04 

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
		//((ByteBuf)msg).release();
		//��һ���޸�,�����ܵ������ݴ�ӡ������̨
		/*ByteBuf in = ((ByteBuf)msg);
		
		try{
			while(in.isReadable()) {
				System.out.print((char)in.readByte());
				System.out.flush();
			}
		}finally{
			ReferenceCountUtil.release(msg);
		}*/
		
		//�ڶ����޸� Ӧ���������echo���������ڲ��ԣ����������������ʾ�ڿͻ��ˣ�ע��͵�һ���޸Ĳ���ͬʱ��
		//ChannelHandlerContext,�ṩ�ܹ��������ָ�����I/O�¼��Ͳ�����ע�����ﲢû����finally{ReferenceCountUtil.release(msg);}��ΪNetty�Զ��ͷ���
		//write��δ����Ϣж��ͨ���У����嵽�ڲ�����Ҫ����flush��������������ǿ��������߿��Ժϲ�Ϊcxt.writeAndFlush(msg)
		ctx.write(msg);
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO ����Throwable����Żᱻ���þʹ�ӡ(���ﵱȻ����������ݲ�ͬ���ѡ��ͬ�Ĵ�����)Ȼ��ر�����
		cause.printStackTrace();
		ctx.close();
	}
}
