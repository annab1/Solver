package Solver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
 public static void main(String[] args) throws IOException {
	 Socket clientSocket = null;
	 
	 try {
	     System.out.println("Server is up");
	     ServerSocket serverSocket = new ServerSocket(4242);
	     while(true) {
		     clientSocket = serverSocket.accept();
		     new Thread(new ClientHandler(clientSocket)).start();
	     }
	     
	 } catch(Exception e) {
		 System.out.print(e);
	 } 
 }
}
