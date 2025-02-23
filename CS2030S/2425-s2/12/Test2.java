class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "DigitalProduct is a subclass of Product",
        () -> DigitalProduct.class.getSuperclass().equals(Product.class),
        true);

    we.expect(
        "new DigitalProduct().toString() returns the correct string",
        () -> {
          return new DigitalProduct(1.0, "eBook").toString();
        },
        "eBook - $1.0");

    we.expectPrint(
        "Selling digital products works correctly",
        () -> {
          Product p = new DigitalProduct(1.0, "eBook");
          try {
            p.sell(100);
          } catch (OutOfStockException e) {
            // do nothing
          }
        },
        "Sold 100 units of eBook - $1.0\n");

    we.expectPrint(
        "Selling digital products works correctly",
        () -> {
          Product p = new DigitalProduct(1.0, "eBook");
          try {
            p.sell(20);
          } catch (OutOfStockException e) {
            // do nothing
          }
        },
        "Sold 20 units of eBook - $1.0\n");

    we.expect(
        "Product correctly computes the discounted price",
        () -> {
          Product p = new DigitalProduct(1.0, "eBook");
          return p.discount(20);
        },
        0.8);
  }
}
