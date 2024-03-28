import java.net.*;
import java.io.*;

public class ThreadedClient {
	
	public static void main(String args[]) throws Exception {
				
		Socket hostSocket = new Socket("localhost", 1024);
		System.out.println("Connection established. Enter A Message!");
		
		PrintWriter goingOut = new PrintWriter(hostSocket.getOutputStream(), true);
		BufferedReader comingIn = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
		
		BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
		
		String fromServer, fromUser;
		
		while ((fromServer = comingIn.readLine()) != null) {
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye."))
				break;
			
			fromUser = clientInput.readLine();
			if (fromUser != null) {
				System.out.println("Client: " + fromUser);
				goingOut.println(fromUser);
			}
		}
		System.out.println("Socket Closing");
		hostSocket.close();
	}
}
