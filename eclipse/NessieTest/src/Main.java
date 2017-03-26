import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String url = "http://api.reimaginebanking.com/customers/58d603b11756fc834d9064ca/accounts?key=37eda199c5d3895687d139770b1d9c9a";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("type", "Credit%20Card");
		con.setRequestProperty("nickname", "Payton");
		con.setRequestProperty("rewards", "0");
		con.setRequestProperty("balance", "0");
		String urlParameters = "type=Credit%20Card&nickname=Payton&rewards=0&balance=0";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = ((HttpURLConnection) con).getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
		
		
		
		
//		String json = "";
//		URL oracle;
//		try {
//			oracle = new URL("http://api.reimaginebanking.com/customers/58d603b11756fc834d9064ca?key=37eda199c5d3895687d139770b1d9c9a");
//	        BufferedReader in = new BufferedReader(
//	        new InputStreamReader(oracle.openStream()));
//	
//	        String inputLine;
//	        while ((inputLine = in.readLine()) != null)
//	            json = json + inputLine;
//	        in.close();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(json + "\n\n");
//		
//		Customer customer = new Customer();
//		customer.setFirstName("Payton");
//		Gson gson = new Gson();
//		String test = gson.toJson(customer);
//		
//		System.out.println(gson);
//		
//		Customer top = gson.fromJson(json, Customer.class);
//		
//		System.out.println(top.getFirstName());
//		System.out.println(top.getLastName());
//		System.out.println(top.getId());
//		System.out.println(top.getAddress().getCity() + "\n" 
//				+ top.getAddress().getState() + "\n" 
//				+ top.getAddress().getStreetName() + "\n"); 
//		for (int i = 0; i < top.getAddress().size(); i++)
//		{
//			System.out.println(top.getAddress().get(i)[0]);
//			System.out.println(top.getAddress().get(i)[1]);
//			System.out.println(top.getAddress().get(i)[2]);
//			System.out.println(top.getAddress().get(i)[3]);
//		}		 
	}

}