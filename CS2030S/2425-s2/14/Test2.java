class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "Router is a subclass of NetworkDevice",
        () -> Router.class.getSuperclass().equals(NetworkDevice.class),
        true);

    we.expect(
        "new Router().toString() returns the correct string",
        () -> {
          return new Router().toString();
        },
        "Router on: false connected: false");

    we.expect(
        "Smartphone is a subclass of NetworkDevice",
        () -> Smartphone.class.getSuperclass().equals(NetworkDevice.class),
        true);

    we.expect(
        "new Smartphone().toString() returns the correct string",
        () -> {
          return new Smartphone().toString();
        },
        "Smartphone signal strength: 4 on: false connected: false");

    we.expect(
        "Smartphone can be turned on",
        () -> {
          Smartphone d = new Smartphone();
          d.on();
          return d.toString();
        },
        "Smartphone signal strength: 4 on: true connected: false");

    we.expect(
        "Smartphone correctly update signal strength when moved",
        () -> {
          Smartphone d = new Smartphone();
          d.on();
          d.updateStrength(2);
          return d.toString();
        },
        "Smartphone signal strength: 2 on: true connected: false");

    we.expect(
        "Smartphone correctly connects when signal strength > 0",
        () -> {
          Smartphone d = new Smartphone();
          d.on();
          d.updateStrength(2);
          try {
            d.connect();
          } catch (NetworkException e) {
            // do nothing
          }
          return d.toString();
        },
        "Smartphone signal strength: 2 on: true connected: true");

    we.expect(
        "Smartphone correctly disconnect when signal strength is 0",
        () -> {
          Smartphone d = new Smartphone();
          d.on();
          try {
            d.connect();
          } catch (NetworkException e) {
            // do nothing
          }
          d.updateStrength(0);
          return d.toString();
        },
        "Smartphone signal strength: 0 on: true connected: false");

    we.expectCheckedException(
        "Smartphone cannot connect while signal strength is 0",
        () -> {
          Smartphone d = new Smartphone();
          d.on();
          d.updateStrength(0);
          d.connect();
        },
        new NetworkException("No signal"));

    we.expectCheckedException(
        "Smartphone cannot connect while off",
        () -> {
          Smartphone d = new Smartphone();
          d.updateStrength(0);
          d.off();
          d.connect();
        },
        new NetworkException("Device is not on"));
  }
}
