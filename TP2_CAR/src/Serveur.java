import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public final static int SOCKET_PORT = 4000; 
	public final static String FILE_TO_RECEIVED = "fileToTest.txt";
	
	public final static int FILE_SIZE = 6022386;
	
	public static void main (String [] args ) throws IOException {
		int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
		
		try {
		      servsock = new ServerSocket(SOCKET_PORT);
		      while (true) {
		          System.out.println("Waiting...");
		          try {
		              sock = servsock.accept();
		              System.out.println("Accepted connection : " + sock);

		              //receive file
		              byte [] mybytearray  = new byte [FILE_SIZE];
		              InputStream is = sock.getInputStream();
		              fos = new FileOutputStream(FILE_TO_RECEIVED);
		              bos = new BufferedOutputStream(fos);
		              bytesRead = is.read(mybytearray,0,mybytearray.length);
		              current = bytesRead;
		              
		              do {
		                  bytesRead =
		                     is.read(mybytearray, current, (mybytearray.length-current));
		                  if(bytesRead >= 0) current += bytesRead;
		               } while(bytesRead > -1);
		              
		              bos.write(mybytearray, 0 , current);
		              bos.flush();
		              System.out.println("File " + FILE_TO_RECEIVED
		                      + " downloaded (" + current + " bytes read)");
		              
		              
		              String[] arg = new String[2];
		              arg[0] = "1";
		              arg[1] = FILE_TO_RECEIVED;
				      Threading t = new Threading();
				      System.out.print("Mot le plus fréquent dans le fichier: ");
				      t.start(arg);
		          }
		          finally {
		              if (fos != null) fos.close();
		              if (bos != null) bos.close();
		              if (sock != null) sock.close();
		          }
		      }
		}
		finally {
		      if (servsock != null) servsock.close();
		}
	}
	
}