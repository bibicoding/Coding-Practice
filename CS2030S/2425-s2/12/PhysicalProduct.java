/**
 * @author A0310635N
 */

class PhysicalProduct extends Product implements Discountable {
  
  private int numInStock;

  public PhysicalProduct(double price, String description, int numInStock) {
    super(price, description);
    this.numInStock = numInStock;
  }
  
  @Override
  public void sell(int qty) throws OutOfStockException {
    if (qty > numInStock) {
      throw new OutOfStockException();
    } else {
      this.numInStock -= qty;
      System.out.println(String.format("Sold %d units of %s", qty, super.toString()));
    }
  }

}
