class PS3 {
  public static <T extends Number & Comparable<T>> short[] toShortArray(T[] a, T threshold) {
    short[] out = new short[a.length];
    for (int i = 0; i < a.length; i += 1) {
      if (a[i].compareTo(threshold) <= 0) {
        out[i] = a[i].shortValue();
      }
    }
    return out;
  }
}
