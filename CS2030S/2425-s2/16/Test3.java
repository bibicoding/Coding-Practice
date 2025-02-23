import java.util.Arrays;

class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "MessagingApp implements Messenger",
        () -> Arrays.asList(MessagingApp.class.getInterfaces()).contains(Messenger.class),
        true);

    we.expectPrint(
        "MessagingApp correctly sends a unsecure message",
        () -> {
          MessagingApp m = new MessagingApp();
          m.send("Hi", "Jack");
        },
        "Sending message to Jack: Hi\n");

    we.expectPrint(
        "MessagingApp correctly prints an error if recipient is empty",
        () -> {
          MessagingApp m = new MessagingApp();
          m.send("Hi", "");
        },
        "Unable to deliver message\n");

    we.expect(
        "MessagingApp correctly receives an unsecure message",
        () -> {
          MessagingApp m = new MessagingApp();
          return m.recv();
        },
        "Dummy message");

    we.expectPrint(
        "MessagingApp correctly sends a secure message",
        () -> {
          MessagingApp m = new MessagingApp();
          m.setSecure(true);
          m.send("Hi", "Jack");
        },
        "Sending message to Jack: Encrypted: Hi\n");

    we.expectPrint(
        "MessagingApp correctly prints an error if recipient is empty",
        () -> {
          MessagingApp m = new MessagingApp();
          m.setSecure(true);
          m.send("Hi", "");
        },
        "Unable to deliver message\n");

    we.expect(
        "MessagingApp correctly receives a secure message",
        () -> {
          MessagingApp m = new MessagingApp();
          m.setSecure(true);
          return m.recv();
        },
        "Decrypted: Dummy message");
  }
}
