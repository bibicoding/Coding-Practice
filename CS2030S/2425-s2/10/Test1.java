class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    we.expectReturn(
        "new MediaPlayer().toString()", () -> new MediaPlayer().toString(), "playing: false");

    we.expect(
        "play starts playback correctly",
        () -> {
          MediaPlayer p = new MediaPlayer();
          p.play();
          return p.toString();
        },
        "playing: true");

    we.expect(
        "play stops playback correctly",
        () -> {
          MediaPlayer p = new MediaPlayer();
          p.play();
          p.stop();
          return p.toString();
        },
        "playing: false");
  }
}
