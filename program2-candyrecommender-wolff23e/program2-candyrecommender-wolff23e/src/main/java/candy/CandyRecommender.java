package candy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This program reads candies and their ingredients from a file.  It then asks
 * the user which of those ingredients they like/dislike.  Finally, it rates
 * the candies and tells the user which ones they would probably like.
 */
public class CandyRecommender {
    
    // All known candies
    private static List<Candy> candies = new ArrayList<>();
    
    //The ingredients the user loves
    private static List<String> loves = new ArrayList<>();

    // The ingredients the user likes
    private static List<String> likes = new ArrayList<>();

    // The ingredients the user dislikes
    private static List<String> dislikes = new ArrayList<>();

    // A map from ingredients to candies containing the ingredient.
    private static IngredientMap ingredientMap = new IngredientMap();



    /**
     * @return candies
     */
    public static List<Candy> getCandies() {
        return candies;
    }

    /**
     * @return ingredientMap
     */
    public static IngredientMap getIngredientMap() {
        return ingredientMap;
    }



    /**
     * @param newLikes
     */
    public static void setLikes(List<String> newLikes) {
        likes = newLikes;
    }

    /**
     * @return likes
     */
    public static List<String> getLikes(){
        return likes;
    }

    /**
     * @param newDislikes
     */
    public static void setDislikes(List<String> newDislikes) {
        dislikes = newDislikes;
    }

  /**
     * @return dislikes
     */
    public static List<String> getDislikes(){
        return dislikes;
    }
    /**
     * resets the global variables in CandyRecommender
     */
    public static void resetCandyRecommender() {
        candies = new ArrayList<>();
        likes = new ArrayList<>();
        dislikes = new ArrayList<>();
        ingredientMap = new IngredientMap();
    }
    /**
     * Read the candy information from the file called Candy.txt.  The file
     * should be formatted like this:  candy name:ingredient 1, ingredient 2
     * Initializes the candies list and the ingredientMap.
     * @throws FileNotFoundException if the Candy.txt file cannot be found.
     */
    static void readCandyFile(String filename) throws FileNotFoundException {
        try (Scanner in = new Scanner (new File (filename))) {
            // Read in each candy
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parsedLine = line.split(":");
                String name = parsedLine[0];
                String[] ingredients;
                try {
                    ingredients = parsedLine[1].split(",");
                }catch (ArrayIndexOutOfBoundsException e) {
                    ingredients = new String[0];
                }
                Candy candy = new Candy( name, ingredients);
                candies.add(candy);
                // Add the information to the ingredient map.
                //after one iteration, the ingredient map is corrupted as
                //each node goes "INGREDIENT", with a key of the Ingredient
                //an arrist list as a value, with the 0 place being
                //two variables of candy being name and ingredients
                for (String ingredient : ingredients) {
                    //not right
                    
                    ingredientMap.add(ingredient, candy);
                }
            }
        }
        System.out.println("Read " + candies.size() +
                           " candies from the file.");
    }

    /**
     * For each ingredient found in any candy, ask the user if they like,
     * dislike, or are neutral about that ingredient.
     */
    private static void getUserPreferences() {
        try (Scanner in = new Scanner (System.in)) {

            // Ask the user about each ingredient
            for (String ingredient : ingredientMap.ingredients()) {
                System.out.print ("How much do you like " + ingredient +
                            "? (1-like, 0-ok, -1-dislike) ");

                String answer = in.nextLine();
                try {
                    int pref = Integer.parseInt(answer);
                    switch (pref) {
                    case 1:
                        likes.add(ingredient);
                        break;
                    case -1:
                        dislikes.add(ingredient);
                        break;
                    default:
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter 1, 0, or -1");
                }
            }
        }
    }

    /**
     * Recommend candies that you think the user will like based upon their
     * likes and dislikes and what the candies contain.  It will not suggest
     * any candidates that contain ingredients the user dislikes.  It will
     * give a score to candies that contain only ingredients the user likes.
     * A higher score is better.
     * @return the candies that this user would probably like
     */
    static Set<Candy> findLikedCandies() {
        Set<Candy> possible = new HashSet<>();
        //even sorts some things without the right ingred. i.e. for fruit
        // peanut m and m hershey reeces etc were added but are not
        //fruit samd with coco not etc
        // just not working corrrectly
        for (String ingredient : likes) {
            for(Candy c : ingredientMap.getCandyWith(ingredient)){
            //need to find ingredientMap asap this is the error
                possible.addAll(ingredientMap.getCandyWith(ingredient));
            }
        }
        for (String ingredient : dislikes) {
            possible.removeAll(ingredientMap.getCandyWith(ingredient));
        }
        return possible;
    }

    /**
     * Outputs the list of candies to the screen
     * @param possible the candies to output
     */
    private static void recommendCandies(Set<Candy> possible) {
        if (possible.size() == 0) {
            System.out.println("Too picky! No reccomendations for you.");
        }
        for (Candy c: possible) {
            System.out.println(c.getName() + ": " + c.score(likes));
        }
    }

    /**
     * Reads the candies from a file, asks the user for their likes and
     * dislikes, and makes a recommendation
     * @param args none
     */
    public static void main(String[] args) {
        try {
            readCandyFile("/Users/emmawolff/Documents/college/senior/software design and dev/homework2su/program2-candyrecommender-wolff23e/program2-candyrecommender-wolff23e/Candy.txt");
            getUserPreferences();
            Set<Candy> likedCandies = findLikedCandies();
            recommendCandies(likedCandies);
        } catch (FileNotFoundException e) {
            System.out.println ("Unable to read the Candy.txt file");
        }
    }

}
