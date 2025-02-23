/**
 * @author A0310635N
 */

class DeviceManager {
  
  //unsure array type
  private NetworkDevice[] devices;

  public DeviceManager(NetworkDevice[] devices) {
    this.devices = devices;
  }

  public void connectAll() {
    try {
      for (NetworkDevice nd : devices) {
        nd.connect();
      }
    } catch (NetworkException e) {
      System.out.println(e.getMsg());
    }
  }

}
