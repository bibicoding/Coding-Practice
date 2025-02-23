/**
 * @author A0310635N
 */

class MediaPlayer {
  
  private boolean playing;

  public MediaPlayer() {
    this.playing = false;
  }

  public void play() {
    this.playing = true;
  }

  public void stop() {
    this.playing = false;
  }

  @Override
  public String toString() {
    return "playing: " + playing;
  }

}
