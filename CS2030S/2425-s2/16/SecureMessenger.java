/**
 * @author A0310635N
 */

class SecureMessenger implements Messenger {

  private Messenger msg;

  public SecureMessenger(Messenger msg) {
    this.msg = msg;
  }

  @Override
  public void send(String message, String recipient) throws MessageDeliveryException {
    this.msg.send("Encrypted: " + message, recipient);
  }

  @Override
  public String recv() {
    return "Decrypted: " + this.msg.recv();
  }

}
