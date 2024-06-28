package zyx.romros.reflection;

import java.lang.reflect.Method;

public class Run {

    public static void main(String[] args) throws Exception {
        long start1 = System.nanoTime();
        Method sumInstanceMethod = Operations.class.getMethod("publicSum", int.class, double.class);

        Operations operationsInstance = new Operations();
        Double result = (Double) sumInstanceMethod.invoke(operationsInstance, 1, 3);
        long end1 = System.nanoTime();
        System.out.println(end1 - start1 + " ns");

        System.out.println(result);

        long start2 = System.nanoTime();
        Double result2 = new Operations().publicSum(1, 3);
        long end2 = System.nanoTime();
        System.out.println(end2 - start2 + " ns");
        System.out.println(result2);
    }
}
