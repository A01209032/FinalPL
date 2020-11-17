import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	static final int PORT = 8080;
	static final boolean verbose = true;
	static final int MAX_T = 100;  
	
	
    public static void main(String[] args) {
    	ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
    	try {
			@SuppressWarnings("resource")
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
			
			while (true) {
				Connection myServer = new Connection(serverConnect.accept());
				
				if (verbose) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}
				
				pool.execute(myServer);
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
    	
    	
        
    }
}