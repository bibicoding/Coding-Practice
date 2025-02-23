import java.util.Arrays;

class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "SecureMessenger implements Messenger",
        () -> Arrays.asList(SecureMessenger.class.getInterfaces()).contains(Messenger.class),
        true);

    we.expectPrint(
        "SecureMessenger correctly sends a message",
        () -> {
          Messenger m = new SecureMessenger(new TextMessenger());
          try {
            m.send("Hi", "Jack");
          } catch (MessageDeliveryException e) {
            // do nothing
          }
        },
        "Sending message to Jack: Encrypted: Hi\n");

    we.expectCheckedException(
        "SecureMessenger throws an exception if recipient is empty",
        () -> {
          Messenger m = new SecureMessenger(new TextMessenger());
          m.send("Hi", "");
        },
        new MessageDeliveryException("Empty recipient"));

    we.expect(
        "SecureMessenger correctly receives a message",
        () -> {
          Messenger m = new SecureMessenger(new TextMessenger());
          return m.recv();
        },
        "Decrypted: Dummy message");
  }
}
