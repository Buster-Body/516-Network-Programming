import java.net.*;
import java.io.*;

public class ThreadedServer {
	
	public static void main(String args[]) throws Exception {
		
		int portNumber = 1024;
		
		ServerSocket server = new ServerSocket(portNumber);
		System.out.println("Awaiting Connection...");
		
		while(true) {
			Socket client = server.accept();
			Thread thread = new Thread(new SocketThread(client));
			thread.start();
		}
	}
	
	private static class SocketThread implements Runnable {
		
		private Socket socket;
		
		public SocketThread(Socket socket) {
			this.socket = socket;
		}
		public void run() {
			
			System.out.println("Connection from:" + socket);
			
			try {
				PrintWriter goingOut = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader comingIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String inputLine, outputLine = "Welcome!";
				goingOut.println(outputLine);
				
				while (true) {
					inputLine = comingIn.readLine();
					outputLine = inputLine;
					goingOut.println("Number of characters: " + outputLine.replace("\\s", "").length());
					if (outputLine.equals("Bye."))
						socket.close();
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
