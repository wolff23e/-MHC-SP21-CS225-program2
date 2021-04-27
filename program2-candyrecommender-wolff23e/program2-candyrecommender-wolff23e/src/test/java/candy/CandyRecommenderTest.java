package candy;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


class CandyRecommenderTest {
    

    private ArrayList<String> likes1 = new ArrayList<String>();//stays empty
    private ArrayList<String> likes2 = new ArrayList<String>();
    private ArrayList<String> likes3 = new ArrayList<String>();
    private ArrayList<String> dislikes1 = new ArrayList<String>();//stays empty
    private ArrayList<String> dislikes3 = new ArrayList<String>();
//	private ArrayList dislikes1 = new ArrayList<String>(Arrays.asList("PEANUT BUTTER","COCONUT"));


    //This is for Our Candy.txt file w/ 20 candys, for default txt file, should be...
    private static int NUM_CANDIES = 20;// --> 18 for default Candy.txt
    private static int NUM_INGRED = 12;// --> 11 for default Candy.txt


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
    void testReadCandyFile() {
        /*
         * Purpose: This test checks to see if readCandyFile() worked with the Candy.txt file.
         * The constants are set to check specifically for Candy.txt and if other filenames are
         * used, update the constants to reflect that file.
         * Method: readCandyFile(), ingredients()
         * Initialization: calling readCandyFile()
         * Parameters: "Candy.txt" filename
         * Correct result: ArrayList<String> candies should have a size of 18 and the size
         * of the ingredients list from the ingredientMap should be 11.
         */
        assertTrue(CandyRecommender.getCandies().size() == NUM_CANDIES);
        assertTrue(CandyRecommender.getIngredientMap().ingredients().size() == NUM_INGRED);
    }

    @Test
    void testFLCNoLikes() {
        /*
         * Purpose: This tests findLikedCandies() when there are no likes and no dislikes.
         * Method: findLikedCandies()
         * Initialization: calling readCandyFile() with "Candy.txt", setting likes and dislikes to
         * likes1 and dislikes1
         * Parameters: none
         * Correct result: possible should be empty because if there are no likes, the
         * program cannot recommend anything.
         */
        CandyRecommender.setLikes(likes1);
        CandyRecommender.setDislikes(dislikes1);
        Set<Candy> possible = new HashSet<Candy>();
        possible = CandyRecommender.findLikedCandies();
        assertTrue(possible.isEmpty());
    }

    @Test
    
    void testFLCLikesAll() {
        /*
         * Purpose: This tests findLikedCandies() when the user likes all ingredients and no dislikes.
         * Method: findLikedCandies()
         * Initialization: calling readCandyFile() with "Candy.txt", setting likes to likes2 and dislikes
         * to dislikes 1
         * Parameters: none
         * Correct result: possible should contain all candies, i.e. possible.size()
         * should equal NUM_CANDIES
         */
        CandyRecommender.setLikes(likes2);
        CandyRecommender.setDislikes(dislikes1);
        Set<Candy> possible = new HashSet<Candy>();
        possible = CandyRecommender.findLikedCandies();
        assertTrue(CandyRecommender.getLikes().size() == NUM_INGRED);
        assertTrue(possible.size() == NUM_CANDIES-1); //<-- minus one for candy without ingredients
    }

    @Test
    void testFLCDislikesAll() {
        /*
         * Purpose: This tests findLikedCandies() when the user dislikes all ingredients
         * Method: findLikedCandies()
         * Initialization: calling readCandyFile() with "Candy.txt", setting likes to likes1
         * and dislikes to likes2
         * Parameters: none
         * Correct result: possible should be empty because when the user dislikes all ingredients,
         * there are no candies to recommend.
         */
        CandyRecommender.setLikes(likes1);
        CandyRecommender.setDislikes(likes2);
        Set<Candy> possible = new HashSet<Candy>();
        possible = CandyRecommender.findLikedCandies();
        assertTrue(possible.isEmpty());
    }

    @Test
    void testFLClikesSomeDislikesNone() {
        /*
         * Purpose: This tests findLikedCandies() when the user only likes CHOCOLATE, PEANUTS, and FRUIT
         * Method: findLikedCandies()
         * Initialization: calling readCandyFile() with "Candy.txt", setting likes to likes3
         * and dislikes to dislikes1
         * Parameters: none
         * Correct result: possible should contain 16 candies, 15 for default Candy.txt
         */
        CandyRecommender.setLikes(likes3);
        CandyRecommender.setDislikes(dislikes1);
        Set<Candy> possible = new HashSet<Candy>();
        possible = CandyRecommender.findLikedCandies();
        // currently working for 19,
        assertTrue(possible.size() == 16);
    }

    @Test
    void testFLClikesSomeDislikesSome() {
        /*
         * Purpose: This tests findLikedCandies() when the user only likes CHOCOLATE, PEANUTS, and FRUIT
         * and dislikes PEANUT BUTTER, TOFFEE, PEPPERMINT, and COCONUT
         * Method: findLikedCandies()
         * Initialization: calling readyCandyFile() with "Candy.txt", setting likes to likes3 and
         * dislikes to dislikes3
         * Parameters: none
         * Correct result: possible should contain 12 candies, 11 using default Candy.txt
         */
        Set<Candy> possible = new HashSet<Candy>();
        CandyRecommender.setLikes(likes3);
        CandyRecommender.setDislikes(dislikes3);
        possible = CandyRecommender.findLikedCandies();
        System.out.println(CandyRecommender.getDislikes());
        assertTrue(possible.size() == 12);
    }






}
