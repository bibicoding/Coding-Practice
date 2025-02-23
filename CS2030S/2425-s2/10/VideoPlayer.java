/**
 * @author A0310635N
 */

class VideoPlayer extends MediaPlayer {
  


  @Override
  public void play() {
    super.play();
    System.out.println("video playback begins.");
  }

  @Override
  public void stop() {
    super.stop();
    System.out.println("video playback ends.");
  }

  @Override
  public String toString() {
    return "Video player: " + super.toString();
  }


}
