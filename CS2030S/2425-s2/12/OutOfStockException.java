/**
 * @author A0310635N
 */

class OutOfStockException extends Exception {
  
  public OutOfStockException() {
    super("Not enough stock");
  }
 
}
