/**
 * @author A0310635N
 */

class Bike extends Vehicle implements Foldable {
    
  private double totalDistance;
  private boolean folded;

  public Bike() {
    this.totalDistance = 0.00;
    this.folded = false;
  }

  @Override
  public void fold() {
    this.folded = true;
  }

  @Override
  public void unfold() {
    this.folded = false;
  }

  @Override
  public void move(double distance) throws CannotMoveException {
    if (!this.folded) {
      this.totalDistance += distance;
    } else {
      throw new CannotMoveException();
    }
  }

  @Override
  public String toString() {
    return String.format("Bike distance: %.2f folded: %b", this.totalDistance, this.folded);
  }

}
