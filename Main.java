import java.io.FileNotFoundException;


public class Main {

	public static void main(String args[]){
		try {
			Clique c = new Clique();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
