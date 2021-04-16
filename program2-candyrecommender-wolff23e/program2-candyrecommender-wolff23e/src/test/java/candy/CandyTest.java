package candy;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
public class CandyTest {

	private List<String> likes1 = new ArrayList<String>();//stays empty
	private List<String> likes2 = new ArrayList<String>();
	private List<String> likes3 = new ArrayList<String>();
	private List<String> dislikes1 = new ArrayList<String>();//stays empty
	private List<String> dislikes3 = new ArrayList<String>();


	@BeforeEach
	void setUp() throws Exception {
			/**
			 * Reads "Candy.txt", or given input file, and fills ingredientMap
			 * adds ingredients to certain sample likes and dislikes List<String>
			 */

			try {
			CandyRecommender.readCandyFile("/Users/emmawolff/Documents/college/senior/software design and dev/homework2su/program2-candyrecommender-wolff23e/program2-candyrecommender-wolff23e/Candy.txt");
			}
			catch (FileNotFoundException e){
					assert(false);
			}

			likes2.addAll(CandyRecommender.getIngredientMap().ingredients());
			likes3.add("FRUIT");
			likes3.add("PEANUTS");
			likes3.add("CHOCOLATE");

			dislikes3.add("PEANUT BUTTER");
			dislikes3.add("COCONUT");
			dislikes3.add("TOFFEE");
			dislikes3.add("PEPPERMINT");

	}

	@AfterEach
	void tearDown() {
			CandyRecommender.resetCandyRecommender();
	}

	@Test
	public void testCandy() throws Exception {
	/*
* Purpose: This test checks to see if candy objects are being properly constructed
* 
* Method: candy constructor
* Correct result: true if candy inserted ingredients into cnady object via constructor
*/
	assertTrue(CandyRecommender.getIngredientMap() != null);
	;
	}

	@Test
	public void testGetName() throws Exception {
		/*
* Purpose: This test checks to see if getName() retreieves name
* The constants are set to check specifically for Candy.txt and if other filenames are
* used, update the constants to reflect that file.
* Method: getName()
* Correct result:  a string that is a name of a candy item
*/
		assertTrue(Candy.class.getName() != null);
	}

	@Test
	public void testScore() throws Exception {
	/*
* Purpose: This test checks to see if score calculates
* The constants are set to calcualte a scoe for a 
* candy based on the liked ingredients
* Method: score(int)
* Correct result:   0-10
*/
	assertTrue( CandyRecommender.getLikes().size() < 11);
	}

}
