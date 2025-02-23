import java.util.Arrays;

class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "MessageDeliveryException is an exception",
        Exception.class.isAssignableFrom(MessageDeliveryException.class),
        true);

    we.expect(
        "MessageDeliveryException is a checked exception",
        RuntimeException.class.isAssignableFrom(MessageDeliveryException.class),
        false);

    we.expect(
        "MessageDeliveryException contains a message",
        () -> new MessageDeliveryException("Testing 1 2 3").getMessage(),
        "Testing 1 2 3");

    we.expect("Messenger is an interface", () -> Messenger.class.isInterface(), true);

    we.expect(
        "TextMessenger implements Messenger",
        () -> Arrays.asList(TextMessenger.class.getInterfaces()).contains(Messenger.class),
        true);

    we.expectPrint(
        "TextMessenger correctly sends a message to recipient",
        () -> {
          Messenger m = new TextMessenger();
          try {
            m.send("Hi", "Jack");
          } catch (MessageDeliveryException e) {
            // do nothing
          }
        },
        "Sending message to Jack: Hi\n");

    we.expectCheckedException(
        "TextMessenger throws an exception if recipient is empty",
        () -> {
          Messenger m = new TextMessenger();
          m.send("Hi", "");
        },
        new MessageDeliveryException("Empty recipient"));

    we.expect(
        "TextMessenger correctly receives a message",
        () -> {
          Messenger m = new TextMessenger();
          return m.recv();
        },
        "Dummy message");
  }
}
