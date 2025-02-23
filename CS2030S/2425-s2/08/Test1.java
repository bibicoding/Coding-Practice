import java.lang.reflect.Modifier;
import java.util.Arrays;

class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "CannotMoveException is an exception",
        Exception.class.isAssignableFrom(CannotMoveException.class),
        true);

    we.expect(
        "CannotMoveException is a checked exception",
        RuntimeException.class.isAssignableFrom(CannotMoveException.class),
        false);

    we.expect(
        "Vehicle is an abstract class",
        () ->
            Modifier.isAbstract(Vehicle.class.getModifiers())
                && !Modifier.isInterface(Vehicle.class.getModifiers()),
        true);

    we.expectReturn("Car.FUEL_EFFICIENCY", () -> Car.FUEL_EFFICIENCY, 5.0);

    we.expect(
        "Car.FUEL_EFFICIENCY is a public constant",
        () ->
            Arrays.stream(Car.class.getDeclaredFields())
                .filter(f -> f.getName().equals("FUEL_EFFICIENCY"))
                .findFirst()
                .filter(
                    f ->
                        Modifier.isStatic(f.getModifiers())
                            && Modifier.isPublic(f.getModifiers())
                            && Modifier.isFinal(f.getModifiers()))
                .isPresent(),
        true);

    we.expect("Car inherits from Vehicle", Car.class.getSuperclass().equals(Vehicle.class), true);

    we.expectReturn("new Car(10).toString()", () -> new Car(10).toString(), "Car fuelLevel: 10.00");

    we.expect(
        "After refueling 5, fuel level is 15",
        () -> {
          Car c = new Car(10);
          c.refuel(5.0);
          return c.toString();
        },
        "Car fuelLevel: 15.00");

    we.expect(
        "After moving by 10, fuel level is 13",
        () -> {
          Vehicle v = new Car(15);
          try {
            v.move(10);
          } catch (CannotMoveException e) {
            return "";
          }
          return v.toString();
        },
        "Car fuelLevel: 13.00");

    we.expectCheckedException(
        "Moving by 200",
        () -> {
          Vehicle v = new Car(10);
          v.move(200);
        },
        new CannotMoveException());
  }
}
