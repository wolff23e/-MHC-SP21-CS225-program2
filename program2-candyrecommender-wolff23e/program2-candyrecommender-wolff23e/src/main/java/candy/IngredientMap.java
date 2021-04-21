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
    private HashMap<String, List<String>> memaMap  = new HashMap<String, List<String>>();
    private List<String> names;

    public List<Candy> newList = new ArrayList<>();
    /**
     * Adds the candy to the list for the given ingredient
     * @param ingredient the ingredient whose list is changed
     * @param candy the candy to add to the list
     */
    public void add(String ingredient, Candy candy) {
        // Initialization of a HashMap
        // using Generics
        //if the map does not have this key 
  //      if( memaMap.containsKey(ingredient) == false){
 //           names.add(candy.getName());
//            //add key and candy name
  //          memaMap.put(ingredient, names);
            //but if it already has a key
 //       } else {
 //           names.add(candy.getName());
 //           memaMap.put(ingredient, names);
  //      }
  
        newList.add(candy);
        ingredientMap.put(ingredient, newList);
     //       if (ingredientMap.isEmpty() == true){
     //          ingredientMap.put(ingredient, candy.getName());
                //if this is not a key yet);
                //ingredientMap = memaMap;
            }
                //if this is not a key yet
           // } else if {entMap.containsKey(thing) != true);
       //broken add
       //only has 12 candies
     //   newList.add(candy);
        //inseting too many nodes to newList second variable
    //    ingredientMap.put(ingredient, newList);
        
    

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
    //shows me ingredient map is broken as 
    //each hash map node for the ingredient as key 
    //and value as array list containing 31 candies
    //not just the candies that have this ingredient
    //local variable newList Array list has the candies
    // and ingredients
    public List<Candy> getCandyWith(String ingredient) {
        //already broken when it arrives here
        System.out.println(ingredientMap.get(ingredient));
        //returns all
        return ingredientMap.get(ingredient);
        
    }
}
