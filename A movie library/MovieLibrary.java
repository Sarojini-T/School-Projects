import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
/* This class encapsulates a list of movies in a user's collection and several
 * operations that can be performed on that list. A movie is represented 
 * by an instance of the Movie class. Each movie has the following fields:
 * a title, a director, an (optional) genre, a playing time, and a release year.
*/
   public class MovieLibrary {
   //Class member variable declaration(s).
   // This class should have two attributes:
   //     one stores an arrayList of movies.
   //     the other stores the name of the file the movies are read from
   //     and written into.
      private ArrayList<Movie> movieList;
      private String fileName;
  
  
   
   /* Constructor that initializes the list and any other 
   *  variables.
   */    
   public MovieLibrary(String fileName){
   // TODO 1: Implement this method.
       movieList = new ArrayList<Movie>();
       this.fileName = fileName;
     

   }
   
   /* Returns true if the movie list contains no movies, false otherwise.
    */
   public boolean isEmpty(){
   // TODO 2: Implement this method.
      if(movieList.size() == 0){
         return true;
      }
      return false;
   }

   // Todo B: readAllMovies
   // This method reads all lines from the CSV file named in attribute nameOfSourceFile
   // and creates an arrayList based on them.
   // For each line of the file, a different Movie object will be created
   // and added into the Object attribute called storageList.
   // This method should call method this.processALine as needed.
   public void readAllMovies() throws IOException {
       FileInputStream fis = new FileInputStream(fileName);
       Scanner sc = new Scanner(fis);
       while(sc.hasNextLine()){
         Movie m = this.processALine(sc.nextLine());
         if(m != null){
            movieList.add(m);
         }
       }
       sc.close();
            
  }

  // TODO A: procesALine
  // this method receives a string
  // with the contents of a single line from the file
  // and returns a Movie object.
  public Movie processALine(String aLine) {
      String[] line = aLine.split(",");
      if(line.length == 4){
         Movie movie1 = new Movie(line[0],line[1],Double.parseDouble(line[3]),Double.parseDouble(line[4]));
         
         return movie1;  
      }
      else if(line.length == 5){
         Movie movie2 = new Movie(line[0],line[1],line[2],Double.parseDouble(line[3]),Double.parseDouble(line[4]));
         return movie2; 
      }
      return null;
    }

   // TODO D: implement writeMoviesToFile   
   // this method will store the contents of the arrayList 
   // into the same file the movies were read from.   
   // This method uses this.getMovieListAsString as needed.
   public void writeMoviesToFile()throws IOException {
      FileOutputStream fos = new FileOutputStream(fileName);
      PrintWriter pw = new PrintWriter(fos);
      pw.print(this.getMovieListAsString());
      pw.close();
   }
      
   
   /* Add the movie passed in to the end of the list. 
    * For example, if the list contained: movie1, movie2,
    * the next movie added, movie3, would result in this list:
    * movie1, movie2, movie3.
   */
   public void addMovie(Movie newMovie){
   // TODO 3: Implement this method.
      movieList.add(newMovie);
   }
   
   /* This method returns a String which consists of the String
    * representation of each movie in the list.     
    * If the movie list is empty, the String "no movies" is returned.
   */
   public String getMovieListAsString(){
   // TODO C: Implement this method. 
      String sum = "";
      for(int i = 0; i < movieList.size(); i++){
         if(movieList.get(i)!= null){
            sum += movieList.get(i).toString() + "/n";
         }
      }
      if(movieList.isEmpty()){
         return "no movies";
      }
      return sum;   
   }
   
   
  /* Remove the movie in the movieList with the targetDirector.
   * First, the method searches for a movie in the list with a director that 
   * matches the targetDirector. If it is found, that movie is removed from 
   * the list. If the targetDirector is not matched, the list remains the same and false is returned.
   * Note that there should not be any null values between movies in the list.
   * For example, if the list contained: movie1, movie2, movie3,
   * and the title of movie2 was "director1", this call:  
   *          removeMovieByTitle("director1");
   * would result in this list:  movie1, movie3.
   * This method returns true if the targetDirector matches the director of a movie in the list,
   * false otherwise.
   */
   public boolean removeMovieByDirector(String targetDirector){ 
   // TODO 5: Implement this method.
      boolean hasRemoved = false;
      for (Movie m: movieList){
         if(m.getDirector().equals(targetDirector)){
            movieList.remove(m); 
            return true;  
         }
      }
      return false;  
   }
   
  /* 
   * Return the movie list object.
   */
   public ArrayList<Movie> getMovieList(){
   // TODO 6: Implement this method.
      return movieList;
   }
   
   /* Remove all movies from the list, resulting in an empty list.
    */
   public void clearMovieList(){
   // TODO 7: Implement this method.
      movieList.clear();
    }   
}
