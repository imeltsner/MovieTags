/*
 * A class to hold a movie tag and a count
 * @author Isaac Meltsner
 */
public class Category {
    String tag = new String();
    int count;

    Category(String movieTag) {
        tag = movieTag;
        count = 1;
    }

    void addOne() {
        count++;
    }

    void print() {
        System.out.println(count + ": " + tag);
    }

    int getCount() {
        return count;
    }

    String getTag() {
        return tag;
    }
}
