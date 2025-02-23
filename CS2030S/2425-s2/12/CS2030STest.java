import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.List;
import java.util.function.Supplier;
import javax.tools.DiagnosticCollector;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * A helper class to test CS2030S labs.
 */
class CS2030STest {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";

  /**
   * Test if two objects are equals.
   *
   * @param test A description of the test.
   * @param output The output from an expression.
   * @param expect The expected output from that expression.
   * @return this object.
   */
  public CS2030STest expect(String test, Object output, Object expect) {
    System.out.print(test);
    if ((expect == null && output == null) || output.equals(expect)) {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    } else {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  expected: " + expect);
      System.out.println("  got this: " + output);
    }
    return this;
  }

  /**
   *
   * @param <T> The type of object the given task will produce.
   * @param test A description of the test.
   * @param task The task to run.
   * @param expect The expected output from that expression.
   * @return this object.
   */
  public <T> CS2030STest expect(String test, Supplier<T> task, Object expect) {
    try {
      T output = task.get();
      System.out.print(test);
      if ((expect == null && output == null) || output.equals(expect)) {
        System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
      } else {
        System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
        System.out.println("  expected: " + expect);
        System.out.println("  got this: " + output);
      }
    } catch (Exception e) {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  with exception: " + e.getMessage());
      e.printStackTrace();
    }
    return this;
  }

  public CS2030STest expectPrint(String test, Runnable task, String expect) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      final PrintStream old = System.out;
      System.setOut(new PrintStream(baos));
      task.run();
      System.out.flush();
      System.setOut(old);
      String output = baos.toString();

      System.out.print(test);
      if ((expect == null && output == null) || output.equals(expect)) {
        System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
      } else {
        System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
        System.out.println("  expected: " + expect);
        System.out.println("  got this: " + output);
      }
    } catch (Exception e) {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  with exception: " + e.getMessage());
      e.printStackTrace();
    }
    return this;
  }

  /**
   * Test if a given producer returns a value.  Wrapper around expect(..)
   * to simplify caller.
   *
   * @param <T> The type of object the given task will produce.
   * @param test A description of the test.
   * @param task The task to run.
   * @param expect The expected output from that expression.
   * @return this object.
   */
  public <T> CS2030STest expectReturn(String test, Supplier<T> task, Object expect) {
    return this.expect(test + " returns " + expect, task, expect);
  }

  /**
   * Test if an expression throws an exception.
   *
   * @param test A description of the test.
   * @param task A lambda expression of the expression.
   * @param expectedE A exception instance of the same type as the expected one.
   * @return this object.
   */
  public CS2030STest expectException(String test, Runnable task, Exception expectedE) {
    boolean gotException = false;
    try {
      task.run();
      System.out.print(test + " throws " + expectedE.getClass().getSimpleName());
    } catch (Exception e) {
      if (e.getClass().equals(expectedE.getClass())) {
        gotException = true;
      }
    }
    if (gotException) {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    } else {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  did not catch expected exception " + expectedE.getClass());
    }
    return this;
  }

  @FunctionalInterface
  public interface TryRunnable<E extends Exception> {
    void run() throws E;
  }

  public <E extends Exception> CS2030STest expectCheckedException(
      String test, TryRunnable<E> task, E expectedE) {
    boolean gotException = false;
    try {
      task.run();
    } catch (Exception e) {
      if (e.getClass().equals(expectedE.getClass())) {
        gotException = true;
      }
    }
    System.out.print(test + " throws " + expectedE.getClass().getSimpleName());
    if (gotException) {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    } else {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  did not catch expected exception " + expectedE.getClass());
    }
    return this;
  }

  /**
   * Test if an expression compiles with/without error.
   *
   * @param test A description of the test.
   * @param statement The java statement to compile
   * @param success Whether the statement is expected to compile or not
   *     (true if yes; false otherwise)
   * @return this object.
   */
  public CS2030STest expectCompile(String test, String statement, boolean success) {
    System.out.print(test);

    class JavaSourceFromString extends SimpleJavaFileObject {
      private final String code;

      public JavaSourceFromString(String code) {
        super(URI.create("string:///TempClass.java"), Kind.SOURCE);
        this.code = "class TempClass {void foo(){" + code + ";}}";
      }

      @Override
      public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
      }
    }

    boolean noError =
        ToolProvider.getSystemJavaCompiler()
            .getTask(
                null,
                null,
                new DiagnosticCollector<>(),
                null,
                null,
                List.of(new JavaSourceFromString(statement)))
            .call();

    if (noError != success) {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      if (!success) {
        System.out.println("  expected compilation error but it compiles fine.");
      } else {
        System.out.println("  expected the statement to compile without errors but it does not.");
      }
    } else {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    }
    return this;
  }
}
