import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

class ServerThread extends Thread {
	Socket clientSock;
	PrintWriter sockWriter;
	PrintWriter logfile;
	BufferedReader reader;
	
	public ServerThread(Socket clientSock, PrintWriter logfile) {
		this.clientSock = clientSock;
		this.logfile = logfile;
	}
	
	public void run() {
		LocalDateTime date = LocalDateTime.now();
		logfile.println("Connection received. Date: " + date.toString() + " IP: " + clientSock.getInetAddress() + " Port: " +clientSock.getPort());
		
		try {
			reader = new BufferedReader(new InputStreamReader( clientSock.getInputStream()));
			sockWriter = new PrintWriter(clientSock.getOutputStream(), true);
		} catch (IOException e) {
			logfile.println("Error: " +e.toString());
		}
		
		String msg = null;
		while(true) {
			try {
				msg = reader.readLine();
			} catch (IOException e) {
				logfile.println("Error: " + e.toString());
				break;
			}
			if(msg == null || msg.equals("quit")) {
				break;
			}
			else {
				msg = Encryption.encrypt(msg);
				sockWriter.println(msg);
			}
		}
		try {
			sockWriter.close();
			logfile.println("Connection closed "  + clientSock.getPort() );
			clientSock.close();
			reader.close();
		} catch (IOException e) {
			logfile.println("Error: "+e.toString());
		}
		return;
	}
}
