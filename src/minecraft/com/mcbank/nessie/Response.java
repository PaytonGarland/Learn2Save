package com.mcbank.nessie;

public class Response {
	private String version;
	private String termsofService;
	private Features features;
	
	public Response()
	{
		features = new Features();
	}
}
