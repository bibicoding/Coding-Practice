/**
 * @author A0310635N
 */

class NetworkException extends Exception {
  
  private String msg;

  public NetworkException(String msg) {
    super(msg);
    this.msg = msg;
  }
  
  public String getMsg() {
    return this.msg;
  }

}
