/**
 * @author A0310635N
 */

class TextMessenger implements Messenger {

  @Override
  public void send(String message, String recipient) throws MessageDeliveryException {
    if (recipient == "") {
      throw new MessageDeliveryException("Empty recipient");
    } else {
      System.out.println(String.format("Sending message to %s: %s", recipient, message));
    }
  }

  @Override
  public String recv() {
    return "Dummy message";
  }

}
