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
	// degree array
	int degree[];
	
	// Constructor
	public Clique()
	{
		InitMatrix();
		InitDegree();
		
		//printMatrix();
		
		//for(int i = 0; i< matrix.length; i++)
		//	System.out.println("Degree of " + i + " = " + degree(i));
		
		for(int i = 0; i< matrix.length; i++)
			if(numOfDegreeOrHigher(i+1) >= i+1)
				System.out.println(numOfDegreeOrHigher(i+1) + 
					" vertices have degree " + (i+1) + " or higher");
	}
	
	public void InitDegree(){
		degree = new int[matrix.length];
		int deg = 0;
		for(int i = 0; i< matrix.length; i++) {
			deg = 0;
			for(int j = 0; j < matrix.length; j++)
				deg += matrix[i][j];
			degree[i] = deg;
		}
	}
	
	public int numOfDegreeOrHigher(int deg)
	{
		int num = 0;
		for(int i = 0; i < matrix.length; i++)
			if(degree[i] >= deg)
				num++;
		return num;
	}
	
	public void printMatrix()
	{
		//print what's in Matrix
		for (int i = 0; i< matrix.length; i++){
			for(int j = 0; j< matrix.length; j++)
				System.out.print(matrix[i][j]);
			System.out.print('\n');
		}
	}
	
	public void InitMatrix(){
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
	}
}