class C<T> {
  static int b = 0;

  C() {
    this.b++;
  }

  public static void main(String[] args) {
    C<Integer> x = new C<>();
    C<String> y = new C<>();

    System.out.println(x.b);
    System.out.println(y.b);
  }
}
