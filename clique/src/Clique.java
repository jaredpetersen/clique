import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 
 * @author jshin13wou, jaredpetersen
 *
 */
public class Clique 
{
	// Input file name
	private String fileName;
	// File scanner
	private Scanner scanner;
	// 2D array used to the matrix
	int matrix[][];
	
	// Constructor
	public Clique()
	{
		// Grab the input file
		fileName = "graph.txt";
		
		try
		{
			scanner = new Scanner(new FileReader(fileName)).useDelimiter("\n");
		
			while(scanner.hasNext())
			{
				String row[] = scanner.next().split(" ");
				
				// Just print out what's in the scanner
				System.out.println(row.length);
			}
			
			scanner.close();
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}