package fb;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Hops {

    public static void main(String[] args) {
        System.out.println(new Hops().getSecondsRequired(3, 1, new long[]{1}));
        System.out.println(new Hops().getSecondsRequired(6, 3, new long[]{5, 2, 4}));
        System.out.println(new Hops().getSecondsRequired(10, 4, new long[]{3, 4, 9, 7}));
        //..AB..D.E.
    }

    public long getSecondsRequired(long N, long F, long[] P) {
        if (F == 1) {
            return N - P[0];
        }

        PriorityQueue<Long> pHeap = new PriorityQueue<>();
        for (long p : P) {
            pHeap.add(p);
        }

        long seconds = 0;
        while (!pHeap.isEmpty()) {
            Long firstFrog = pHeap.poll();

            Long nextFrog = pHeap.peek();
            if (nextFrog == null) { //last frog
                seconds += N - firstFrog;
                break;
            }

            if (nextFrog - firstFrog == 1) { //hop over
                seconds += 1;

                List<Long> frogQueue = new LinkedList<>();
                Long prev = pHeap.poll();
                frogQueue.add(prev);

                Long curr;
                while (prev != null) {

                    curr = pHeap.poll();
                    if (curr != null) {
                        frogQueue.add(curr);
                        if (curr - prev > 1) {
                            break;
                        }
                    }
                    prev = curr;
                }

                pHeap.addAll(frogQueue);
                Long lastInQueue = frogQueue.get(frogQueue.size() - 1);
                firstFrog = lastInQueue + 1;
                if (firstFrog != N) {
                    pHeap.add(firstFrog);
                }
            } else { //hop closer
                long hops = nextFrog - firstFrog;
                seconds += hops - 1;

                firstFrog = nextFrog - 1;
                pHeap.add(firstFrog);
            }
        }

        return seconds;
    }

    long move(long frogName, long currAt, long[] pos, int idxInPos) {
        long moves = 0;
        for (int j = idxInPos + 1; j < pos.length; j++) {
            if (j == pos.length - 1) { //end
                pos[idxInPos] = 0;
                return moves + 1;
            }

            long hopsToNextFrog = pos[j] - pos[idxInPos];
            if (hopsToNextFrog > 1) {
                moves += hopsToNextFrog;
            } else {

            }


            if (pos[(int) j] == 0L) {

                if (j - 1 == currAt) { //hop next
                    //[0020000100]
                    //[0002000100]
                    pos[(int) j] = frogName;
                    pos[(int) currAt] = 0;
                    moves++;
                    currAt = j;
                } else { //hop over
                    //[0000002100]
                    //[0000000120]
                    pos[(int) j] = frogName;
                    pos[(int) currAt] = 0;
                    moves++;
                    return moves;
                }
            }
        }

        return moves;
    }

    public long getSecondsRequired1(long N, long F, long[] P) {
        if (F == 1) {
            return N - P[0];
        }

        long min = Integer.MAX_VALUE;
        long[] pos = new long[(int) (N + 1)];
        for (long p : P) {
            pos[(int) p] = p; //[0012004030]
            min = Math.min(min, p);
        }

        long seconds = 0;
        for (long i = min; i < N + 1; i++) {
            if (pos[(int) i] != 0) {
                long firstFrog = pos[(int) i];

                seconds += move1(firstFrog, i, pos);
            }
        }


        return seconds;
    }

    long move1(long firstFrog, long currAt, long[] pos) {
        long moves = 0;
        for (long j = currAt + 1; j < pos.length; j++) {
            if (j == pos.length - 1) { //end
                pos[(int) currAt] = 0;
                return moves + 1;
            }

            if (pos[(int) j] == 0L) {

                if (j - 1 == currAt) { //hop next
                    //[0020000100]
                    //[0002000100]
                    pos[(int) j] = firstFrog;
                    pos[(int) currAt] = 0;
                    moves++;
                    currAt = j;
                } else { //hop over
                    //[0000002100]
                    //[0000000120]
                    pos[(int) j] = firstFrog;
                    pos[(int) currAt] = 0;
                    moves++;
                    return moves;
                }
            }
        }

        return moves;
    }
}
