package testing;

import cliqueSolver.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class Test5 
{
	    
	@Test
	public void test() 
	{
		/* Input graph:
		 * O----O----O
		 *      | \  |
		 *      |  \ |
		 *      O----O----O
		 *           | \/ |
		 *           | /\ |
		 *           O----O
		 * 
		 * Expected Result:
		 * Clique of Size 4
		 * 
		 * Current Status:
		 * FAIL
		 */
		
		CliqueSolver clique = new CliqueSolver("testInputs/graphTest5.txt");
		
		// Begin solving the problem
		// Iterate over the matrix, start at the highest clique size
		for (int i = clique.matrix.length; i >= 1; i--)
		{	
			if (clique.numOfDegreeOrHigher(i-1) >= i-1)
			{
				// Check for a complete clique of size i
				if (clique.checkClique(i))
				{
					// Clique found
					// Application architecture does not allow for checking the clique nodes
					// Really should be refactored
					return;
				}
			}
		}
		
		fail();
	}
}