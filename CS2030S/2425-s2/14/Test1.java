import java.util.Arrays;

class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "NetworkException is an exception",
        Exception.class.isAssignableFrom(NetworkException.class),
        true);

    we.expect(
        "NetworkException is a checked exception",
        RuntimeException.class.isAssignableFrom(NetworkException.class),
        false);

    we.expect(
        "NetworkException contains a message",
        () -> new NetworkException("Testing 1 2 3").getMessage(),
        "Testing 1 2 3");

    we.expect("Connectable is an interface", () -> Connectable.class.isInterface(), true);

    we.expect(
        "NetworkDevice implements Connectable",
        () -> Arrays.asList(NetworkDevice.class.getInterfaces()).contains(Connectable.class),
        true);

    we.expect(
        "new NetworkDevice().toString() returns the correct string",
        () -> {
          return new NetworkDevice().toString();
        },
        "on: false connected: false");

    we.expect(
        "NetworkDevice can be turned on",
        () -> {
          NetworkDevice d = new NetworkDevice();
          d.on();
          return d.toString();
        },
        "on: true connected: false");

    we.expect(
        "NetworkDevice can be turned off",
        () -> {
          NetworkDevice d = new NetworkDevice();
          d.off();
          return d.toString();
        },
        "on: false connected: false");

    we.expect(
        "NetworkDevice can connect after turning on",
        () -> {
          NetworkDevice d = new NetworkDevice();
          d.on();
          try {
            d.connect();
          } catch (NetworkException e) {
            // do nothing
          }
          return d.toString();
        },
        "on: true connected: true");

    we.expect(
        "NetworkDevice disconnects after turning off",
        () -> {
          NetworkDevice d = new NetworkDevice();
          d.on();
          try {
            d.connect();
          } catch (NetworkException e) {
            // do nothing
          }
          d.off();
          return d.toString();
        },
        "on: false connected: false");

    we.expectCheckedException(
        "NetworkDevice cannot connect while off",
        () -> {
          NetworkDevice d = new NetworkDevice();
          d.connect();
        },
        new NetworkException("Device is not on"));
  }
}
