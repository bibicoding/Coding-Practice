/**
 * @author A0310635N
 */

interface Messenger {
  
  void send(String message, String recipient) throws MessageDeliveryException;

  String recv();

}
