class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expect(
        "VideoPlayer is a subclass of MediaPlayer",
        () -> VideoPlayer.class.getSuperclass().equals(MediaPlayer.class),
        true);

    we.expectReturn(
        "new VideoPlayer().toString()",
        () -> new VideoPlayer().toString(),
        "Video player: playing: false");

    we.expectPrint(
        "play correctly starts playback",
        () -> {
          MediaPlayer p = new VideoPlayer();
          p.play();
        },
        "video playback begins.\n");

    we.expect(
        "play() correctly updates the state",
        () -> {
          MediaPlayer p = new VideoPlayer();
          p.play();
          return p.toString();
        },
        "Video player: playing: true");

    we.expectPrint(
        "stop() correctly stops playback",
        () -> {
          MediaPlayer p = new VideoPlayer();
          p.stop();
        },
        "video playback ends.\n");

    we.expect(
        "stop() correctly updates the state",
        () -> {
          MediaPlayer p = new VideoPlayer();
          p.play();
          p.stop();
          return p.toString();
        },
        "Video player: playing: false");
  }
}
