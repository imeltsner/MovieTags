<h2>Movie Tags</h2>
The purpose of this project is to read a csv file containing movie data. The program sorts the data and outputs the most popular and least popular 
movie tags. The program then prompts the user to search by tag or by count, and outputs the count of the given tag, or all tags that match the given count. (Note: for this project, we could not use Hashmaps or the Collections module)
<h3>Complexity Analysis</h3>
<h4>Reading the data file</h4>
Reading the data file is done in O(n^2) time. Each line of the csv is read and the movie tag is parsed from the data. If the tag has not been seen yet,
it is stored in 3 different ArrayLists. The first ArrayList contains the string representation of each unique tag. The second ArrayList contains a Category object that stores the movie tag and the associated count. The third ArrayList is a duplicate of the second, but it will later be sorted. This portion of the read is O(n). If the tag has been seen, then the count of the Category object holding the tag gets incremented. The incrementation happens in O(n) time, because it uses indexOf(), which is an O(n) function. 
<h4>Listing popular tags</h4>
Outputting the popular tags happens in O(n^2) time. I used a selection sort to sort the ArrayList. I then used .get() to output the first three and last three tags from the sorted list. 
<h4>Search by Tag</h4>
Searching by tag is done in O(n) time. It uses the indexOf() function to find the position of the correct tag in the ArrayList, which is an O(n) function. 
<h4>Search by Count</h4>
Searching by count is done in O(n) time. I used a linear search through a sorted list. 

