package zyx.romros;

public class FibSolution {

  public static void main(String[] args) {
    System.out.println(fib(6));
    System.out.println(fibFor(6));
  }

  private static int fibFor(int n) {
    //0 1 1 2 3 5 8 13
    int nthFib = 0;
    int n1 = 0;
    int n2 = 1;
    while (n > 1) {
      nthFib = n1 + n2;
      n1 = n2;
      n2 = nthFib;
      n--;
    }
    return nthFib;
  }

  public static int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

}
