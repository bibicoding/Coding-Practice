import java.lang.reflect.Modifier;

class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "OutOfStockException is an exception",
        Exception.class.isAssignableFrom(OutOfStockException.class),
        true);

    we.expect(
        "OutOfStockException is a checked exception",
        RuntimeException.class.isAssignableFrom(OutOfStockException.class),
        false);

    we.expect("Discountable is an interface", () -> Discountable.class.isInterface(), true);

    we.expect(
        "Product is an abstract class",
        () ->
            Modifier.isAbstract(Product.class.getModifiers())
                && !Modifier.isInterface(Product.class.getModifiers()),
        true);

    we.expect(
        "PhysicalProduct is a subclass of Product",
        () -> PhysicalProduct.class.getSuperclass().equals(Product.class),
        true);

    we.expect(
        "new PhysicalProduct().toString() returns the correct string",
        () -> {
          return new PhysicalProduct(2.0, "Pen", 110).toString();
        },
        "Pen - $2.0");

    we.expectPrint(
        "Selling physical products works correctly",
        () -> {
          Product p = new PhysicalProduct(2.0, "Pen", 110);
          try {
            p.sell(100);
          } catch (OutOfStockException e) {
            // do nothing
          }
        },
        "Sold 100 units of Pen - $2.0\n");

    we.expectCheckedException(
        "Selling digital products works correctly",
        () -> {
          Product p = new PhysicalProduct(2.0, "Pen", 10);
          p.sell(20);
        },
        new OutOfStockException());

    we.expect(
        "Product correctly computes the discounted price",
        () -> {
          Product p = new PhysicalProduct(2.0, "Pen", 110);
          return p.discount(50);
        },
        1.0);
  }
}
