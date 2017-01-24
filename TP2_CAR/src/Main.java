import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author six
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nThreads = Integer.parseInt(args[0]);
		StringBuilder lines[] = new StringBuilder[nThreads];
		for (int i = 0; i < nThreads; i++) {
			lines[i] = new StringBuilder(" ");
		}

		InputStream fis;
		try {
			fis = new FileInputStream(args[1]);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
