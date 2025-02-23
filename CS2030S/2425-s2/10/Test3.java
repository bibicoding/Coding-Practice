import java.util.Arrays;

class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "InvalidVolumeException contains the correct message",
        () -> {
          return new InvalidVolumeException(17).getMessage();
        },
        "Cannot set volume to 17");

    we.expect("Adjustable is an interface", () -> Adjustable.class.isInterface(), true);

    we.expect(
        "AudioPlayer is a subclass of MediaPlayer",
        () -> AudioPlayer.class.getSuperclass().equals(MediaPlayer.class),
        true);

    we.expect(
        "AudioPlayer implements Adjustable",
        () -> Arrays.asList(AudioPlayer.class.getInterfaces()).contains(Adjustable.class),
        true);

    we.expectReturn(
        "new AudioPlayer().toString()",
        () -> new AudioPlayer().toString(),
        "Audio player volume: 2 playing: false");

    we.expectPrint(
        "AudioPlayer's play() correctly starts playback",
        () -> {
          MediaPlayer p = new AudioPlayer();
          p.play();
        },
        "audio playback begins.\n");

    we.expect(
        "AudioPlayer's play() correctly updates the state",
        () -> {
          MediaPlayer p = new AudioPlayer();
          p.play();
          return p.toString();
        },
        "Audio player volume: 2 playing: true");

    we.expectPrint(
        "AudioPlayer's stop() correctly stops playback",
        () -> {
          MediaPlayer p = new AudioPlayer();
          p.stop();
        },
        "audio playback ends.\n");

    we.expect(
        "AudioPlayer's stop() correctly updates the state",
        () -> {
          MediaPlayer p = new AudioPlayer();
          p.play();
          p.stop();
          return p.toString();
        },
        "Audio player volume: 2 playing: false");

    we.expect(
        "AudioPlayer's increase() correctly updates the volume",
        () -> {
          AudioPlayer p = new AudioPlayer();
          try {
            p.increase();
          } catch (InvalidVolumeException e) {
            // do nothing
          }
          return p.toString();
        },
        "Audio player volume: 3 playing: false");

    we.expect(
        "AudioPlayer's decrease() correctly updates the volume",
        () -> {
          AudioPlayer p = new AudioPlayer();
          try {
            p.decrease();
          } catch (InvalidVolumeException e) {
            // do nothing
          }
          return p.toString();
        },
        "Audio player volume: 1 playing: false");

    we.expectCheckedException(
        "Decreasing volume beyond 0 would cause an exception",
        () -> {
          AudioPlayer p = new AudioPlayer();
          p.decrease();
          p.decrease();
          p.decrease();
          p.decrease();
        },
        new InvalidVolumeException(-1));

    we.expectCheckedException(
        "Increasing volume beyond 5 would cause an exception",
        () -> {
          AudioPlayer p = new AudioPlayer();
          p.increase();
          p.increase();
          p.increase();
          p.increase();
        },
        new InvalidVolumeException(6));
  }
}
