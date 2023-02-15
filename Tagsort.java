import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

//Reads a list of movie tags from a CSV and sorts them lexicographically
public class Tagsort {
    ArrayList<String> tags = new ArrayList<String>();

    Tagsort(String filePath) {
        try {
            boolean firstLine = true;
            Scanner scan = new Scanner(new File(filePath));
            int c = 0;
            while (scan.hasNextLine()) {
                String tag = scan.nextLine().split(",")[2];
                tags.add(tag);
                if (firstLine) {
                    tags.clear();
                    firstLine = !firstLine;
                }
                
                c++;
                if (c > 10000) {
                    break;
                }
            }
            scan.close();
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
        }
    //    System.out.println("File Read Success: tags[0] is " + tags.get(0));
    }

    public void swap(int i, int j) {
        String temp = tags.get(j);
        tags.set(j, tags.get(i));
        tags.set(i, temp);
    }

    public int findSmallest(int start) {
        //returns index of smallest value in array
        int smallestIndex = start;
        for (int i = start+1; i < tags.size(); i++) {
            if (tags.get(i).compareTo(tags.get(smallestIndex)) <= 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public void sort() {
        for (int i = 0; i < tags.size(); i ++) {
            swap(i, findSmallest(i)); //swaps the smallest value with the value at i
        }
        for (int i = 0; i < tags.size(); i++) {
            System.out.println(tags.get(i));
        }
    }

    public ArrayList<String> getList() {
        return tags;
    }
}
