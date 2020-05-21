package com.cl.elena.nettyClient;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// TODO Auto-generated method stub
							ch.pipeline().addLast(new EchoClientHandle());

						}
					});
			ChannelFuture futrue = bootstrap.connect().sync();
			futrue.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			group.shutdownGracefully().sync();
		}

	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port> ");
			return;
		}
		String host = "localhost";
		int port = Integer.parseInt("9999");
		new EchoClient(host, port).start();

	}
}