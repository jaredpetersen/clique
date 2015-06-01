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
		
			String row[] = scanner.next().split("\\W+");
				
			matrix = new int[row.length][row.length];
			
			int index = 0;
			
			for(int i = 0; i< row.length; i++)
				matrix[index][i] = Integer.parseInt(row[i]);

			while(scanner.hasNext())
			{
				index++;
				row = scanner.next().split("\\W+");
				for(int i = 0; i< row.length; i++)
					matrix[index][i] = Integer.parseInt(row[i]);
			}
			
			scanner.close();
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		//print what's in Matrix
		for (int i = 0; i< matrix.length; i++){
			for(int j = 0; j< matrix.length; j++)
				System.out.print(matrix[i][j]);
			System.out.print('\n');
		}
		
		System.out.println('\n');
		for(int i = 0; i< matrix.length; i++)
			System.out.println("Degree of " + i + " = " + degree(i));
	}
	
	public int degree(int index){
		int deg = 0;
		for(int i = 0; i< matrix.length; i++)
			deg += matrix[index][i];
		return deg;
	}
}