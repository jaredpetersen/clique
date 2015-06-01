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
		
		for(int i = matrix.length; i >= 1; i--)
			if(numOfDegreeOrHigher(i-1) >= i-1)
				if(checkClique(i))
					break;// this means we found one
				//System.out.println(numOfDegreeOrHigher(i+1) + 
				//	" vertices have degree " + (i+1) + " or higher");
	}
	
	private void InitDegree(){
		degree = new int[matrix.length];
		int deg = 0;
		for(int i = 0; i< matrix.length; i++) {
			deg = 0;
			for(int j = 0; j < matrix.length; j++)
				deg += matrix[i][j];
			degree[i] = deg;
		}
	}
	
	private int numOfDegreeOrHigher(int deg)
	{
		int num = 0;
		for(int i = 0; i < matrix.length; i++)
			if(degree[i] >= deg)
				num++;
		return num;
	}
	
	private void printMatrix()
	{
		//print what's in Matrix
		for (int i = 0; i< matrix.length; i++){
			for(int j = 0; j< matrix.length; j++)
				System.out.print(matrix[i][j]);
			System.out.print('\n');
		}
	}
	
	// input to this method is the number of nodes in the clique
	private boolean checkClique(int size) {
		System.out.println("checking for a clique of size " + size + "...\n");
		int nodes[] = new int[numOfDegreeOrHigher(size-1)];
		
		int count = 0;
		for(int i = 0; i< degree.length; i++)
			if(degree[i] >= size-1){
				nodes[count] = i;
				count++;
			}
		
		return combination(nodes, size);
		
		//return checkCurrClique(nodes, size);
	}
	
	private boolean checkCurrClique(int[] nodes, int size)
	{
		boolean failed = false;
		for(int i = 0; i < nodes.length-1; i++) // the last node NEVER needs to be checked
		{
			for (int j = i+1; j < nodes.length-1; j++)
				if(matrix[nodes[i]][nodes[j]] == 0)
					failed = true;
			if(failed)
				break;
		}
		if(failed)
			failed=false;
		else{
			System.out.println("A clique exists of size " + size);
			System.out.print("Nodes included: " + (nodes[0]+1));
			for(int i = 1; i < nodes.length; i++)
				System.out.print(", " + (nodes[i]+1));
			System.out.println('\n');
			return true;
		}
		return false;
	}
	
	private void InitMatrix(){
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
	
	private boolean combination(int[] arr, int r)
	{
		int[] res = new int[r];
		return combine(arr, res, 0, 0, r);
	}
	
	private boolean combine(int[] arr, int[] res, int currIndex, int level, int r) {
        if(level == r){
        	if(checkCurrClique(res, r))
				return true;
        	return false;
        }
        for (int i = currIndex; i < arr.length; i++) {
            res[level] = arr[i];
            if(combine(arr, res, i+1, level+1, r))
    			return true;
            //way to avoid printing duplicates
            if(i < arr.length-1 && arr[i] == arr[i+1]){
                i++;
            }
        }
        return false;
    }
}