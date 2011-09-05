package edu.ecm.blog.test;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void get() throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpGet httpget = new HttpGet("http://www.lemonde.fr");
		
		HttpResponse response = httpClient.execute(httpget);
		
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
	}
}
