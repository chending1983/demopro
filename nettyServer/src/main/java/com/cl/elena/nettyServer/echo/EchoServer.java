/**
 * 
 */
package com.cl.elena.nettyServer.echo;

import static java.lang.System.err;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author charlie
 *
 */
public class EchoServer {

	private final int port;
	/**
	 * 
	 */
	public EchoServer(int port) {
		this.port = port;
	}

	private void StartServer(String args[]) throws InterruptedException {
		final EchoServerHandler serverHandler = new EchoServerHandler();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(serverHandler);
				}
			});
			ChannelFuture f = serverBootstrap.bind().sync();
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
		
		
	}
	
	public static void main(String args[]) throws InterruptedException {
		if (args.length != 1) {
			err.println("Usage : " + EchoServer.class.getSimpleName() + "<port>");
			return;
		}
		int port = Integer.parseInt(args[0]);
		new EchoServer(port).StartServer(args);
	}
	
}
