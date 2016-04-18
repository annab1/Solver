package Solver;

import java.awt.Point;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import Common.SolveData;

public class ClientHandler implements Runnable {
	Socket clientSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
	     AStar aStar = new AStar(); 

		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		     in = new ObjectInputStream(clientSocket.getInputStream());
		     
		     SolveData solveData;
			while ((solveData = (SolveData) in.readObject()) != null) {
		    	List<Point> solution = aStar.solve(solveData);
		    	out.writeObject(solution);
		    	break;
		    }
		} 
		catch (Exception e) {}
	    finally {
	    	try {
	    		if (clientSocket != null && !clientSocket.isClosed()) {
	    			 clientSocket.close();
	    	    }
	  	 
				if (in != null) {
					in.close();
			     }
			     if (out != null) {
			         out.close();
			     }
	    	}
	    	catch (Exception e) {}
		}
	}

}
