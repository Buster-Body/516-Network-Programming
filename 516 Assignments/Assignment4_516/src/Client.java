import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter an ip address to connect to.");
		String address = clientInput.readLine();
		System.out.println("Enter a port number");
		int portNumber = Integer.parseInt(clientInput.readLine());
		
		
		Socket hostSocket = new Socket(address, portNumber);
		System.out.println("Connection established. Enter A Message!");
		
		PrintWriter out = new PrintWriter(hostSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
		
		String fromServer, fromUser;
		
		while (true) {
			
			// retrieving seat list from server and printing to client
			while ((fromServer = in.readLine()) != null) {
				if (fromServer.isEmpty())
					break;
				System.out.println(fromServer);
			}
			System.out.println("Enter a valid seat #:");
			fromUser = clientInput.readLine();
			
			if (fromUser.equals("Quit")) {
				out.println(fromUser);
				break;
			}
			out.println(fromUser);
			System.out.println("Enter the reservation holders name");
			out.println(clientInput.readLine());
			System.out.println(in.readLine());
		}
		
		System.out.println("Socket Closing");
		hostSocket.close();
	}

}
