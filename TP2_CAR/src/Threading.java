import java.io.BufferedReader;
import java.io.File;
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
public class Threading {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void start(String[] args) throws IOException {

		File f = new File(
				args[1]);
		System.out.println("Chemin absolu du fichier : "
				+ f.getAbsolutePath());
		System.out.println("Nom du fichier : " + f.getName());
		System.out.println("Est-ce qu'il existe ? " + f.exists());
		System.out.println("Est-ce un fichier ?" + f.isFile() + "\n");
		
		// nb threads
		System.out.println("nb de Threads demandés: "+args[0]);
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
				// permet de répartir équitablement le nombre de ligne dans les
				// threads
				i = (i++) % nThreads;
			}
			App[] apps = new App[nThreads];
			for (int j = 0; j < nThreads; j++) {
				apps[j] = new App();
			}

			Compteur[] compt = new Compteur[nThreads];

			for (int j = 0; j < nThreads; j++) {
				compt[j] = new Compteur(apps[j], lines[j].toString());
				compt[j].num();
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

			for (int j = 1; j < nThreads; j++) {
				apps[0].merge(apps[j]);
			}

			Map.Entry<String, Integer> max = apps[0].findMax();
			System.out.println(max);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}