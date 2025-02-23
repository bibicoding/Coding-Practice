/**
 * @author A0310635N
 */

class AudioPlayer extends MediaPlayer implements Adjustable {

  private int volume;

  public AudioPlayer() {
    this.volume = 2;
  }

  @Override 
  public void play() {
    super.play();
    System.out.println("audio playback begins.");
  }

  @Override
  public void stop() {
    super.stop();
    System.out.println("audio playback ends.");
  }

  @Override
  public void increase() throws InvalidVolumeException {
    if (volume == 5) {
      throw new InvalidVolumeException(6);
    } else {
      this.volume += 1;
    }
  }

  @Override
  public void decrease() throws InvalidVolumeException {
    if (volume == 0) {
      throw new InvalidVolumeException(-1);
    } else {
      this.volume -= 1;
    }
  }

  @Override
  public String toString() {
    return "Audio player volume: " + volume + " " + super.toString();
  }

}
