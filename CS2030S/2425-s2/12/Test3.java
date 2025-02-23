class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expectReturn(
        "new Order(product, 20).toString()",
        () -> {
          Product p = new PhysicalProduct(4.00, "Notepad", 40);
          return new Order(p, 20).toString();
        },
        "Order 20 units of Notepad - $4.0 with 0.0% discount");

    we.expectPrint(
        "Processing order for physical product",
        () -> {
          Product p = new PhysicalProduct(4.00, "Notepad", 40);
          new Order(p, 20, 10).process();
        },
        "Sold 20 units of Notepad - $4.0\n");

    we.expect(
        "Processing order for physical product returns the correct price",
        () -> {
          Product p = new PhysicalProduct(4.00, "Notepad", 40);
          return new Order(p, 20, 10).process();
        },
        72.0);

    we.expectPrint(
        "Processing order for physical products with insufficient stock",
        () -> {
          Product p = new PhysicalProduct(4.00, "Notepad", 0);
          new Order(p, 20, 10).process();
        },
        "");

    we.expect(
        "Processing order for physical products with insufficient stock returns 0.00",
        () -> {
          Product p = new PhysicalProduct(4.00, "Notepad", 0);
          return new Order(p, 20, 10).process();
        },
        0.00);

    we.expectReturn(
        "new Order(product, 200).toString()",
        () -> {
          Product p = new DigitalProduct(4.00, "Notepad.app");
          return new Order(p, 200).toString();
        },
        "Order 200 units of Notepad.app - $4.0 with 0.0% discount");

    we.expectPrint(
        "Processing order for digital product",
        () -> {
          Product p = new DigitalProduct(4.00, "Notepad.app");
          new Order(p, 200).process();
        },
        "Sold 200 units of Notepad.app - $4.0\n");

    we.expect(
        "Processing order for digital product returns the correct price",
        () -> {
          Product p = new DigitalProduct(4.00, "Notepad.app");
          return new Order(p, 200).process();
        },
        800.0);
  }
}
