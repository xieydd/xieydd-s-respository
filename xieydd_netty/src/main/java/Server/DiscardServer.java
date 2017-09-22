package Server;

import Handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 

* @ClassName: DiscardServer 

* @Description: TODO(启动DiscardServerHandler) 

* @author xieydd xieydd@gmail.com  

* @date 2017-9-20 下午1:30:58 

*
 */
public class DiscardServer {
	
	private int port;
	
	public DiscardServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		/**
		 * NioEventLoopGroup：是处理IO操作的多线程循环器
		 * bossGroup:用来接收进来的连接,一旦接收到数据就注册到workGroup上
		 * workGroup:处理进来的连接
		 */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			/**
			 * ServerBootstrap是为了启动NIO服务创建的辅助类，在这个服务中可以直接使用Channel(为了将这个过程简化)
			 */
			ServerBootstrap b = new ServerBootstrap();
			/**
			 * 指定使用NioServerSocketChannel来说明新的Channel如何连接进来
			 * ChannelInitializer帮助使用者配置一个新的Channel，我们在这里配置了一个处理类DiscardServerHandler(将其加到SocketChannel.pipline得到的ChannelPipeline上)
			 * .option和childOption是配置Channel实现的参数
			 *  public static final ChannelOption<Integer> SO_BACKLOG = valueOf("SO_BACKLOG"); return new ChannelOption<Object>(id, name);
			 *  
			 */
			b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ch.pipeline().addLast(new DiscardServerHandler());
				}
				
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE,true);
			
			//绑定端口,这里可以绑定不同地址
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally{
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int port;
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}else{
			port = 8080;
		}
		new DiscardServer(port).run();
	}
}
