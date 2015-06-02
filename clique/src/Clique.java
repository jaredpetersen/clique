import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Clique Class
 * Used to find the largest clique in the given graph
 * @author EthanEiter, jshin13wou, jaredpetersen
 */
public class Clique 
{
	private String fileName; // Input file name
	private Scanner scanner; // File Scanner
	int matrix[][]; 		 // 2D array used to store the adjacency matrix
	int degree[]; 			 // degree array
	
	/**
	 * Clique Constructor
	 */
	public Clique()
	{
		// Get the graph in the form of an adjacency matrix
		InitMatrix();
		// Get the degrees for each vertex
		InitDegree();
		
		// Begin solving the problem
		// Iterate over the matrix, start at the highest clique size
		for (int i = matrix.length; i >= 1; i--)
		{	
			if (numOfDegreeOrHigher(i-1) >= i-1)
			{
				// Check for a complete clique of size i
				if (checkClique(i))
				{
					// Found the clique, stop looking
					break;
				}
			}
		}
	}
	
	/**
	 * Set a 2D array that matches the input file
	 */
	@SuppressWarnings("resource")
	private void InitMatrix()
	{
		// Grab the input file
		// File needs to be in the folder just above where the code is located
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
		
		catch (FileNotFoundException e)
		{
			// File does not exist
			System.out.println("Oops! It looks like we couldn't find your input file.");
			System.out.println("Please read the documentation for more information.");
			
			// Shut the program down with an error
			System.exit(1);
		}
	}
	
	/**
	 * Set an array of the degrees for each vertex
	 */
	private void InitDegree()
	{
		degree = new int[matrix.length];
		int deg = 0;
		for (int i = 0; i < matrix.length; i++) 
		{
			deg = 0;
			for (int j = 0; j < matrix.length; j++)
			{
				deg += matrix[i][j];
			}
			degree[i] = deg;
		}
	}
	
	/**
	 * Finds the number of vertices with the input degree or higher
	 * @param deg The degree
	 * @return The number of the vertices with the input degree or higher
	 */
	private int numOfDegreeOrHigher(int deg)
	{
		int num = 0;
		for (int i = 0; i < matrix.length; i++)
		{
			if(degree[i] >= deg)
			{
				num++;
			}
		}
		return num;
	}
	
	/**
	 * Prints the input matrix
	 * Used for debugging
	 */
	@SuppressWarnings("unused")
	private void printMatrix()
	{
		//print what's in Matrix
		for (int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				System.out.print(matrix[i][j]);
			}
			System.out.print('\n');
		}
	}
	
	/**
	 * Check if there is a complete clique with the input size
	 * @param size The number of nodes in the clique
	 * @return True if there is a complete clique with the input size
	 */
	private boolean checkClique(int size) 
	{
		/* Nodes used to store the nodes that fit the degree requirements
		   i.e. if we're looking for a clique of size 4, don't include nodes that only have 
		   a degree of 1 because there's no way it could be in the complete clique */
		int nodes[] = new int[numOfDegreeOrHigher(size-1)];
		int count = 0;
		// Get the previously discussed nodes that fit the degree requirements
		for (int i = 0; i < degree.length; i++)
		{
			if (degree[i] >= size-1)
			{
				nodes[count] = i;
				count++;
			}
		}
		
		// Helper method called, used to check all of the combinations of the nodes array
		// Uses brute force, but cuts down on the number of nodes that need to be checked
		return combination(nodes, size);
	}
	
	/**
	 * 
	 * @param nodes
	 * @param size
	 * @return
	 */
	private boolean checkCurrClique(int[] nodes, int size)
	{
		boolean failed = false;
		for (int i = 0; i < nodes.length-1; i++) // the last node NEVER needs to be checked
		{
			for (int j = i+1; j < nodes.length-1; j++)
			{
				if (matrix[nodes[i]][nodes[j]] == 0)
				{
					failed = true;
				}
			}
			if (failed)
			{
				break;
			}
		}
		
		if (failed)
		{
			failed=false;
		}
		else
		{
			// Found the largest complete clique
			System.out.println("The largest clique is of size " + size);
			System.out.print("Nodes included: " + (nodes[0]+1));
			for(int i = 1; i < nodes.length; i++)
			{
				System.out.print(", " + (nodes[i]+1));
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param arr
	 * @param r
	 * @return
	 */
	private boolean combination(int[] arr, int r)
	{
		int[] res = new int[r];
		return combine(arr, res, 0, 0, r);
	}
	
	/**
	 * 
	 * @param arr
	 * @param res
	 * @param currIndex
	 * @param level
	 * @param r
	 * @return
	 */
	private boolean combine(int[] arr, int[] res, int currIndex, int level, int r) {
        if (level == r)
        {
        	if(checkCurrClique(res, r))
        	{
				return true;
        	}
        	return false;
        }
        for (int i = currIndex; i < arr.length; i++) 
        {
            res[level] = arr[i];
            if (combine(arr, res, i+1, level+1, r))
            {
    			return true;
            }
            // Avoid printing duplicates
            if (i < arr.length-1 && arr[i] == arr[i+1])
            {
                i++;
            }
        }
        return false;
    }
}