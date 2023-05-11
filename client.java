import java.net.*;
import java.io.*;

class client {
	public static void main(String[] args) {


		// setup iniziale
		String inputline = ""; 
		int portNumber = 1234;

		try {

			// creating socket.
			System.out.println("Client setting up:");
			Socket socket =  new Socket("localhost", portNumber);
			PrintWriter    outServer	= new PrintWriter(socket.getOutputStream(), true);
			BufferedReader inServer		= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("connesso!");

			BufferedReader inKeyboard = null;
			do {

				inKeyboard = new BufferedReader(new InputStreamReader(System.in));

				// leggere input da tastiera (unica parte bloccante del codice).
				inputline  = inKeyboard.readLine();
				System.out.println("invio: " + inputline);
				outServer.println(inputline); // parte nella quale mando qualcosa al server

				System.out.println("ricevuto: " + inServer.readLine());

			} while (!inputline.equals("BYE"));

			// rilascio delle risorse.
			outServer.close();
			inServer.close();
			inKeyboard.close();
			socket.close();



		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

