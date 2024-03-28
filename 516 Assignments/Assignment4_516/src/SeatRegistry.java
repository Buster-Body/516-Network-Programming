import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SeatRegistry {
	
	private static Seat[] seats;
	
	private static Seat[] createSeats(int numSeats) {

		Seat[] seats = new Seat[numSeats];
		for (int i = 0; i < seats.length; i++) {
			seats[i] = new Seat();
		}
		return seats;
	}
	
	private static String seatList() {
		String seatList = "";
		for (Seat seat : seats)
			seatList += seat.print();
		return seatList;
	}

	public static void main(String[] args) throws Exception{
		
		if (args.length != 1) {
			System.out.println("No arguments");
			System.exit(0);
		}
		seats = createSeats(20);
		ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("Awaiting Connections");
		
		while (true) {
			Socket clientSocket = server.accept();
			Thread socketThread = new Thread(new SocketHandler(clientSocket));
			socketThread.start();
		}
	}
	
	private static class SocketHandler implements Runnable{
		
		Socket s;
		BufferedReader in;
		PrintWriter out;
		
		private SocketHandler(Socket client) {
			s = client;
		}
		
		private void seatSelectionPrompt() {
			try {
				int seatNum = (Integer.parseInt(in.readLine()) - 1);
				String name = in.readLine();
				
				if (!seats[seatNum].isReserved()) {
					seats[seatNum].setReservation(name);
					out.println("*RESERVATION SUCESSFUL*");
				} else
					out.println("*RESERVATION DECLINED*");
				out.println(seatList());
				seatSelectionPrompt();
			}catch (Exception e) {}
		}
		@Override
		public void run() {
			System.out.println("Connection from:" + s);
			try {
				out = new PrintWriter(s.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				out.println("***Select seats to reserve***");
				out.println(seatList());
				
				seatSelectionPrompt();
				
				System.out.println("Closing client for: " + s);
				Thread.currentThread().interrupt();
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Seat {
		// class Variable
		private static int numSeatsCreated = 0;

		// Object Variables
		private String reservationHolder;
		private boolean isReserved;
		private int seatNum;

		Seat() {
			isReserved = false;
			reservationHolder = null;
			numSeatsCreated++;
			seatNum = numSeatsCreated;
		}

		public boolean isReserved() {
			return isReserved;
		}

		public synchronized void setReservation(String name) {
			if (!isReserved())
				reservationHolder = name;
				isReserved = true;
		}

		public String print() {
			if (isReserved)
				return "\tSeat: " + seatNum + " | " + reservationHolder + "\n";
			else
				return "\tSeat: " + seatNum + " | N/A" + "\n";
		}
	}
}