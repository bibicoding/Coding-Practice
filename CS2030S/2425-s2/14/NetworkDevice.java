/**
 * @author A0310635N
 */

class NetworkDevice implements Connectable {
  
  private boolean on;
  private boolean connected;

  public NetworkDevice() {
    this.on = false;
    this.connected = false;
  }

  public void on() {
    this.on = true;
  }

  public void off() {
    this.on = false;
    this.connected = false;
  }

  @Override
  public void connect() throws NetworkException {
    if (!on) {
      throw new NetworkException("Device is not on");
    } else {
      this.connected = true;
    }
  }

  @Override 
  public void disconnect() {
    this.connected = false;
  }
  
  @Override
  public String toString() {
    return String.format("on: %b connected: %b", on, connected);
  }

}
