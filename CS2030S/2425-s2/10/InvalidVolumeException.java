/**
 * @author A0310635N
 */

class InvalidVolumeException extends Exception {
  
  public InvalidVolumeException(int volume) {
    super("Cannot set volume to " + volume);
  }

}
