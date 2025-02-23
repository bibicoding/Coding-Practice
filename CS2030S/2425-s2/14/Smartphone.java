/**
 * @author A0310635N
 */

class Smartphone extends NetworkDevice {
  
  private int signalStrength;

  public Smartphone() {
    this.signalStrength = 4;
  }

  public void updateStrength(int strength) {
    this.signalStrength = strength;
    if (strength == 0) {
      super.disconnect();
    }
  }

  @Override
  public void connect() throws NetworkException {
    if (signalStrength == 0) {
      throw new NetworkException("No signal");
    } else {
      super.connect();
    }
  }

  @Override
  public String toString() {
    return String.format("Smartphone signal strength: %d %s", signalStrength, super.toString());
  }

}
