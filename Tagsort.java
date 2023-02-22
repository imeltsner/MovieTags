import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

/**
 * A class to read a csv file of movie tags and perform various sorting and searching operations on the data
 * @author Isaac Meltsner
 */
public class Tagsort {
    ArrayList<String> seenTags = new ArrayList<String>(); //contains each unique tag
    ArrayList<Category> categories = new ArrayList<Category>(); //contains each unique tag/count pair
    ArrayList<Category> sortedCategories = new ArrayList<Category>(); //contains each unique tag count pair but sorted

    /**
     * Reads csv and populates ArrayLists
     * @param filePath -> the filepath where the csv is located
     */
    Tagsort(String filePath) {
        try {
            boolean firstLine = true;
            Scanner scan = new Scanner(new File(filePath));
            while (scan.hasNextLine()) {
                if (!firstLine) {
                    String tag = scan.nextLine().split(",")[2].trim(); //get movie tag from each line of csv
                    if (!seenTags.contains(tag)) { //if tag hasn't been seen add to lists
                        seenTags.add(tag);
                        Category movie = new Category(tag);
                        categories.add(movie);
                        sortedCategories.add(movie);
                    } else { //if tag has been seen increment counts
                        categories.get(seenTags.indexOf(tag)).addOne();
                        sortedCategories.get(seenTags.indexOf(tag)).addOne();
                    }
                }
                if (firstLine) { //ignore first line
                    firstLine = !firstLine;
                } 
            }
            scan.close();
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            System.exit(0);
        }
    }
    
    /**
     * Swaps arraylist elements at index i and j
     * @param i -> element at index i
     * @param j -> element at index j
     */
    public void swap(int i, int j) {
        Category temp = sortedCategories.get(j);
        sortedCategories.set(j, sortedCategories.get(i));
        sortedCategories.set(i, temp);
    }

    /** 
     * A function to find the index of the smallest value in an array
     * @return The index of the smallest value 
    */
    public int findSmallest(int start) {
        int smallestIndex = start;
        for (int i = start+1; i < sortedCategories.size(); i++) {
            if (sortedCategories.get(i).getCount() < sortedCategories.get(smallestIndex).getCount()) {
                smallestIndex = i;
            }
            if (sortedCategories.get(i).getCount() == sortedCategories.get(smallestIndex).getCount()) {
                if (sortedCategories.get(i).getTag().compareTo(sortedCategories.get(smallestIndex).getTag()) <= 0) {
                    smallestIndex = i;
                }
            }
        }
        return smallestIndex;
    }

    /**
     * An implementation of selection sort
     */
    public void sort() {
        for (int i = 0; i < sortedCategories.size(); i ++) {
            swap(i, findSmallest(i)); //swaps the smallest value with the value at i
        }
    }

    /**
     * Prints the three most popular and three least popular tags in the csv file
     */
    public void printPopularity() {
        System.out.println("*** Highest 3 movies by count ***");
        sortedCategories.get(sortedCategories.size()-1).print();
        sortedCategories.get(sortedCategories.size()-2).print();
        sortedCategories.get(sortedCategories.size()-3).print();
        System.out.println("*** Lowest 3 movies by count");
        sortedCategories.get(0).print();
        sortedCategories.get(1).print();
        sortedCategories.get(2).print();
        
    }

    /**
     * Prints the frequency of the given tag
     * @param tag -> the tag to search for
     */
    void searchByTag(String tag) {
        int index = seenTags.indexOf(tag); //the index of the tag in seenTags matches the index of the category object in categories
        if (index != -1) {
            int count =  categories.get(index).getCount();
            System.out.println("Tag " + tag + " occured " + count + " times."); 
        } else {
            System.out.println("Tag " + tag + " does not exist.");;
        }
    }

    /**
     * Prints the all tags whose count matches the given frequency using a linear search
     * @param count -> the given frequency
     */
    void searchByCount(int count) {
        boolean tagFound = false;
        System.out.println("Tags with " + count + " occurences:");
        for (int i = 0; i < sortedCategories.size(); i++) {
            //terminate loop if count > max value in list
            if (sortedCategories.get(i).getCount() > count || count > sortedCategories.get(sortedCategories.size()-1).getCount() || count <= 0) {
                break;
            }
            if (sortedCategories.get(i).getCount() == count) {
                System.out.println("* " + sortedCategories.get(i).getTag());
                tagFound = true;
            }
        }
        if (!tagFound) {
            System.out.println("No tags with count " + count + " were found.");
        }
    }

    //getters
    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<String> getTagList() {
        return seenTags;
    }
}
