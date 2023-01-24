package structures;

public class LinearCollisionHandler <K> implements CollisionHandler <K>{
    private int probeLength;

    /**
  * Constructors to set probeLength to 1, or a different length.
  */
    public LinearCollisionHandler(){
        this.probeLength = 1;
    }

    public LinearCollisionHandler(int probeLength){
        this.probeLength = probeLength;
    }

/**
  * Method starts at index and searches linearly until an open spot
  * is found in the array. This could include index itself.
  * index = (index + probeLength) % size
  */
   public int probe(int index, boolean[] activeArray, int M) {
      //TODO: Implement this method.
      int currIndex = index;
      int i = 0;
      while(i < M && activeArray[currIndex] == true){
        currIndex = (currIndex + probeLength) % M;
        i++;
     }
      return currIndex;
   }

  /**
* Start at index and search the array linearly until the target
* is found. Then return the array index of the target. 
* Return -1 if not found.
*/
   public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){
      //TODO: Implement this method.
    int currIndex = startIndex;
    int i = 0;

    while(i < M){
      if(activeArray[currIndex] == true && keyArray[currIndex].equals(target)){
        return currIndex;
      } 
      if(activeArray[currIndex] == false && keyArray[currIndex] == null){
        break;
      }
      currIndex = (currIndex + probeLength) % M;
      i++;
    }
    return -1;
 }
}
