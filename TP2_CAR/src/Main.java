import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author six
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// nb threads
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

			String l = null;
			int i = 0;
			while ((l = br.readLine()) != null) {
				lines[i].append(l);
				i = (i++) / nThreads;
			}
			App[] apps = new App[nThreads];
			for (int j = 0; j < nThreads; j++) {
				apps[i] = new App();
			}

			Compteur[] compt = new Compteur[nThreads];

			for (int j = 0; j < nThreads; j++) {
				compt[j] = new Compteur(apps[i], lines[j].toString());
				compt[j].start();
			}
			for (int j = 0; j < nThreads; j++) {
				try {
					compt[j].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for(int j=1;j<nThreads;j++){
				apps[0].merge(apps[j]);
			}
			
			Map.Entry<String, Integer> max = apps[0].findMax();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
