import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.lang.Object.*;
import org.omg.CORBA.NameValuePair;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) throws IOException {
		
		try
        {
			String urlParameters = "{\"medium\": \"balance\",\"transaction_date\": \"2017-03-26\",\"amount\": 1,\"description\": \"Coin deposit\"}";
            System.out.println(urlParameters);
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String request = "http://api.reimaginebanking.com/accounts/58d7bf181756fc834d909c86/deposits?key=37eda199c5d3895687d139770b1d9c9a";
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);


            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		
    		String json = "";
    		while ((json = rd.readLine()) != null)
                System.out.println(json);
            rd.close();
        }
        catch (Exception e)
        {
        }
		
		
		
		
		
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
