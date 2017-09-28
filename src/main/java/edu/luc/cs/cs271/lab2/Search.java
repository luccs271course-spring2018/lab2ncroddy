package edu.luc.cs.cs271.lab2;

import java.util.List;
import java.util.Optional;

public class Search {

  /** Looks for the position of the named team in an array. */
  public static Optional<Integer> findTeamPosition(final Team[] arr, final String key) {
    // Gets the array size
    final int size = arr.length;
    // Runs through a for loop to check
    for (int i = 0; i < size; i++) {
      // Gets the current item at index and compare name to key
      if (arr[i].getName().equals(key)) {
        // Return the index of where the item with key is located
        return Optional.of(i);
      }
    }
    // If it does not exist in the array then return an index of -1
    return Optional.empty();
  }

  /** Looks for the position of the named team in a list. */
  public static Optional<Integer> findTeamPosition(final List<Team> list, final String key) {
    // DONE complete this method
    final int size = list.size();
    for (int i = 0; i < size; i++){
      if (list.get(i).getName().equals(key)) return Optional.of(i);
    }
    return Optional.empty();
  }
  
  /** 
   * Looks for the position of the poorest team that has at least 
   * the specified funding level.
   * @pre arr is sorted
   * @post arr[result].funding >= minFunding && for all 0 <= i < result : arr[i].funding < minFunding
   */
  public static Optional<Integer> findTeamMinFunding(final Team[] arr, final int minFunding) {
    
    final int size = arr.length;
    Optional<Integer> currentMinPos = Optional.empty();
    int currentMinFunding;
    
    if (arr.length >= 1){ //in case we have an array of length 0
      currentMinFunding = arr[arr.length - 1].getFunding();  //because we know the highest value is the last team
    }
    else return currentMinPos;
    
    for (int i = 0; i < size; i++){
      if(arr[i].getFunding() <= currentMinFunding && arr[i].getFunding() >= minFunding ){
        currentMinFunding = arr[i].getFunding();
        currentMinPos = Optional.of(i);
      }
    }
    // DONE DRY complete this method
    return currentMinPos;
  }
  
  /** 
   * Looks for the position of the poorest team that has at least 
   * the specified funding level. 
   * Uses binary search: Initially search the entire array, 
   * then repeatedly eliminate the wrong half of the array until 
   * zero or one items are left.
   * @pre arr is sorted
   * @post arr[result].funding >= minFunding && for all 0 <= i < result : arr[i].funding < minFunding
   */
  public static Optional<Integer> findTeamMinFundingFast(final Team[] arr, final int minFunding) {
    // DONE DRY complete this method
    // Gets the array size
    final int size = arr.length;
    Optional<Integer> currentMinPos = Optional.empty();
    if(arr.length < 1) return currentMinPos;
    // Initially search the entire array 
    int low = 0;
    int high = size - 1;
    // Keep going as long as there is more than one item to be checked
    while ( low <= high){
      final int mid = (low + high)/2 + (low +high)%2;
      
      if( low == high && arr[mid].getFunding() >= minFunding){
          currentMinPos = Optional.of(mid);
          return currentMinPos;
        }
      else if(low == high){
        return currentMinPos;
      }
        //if(mid == 0) return Optional.empty();
      // Eliminate the wrong half of the array
      if ( arr[mid].getFunding() < minFunding){
        low = mid + 1;
      }
      
      else if(arr[mid-1].getFunding() >= minFunding){
        high = mid - 1;
      }
      else if (arr[mid].getFunding() >= minFunding && arr[mid-1].getFunding() < minFunding){
        low = mid;
        high = mid;
      }
    // Return current item only if it meets the condition!
     
    }
    return currentMinPos;
  }
}
