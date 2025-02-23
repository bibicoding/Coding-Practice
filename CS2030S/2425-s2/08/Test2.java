import java.util.Arrays;

class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    we.expect("Foldable is an interface", () -> Foldable.class.isInterface(), true);

    we.expect(
        "Bike implements Foldable",
        () -> Arrays.asList(Bike.class.getInterfaces()).contains(Foldable.class),
        true);

    we.expectReturn(
        "new Bike().toString()", () -> new Bike().toString(), "Bike distance: 0.00 folded: false");

    we.expect(
        "Bike is correctly folded",
        () -> {
          Foldable f = new Bike();
          f.fold();
          return f.toString();
        },
        "Bike distance: 0.00 folded: true");

    we.expect(
        "Bike is correctly unfolded",
        () -> {
          Foldable f = new Bike();
          f.fold();
          f.unfold();
          return f.toString();
        },
        "Bike distance: 0.00 folded: false");

    we.expect(
        "After moving, distance is correctly updated",
        () -> {
          Bike b = new Bike();
          try {
            b.move(10);
          } catch (CannotMoveException e) {
            // do nothing
          }
          return b.toString();
        },
        "Bike distance: 10.00 folded: false");

    we.expectCheckedException(
        "Cannot move a folded bike",
        () -> {
          Bike b = new Bike();
          b.fold();
          b.move(10);
        },
        new CannotMoveException());

    we.expect(
        "Cannot move again after unfolding",
        () -> {
          Bike b = new Bike();
          try {
            b.move(10);
            b.fold();
            b.move(10);
          } catch (CannotMoveException e) {
            // do nothing
          }
          b.unfold();
          try {
            b.move(10);
          } catch (CannotMoveException e) {
            // do nothing
          }
          return b.toString();
        },
        "Bike distance: 20.00 folded: false");
  }
}
