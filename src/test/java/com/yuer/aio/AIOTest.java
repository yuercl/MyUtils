package com.yuer.aio;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class AIOTest {

	@Test
	public void testServer() throws IOException, InterruptedException {
		@SuppressWarnings("unused")
		AioSimpleServer server = new AioSimpleServer(7788);
		Thread.sleep(10000);
	}

	@Test
	public void testClient() throws IOException, InterruptedException, ExecutionException {
		AioSimpleClient client = new AioSimpleClient("localhost", 7788);
		client.write((byte) 11);
	}

}