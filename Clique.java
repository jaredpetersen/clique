import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// MTH 354 group project

public class Clique {
	private String fileName;
	private int[] input;
	private Scanner s;
	
	public Clique() throws FileNotFoundException{
		fileName = "graph.txt";
		
		try{
			s = new Scanner(new FileReader(fileName)).useDelimiter("\n");
		
		//System.out.println(s.toString());
		//for int i = 0;
		while(s.hasNext()){
			//int i = s.nextInt();
			System.out.println(s.next());
		}
		
		
		s.close();
		}
		catch(FileNotFoundException e){
			
		}
	}
}
