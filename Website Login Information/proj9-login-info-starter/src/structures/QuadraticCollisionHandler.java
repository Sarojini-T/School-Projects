package structures;

public class QuadraticCollisionHandler <K> implements CollisionHandler <K>{

    // constants for the quadratic 
    private int c1;
    private int c2;

    public QuadraticCollisionHandler(){
        this.c1 = 1;
        this.c2 = 1;
    }

    public QuadraticCollisionHandler(int firstParam, int secondParam){
        this.c1 = firstParam;
        this.c2 = secondParam;
    }
/**
  * Method starts at index and searches quadratically until an open spot
  * is found in the array. This could include index itself.
  * This is the formula to generate a new index to :
      curIndex = startIndex, i=1
  *    while the space at curIndex is occupied
  *       curIndex = (curIndex + c1*i + c2*(i*i)) % tablesize;
  *       increment i
  *    return the curIndex
  *@param startIndex the index that generated a collision
  *@param activeArray the boolean array where true cells are occupied, false are open.
  *@param M the size of the table.
  */
  public int probe(int startIndex, boolean[] activeArray, int M) {
    int curIndex = startIndex;
    int i = 1;
      //TODO: Implement this method.
    while(activeArray[curIndex] == true && i < M){
        curIndex = (curIndex + c1*i + c2*(i*i)) % M;
        i++;
    }
    return curIndex;
 }

/**
* Start at index and search the array quadratically until the target
return currIndex;
* is found. Then return the array index of the target. 
* Return -1 if not found.
*/
 public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){
      //TODO: Implement this method.
    int currIndex = startIndex;
    int i = 1;

    while(i < M){ 
        if(activeArray[currIndex] == false && keyArray[currIndex] == null){
            return -1;
        }
        else if (keyArray[currIndex].equals(target) && activeArray[currIndex] == true){
            return currIndex;
        }
        currIndex = (currIndex + c1*i + c2*(i*i)) % M;
        i++;
    }
    return -1;
 }
}
