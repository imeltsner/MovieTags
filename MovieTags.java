import java.util.Scanner;
/*
 * A class to read a csv containing movie tags. Tags are sorted alphabetically. The most popular and least popular tags by frequency are printed.
 * The user can then choose to search by tag or by count. If they search by tag, the frequency of the given tag is printed. If they search by 
 * count, all tags with the given count are output. 
 * @author Isaac Meltsner 
 */
public class MovieTags {
    public static void main(String[] args) {
        String filePath = args[0];
        Tagsort sorter = new Tagsort(filePath);
        sorter.sort();
        sorter.printPopularity();
        Scanner scan = new Scanner(System.in);
        System.out.println("Search by Tag or Tag Count? (Enter T or C... or EXIT to exit):");
        while (scan.hasNext()) {
            String userInput = scan.next().toUpperCase();
            scan.nextLine();
            if (userInput.equals("T")) {
                System.out.println("Enter tag to search for: ");
                String tag = scan.nextLine();
                sorter.searchByTag(tag);
            } else if (userInput.equals("C")) {
                System.out.println("Enter count to search for: ");
                String tag = scan.next();
                try {
                    int num = Integer.parseInt(tag);
                    sorter.searchByCount(num);
                } catch (NumberFormatException e) {
                    System.out.println(tag + " is not a number");
                }
            } else if (userInput.equals("EXIT")) {
                scan.close();
                System.exit(0);
            } else {
                System.out.println("invalid input");
            }
            System.out.println("Search by Tag or Tag Count? (Enter T or C... or EXIT to exit):");
        }
    }
}
