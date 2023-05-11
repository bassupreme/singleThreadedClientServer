import java.net.*;
import java.io.*;

public class server {
	public static void main(String[] args) throws IOException {
		while(true) {
			// setup iniziale	
			String inputline = "";
			final String msg = "ciao client! ";
			final int portNumber = 1234;

			System.out.println("Server setting up ... ");
			ServerSocket serverSocket = new ServerSocket(portNumber);

			Socket soc	= serverSocket.accept(); // accept ritorna solo un socket.
			System.out.println("connessione accettatata...");

			// setup degli I/O di questo endpoint
			PrintWriter  outServer		= new PrintWriter(soc.getOutputStream(), true); 
			BufferedReader inServer		= new BufferedReader(new InputStreamReader(soc.getInputStream()));

			while(!inputline.equals("BYE")) { // alla prima iterazione questa condizione == false
				try {

					inputline = inServer.readLine(); // parte bloccante del codice
																					 // una buona idea sarebbe mettere questa parte di computazione in un thread separato
																					 // per non bloccare la principale linea di esecuzione (=> main thread).
					System.out.println("ricevuto: " + inputline);
					// invio risposta da parte del server 
					outServer.println(msg + "ho ricevuto: " + inputline);	

				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			// rilascio delle risorse
			outServer.close();
			inServer.close();
			serverSocket.close();
			soc.close();
		}
	}
}
