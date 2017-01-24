/**
 * @author six
 * 
 */
public class Compteur extends Thread {

	public App app;
	String text;
	
	/**
	 * Constructeur
	 * @param a
	 * @param t
	 */
	public Compteur(final App a, final String t) {
		this.app = a;
		this.text = t;
	}
	
	/**
	 * 
	 */
	public void num(){
		String array[]= text.split("\\n");
		for(String l : array){
//			app.countWords(l);
		}
	}

}
