package candy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Maps from ingredients to candies that contain the ingredient.
 */
public class IngredientMap {
    // The map being managed.
    //private HashMap<Candy> ingredientMap = new HashSet<Candy>;
    //HashMap<K, V> hm = new HashMap<K, V>();
    private HashMap<String, List<Candy>> ingredientMap  = new HashMap<String, List<Candy>>();
    private List<String> names;

   // public List<Candy> newList = new ArrayList<>();
    /**
     * Adds the candy to the list for the given ingredient
     * @param ingredient the ingredient whose list is changed
     * @param candy the candy to add to the list
     */
    public void add(String ingredient, Candy candy) {

        List<Candy> newList = new ArrayList<>();
        if(ingredientMap.containsKey(ingredient)){
            newList.addAll(ingredientMap.get(ingredient));
        }
        newList.add(candy);
        ingredientMap.put(ingredient, newList);
    }
  
    

    /**
     * @return all the ingredients that appear in any candy.
     */
    public Set<String> ingredients() {
        return ingredientMap.keySet();
    }

    /**
     * 
     * @param ingredient an ingredient to look up
     * @return all candies that contain the ingredient.  Returns null if there 
     * are no candies with this ingredient.
     */

    public List<Candy> getCandyWith(String ingredient) {
        //already broken when it arrives here
        System.out.println(ingredientMap.get(ingredient));
        //returns all
        return ingredientMap.get(ingredient);
        
    }
}
