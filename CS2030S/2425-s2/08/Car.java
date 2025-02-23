/**
 * @author A0310635N
 */

class Car extends Vehicle {
  
  public static final double FUEL_EFFICIENCY = 5.0;
  private double fuelLevel;


  public Car(double fuelLevel) {
    this.fuelLevel = fuelLevel;
  }

  public void refuel(double fuel) {
    this.fuelLevel += fuel;
  }

  @Override
  public void move(double distance) throws CannotMoveException {
    double deduct = distance / FUEL_EFFICIENCY;
    if (deduct > this.fuelLevel) {
      throw new CannotMoveException();
    } else {
      this.fuelLevel -= deduct;
    }
  }

  @Override
  public String toString() {
    return String.format("Car fuelLevel: %.2f", this.fuelLevel);
  }
}
