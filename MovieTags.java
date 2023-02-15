
public class MovieTags {
    public static void main(String[] args) {
        String filePath = args[0];
        Tagsort sorter = new Tagsort(filePath);
        sorter.sort();
    }
}
