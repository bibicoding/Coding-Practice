/**
 * @author A0310635N
 */

class Order {

  private Product product;
  private int qty;
  private double discount = 0.0;

  public Order(Product product, int qty) {
    this.product = product;
    this.qty = qty;
  }

  public Order(Product product, int qty, double discount) {
    this.product = product;
    this.qty = qty;
    this.discount = discount;
  }

  public double process() {
    try {
      this.product.sell(this.qty);
      double income = this.product.discount(this.discount) * this.qty;
      return income;
    } catch (OutOfStockException e) {
      return 0.0;
    }
  }

  @Override
  public String toString() {
    return String.format("Order %d units of %s with %.1f", qty, product, discount) + "% discount";
  }
}
