/**
 * @author A0310635N
 */

class RoadTrip {
  
  private Vehicle v;
  private double distance;

  public RoadTrip(Vehicle v, double distance) {
    this.v = v;
    this.distance = distance;
  }
  
  public void complete() {
    try {
      this.v.move(this.distance);
    } catch (CannotMoveException e) {
      System.out.println("This trip cannot be completed.");
    } 
  }
}
