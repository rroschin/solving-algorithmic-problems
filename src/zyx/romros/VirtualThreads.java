package zyx.romros;

import java.util.concurrent.atomic.AtomicLong;

public class VirtualThreads {
    public static void main(String[] args) {
        var c = new AtomicLong();
        for (var i = 0; i < 1_000_000; i++) {
            new Thread(c::incrementAndGet).start();
        }

        System.out.println(c.get());
    }
}
