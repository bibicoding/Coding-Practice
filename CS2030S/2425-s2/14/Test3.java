class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "DeviceManager can connect all devices (incl. smartphone)",
        () -> {
          Smartphone s = new Smartphone();
          Router r = new Router();
          s.on();
          r.on();
          DeviceManager m = new DeviceManager(new NetworkDevice[] {s, r});
          m.connectAll();
          return s.toString();
        },
        "Smartphone signal strength: 4 on: true connected: true");

    we.expect(
        "DeviceManager can connect all devices (incl. router)",
        () -> {
          Smartphone s = new Smartphone();
          Router r = new Router();
          s.on();
          r.on();
          DeviceManager m = new DeviceManager(new NetworkDevice[] {s, r});
          m.connectAll();
          return r.toString();
        },
        "Router on: true connected: true");

    we.expectPrint(
        "DeviceManager cannot connect router if it is off",
        () -> {
          Smartphone s = new Smartphone();
          Router r = new Router();
          s.on();
          DeviceManager m = new DeviceManager(new NetworkDevice[] {s, r});
          m.connectAll();
        },
        "Device is not on\n");

    we.expectPrint(
        "DeviceManager cannot connect smartphone is it has no signal",
        () -> {
          Smartphone s = new Smartphone();
          Router r = new Router();
          r.on();
          s.on();
          s.updateStrength(0);
          DeviceManager m = new DeviceManager(new NetworkDevice[] {s, r});
          m.connectAll();
        },
        "No signal\n");
  }
}
