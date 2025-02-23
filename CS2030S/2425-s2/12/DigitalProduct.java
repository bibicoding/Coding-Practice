/**
 * @author A0310635N
 */

class DigitalProduct extends Product {
  
  public DigitalProduct(double price, String description) {
    super(price, description);
  }

  @Override
  public void sell(int qty) {
    System.out.println(String.format("Sold %d units of %s", qty, super.toString()));
  }

}
