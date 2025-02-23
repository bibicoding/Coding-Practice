/**
 * @author A0310635N
 */

class MessagingApp implements Messenger {
  
  private boolean isEncrypt;

  public MessagingApp() {
    this.isEncrypt = false;
  }

  public void setSecure(Boolean s) {
    this.isEncrypt = s;
  }

  @Override
  public void send(String message, String recipient) {
    try {
      if (!isEncrypt) {
        new TextMessenger().send(message, recipient);
      } else {
        new SecureMessenger(new TextMessenger()).send(message, recipient);
      }
    } catch (MessageDeliveryException e) {
      System.out.println("Unable to deliver message");
    }
  }

  @Override
  public String recv() {
    if (!isEncrypt) {
      return new TextMessenger().recv();
    } else {
      return new SecureMessenger(new TextMessenger()).recv();
    }
  }
}
