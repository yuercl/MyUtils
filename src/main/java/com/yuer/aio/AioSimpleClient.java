package com.yuer.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioSimpleClient {

	private AsynchronousSocketChannel client;

	public AioSimpleClient(String host, int port) throws IOException, InterruptedException, ExecutionException {
		this.client = AsynchronousSocketChannel.open();
		Future<?> future = client.connect(new InetSocketAddress(host, port));
		future.get();
	}

	public void write(byte b) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		byteBuffer.put(b);
		byteBuffer.flip();
		client.write(byteBuffer);
	}

}