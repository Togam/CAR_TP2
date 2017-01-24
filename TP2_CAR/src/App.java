import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author six
 * 
 */
public class App {
	HashMap<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * @param line
	 */
	public void countWords(String line) {
		// System.out.println(line);
		String[] words = line.split("\\s");
		for (String w : words) {
			if (map.containsKey(w))
				map.put(w, new Integer(map.get(w).intValue() + 1));
			else
				map.put(w, new Integer(1));
		}
	}

	/**
	 * @return
	 */
	public Map.Entry<String, Integer> findMax() {
		Map.Entry<String, Integer> maxEntry = null;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (maxEntry == null
					|| entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return maxEntry;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Word Counter!");
		if (args.length < 1) {
			System.err.println("Usage: \napp <filename>");
			System.exit(-1);
		}

		App myapp = new App();

		try {

			InputStream fis = new FileInputStream(args[0]);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String line = null;

			while ((line = br.readLine()) != null) {
				myapp.countWords(line);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

		Map.Entry<String, Integer> maxEntry = myapp.findMax();

		System.out.println("Most frequent word: " + maxEntry.getKey()
				+ " with " + maxEntry.getValue() + " occurrences");
	}
}