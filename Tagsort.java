import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

//Reads a list of movie tags from a CSV
public class Tagsort {
    ArrayList<String> seenTags = new ArrayList<String>();
    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayList<Category> sortedCategories = new ArrayList<Category>();

    Tagsort(String filePath) {
        try {
            boolean firstLine = true;
            Scanner scan = new Scanner(new File(filePath));
            while (scan.hasNextLine()) {
                if (!firstLine) {
                    String tag = scan.nextLine().split(",")[2];
                    if (!seenTags.contains(tag)) {
                        seenTags.add(tag);
                        Category movie = new Category(tag);
                        categories.add(movie);
                        sortedCategories.add(movie);
                    } else {
                        categories.get(seenTags.indexOf(tag)).addOne();
                        sortedCategories.get(seenTags.indexOf(tag)).addOne();
                    }
                }
                if (firstLine) {
                    firstLine = !firstLine;
                } 
            }
            scan.close();
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
        }
    }

    public void swap(int i, int j) {
        Category temp = sortedCategories.get(j);
        sortedCategories.set(j, sortedCategories.get(i));
        sortedCategories.set(i, temp);
    }

    public int findSmallest(int start) {
        //returns index of Largest value in array
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

    public void sort() {
        for (int i = 0; i < sortedCategories.size(); i ++) {
            swap(i, findSmallest(i)); //swaps the largest value with the value at i
        }
        /*
        for (int i = 0; i < 50; i++) {
            sortedCategories.get(i).print();
        } */
    }

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

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<String> getTagList() {
        return seenTags;
    }
}
