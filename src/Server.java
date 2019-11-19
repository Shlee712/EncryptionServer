import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
	public static void main(String args[]) {
		Server server = new Server();
		server.run();
	}
	
	public void run( ) {
		int portNum = 5520;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNum);
		} catch(IOException e) {
			e.printStackTrace();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream("prog1b.log"),true);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		while(true) {
			Socket sock = null;
			try {
				sock = serverSocket.accept();
			} catch(IOException e) {
				e.printStackTrace();
			}
			ServerThread servThread = new ServerThread(sock, writer);
			servThread.start();
		}
		
	}
	
}
