/**
 * @author A0310635N
 */

abstract class Product implements Discountable {
  
  private double price;
  private String description;

  public Product(double price, String description) {
    this.price = price;
    this.description = description;
  }

  abstract void sell(int qty) throws OutOfStockException;
  
  @Override
  public double discount(double discountRate) {
    return price * (1 - discountRate / 100);
  }

  @Override
  public String toString() {
    return this.description + " - $" + this.price;
  }
}
