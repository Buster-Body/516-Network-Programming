import java.net.*;
import java.io.*;

public class MyServer {
	
	public static void main(String args[]) throws Exception{
		
		int portNumber = 1024;
		
		ServerSocket server = new ServerSocket(portNumber);
		System.out.println("Awaiting Connection...");
		
		
		Socket client = server.accept();
		System.out.println("Connection from:" + client);
			
		PrintWriter goingOut = new PrintWriter(client.getOutputStream(), true);
		BufferedReader comingIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		String inputLine, outputLine = "Welcome!";
		goingOut.println(outputLine);
		
		while ((inputLine = comingIn.readLine()) != null) {
			outputLine = inputLine;
			goingOut.println("Number of characters: " + outputLine.replace(" ", "").length());
			if (outputLine.equals("Bye."))
				break;
		}
		System.out.println("Connection Closed.");
		server.close();
		
	}
}
