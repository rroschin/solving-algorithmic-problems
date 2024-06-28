package leetcode.kth;

import java.util.ArrayList;
import java.util.List;

class KthFactorOfN {
    public static void main(String[] args) {
        System.out.println(new KthFactorOfN().kthFactor(24, 6)); //8 [1,2,3,4,6,8,12,24]
        System.out.println(new KthFactorOfN().kthFactor(100, 7)); //25 [1,2,4,5,10,20,25,50,100]
        System.out.println(new KthFactorOfN().kthFactor(12, 3)); //3
        System.out.println(new KthFactorOfN().kthFactor(4, 4)); //-1
        System.out.println(new KthFactorOfN().kthFactor(46, 4)); //4
    }

    public int kthFactor(int n, int k) {

        //[1,2,3,4]
        //[24,12,8,6]
        // 8, 7, 6,5,
        //
        int factor = 1;
        int counter = 1;
        List<Integer> revFactors = new ArrayList<>();
        int same = 0;
        while (factor <= n) {
            if (n % factor == 0) {
                if (counter == k) {
                    return factor;
                }
                if (factor >= n / factor) {
                    if (factor == n / factor) {
                        same = 1;
                    }
                    break;
                }
                revFactors.add((n / factor));
                counter++;
            }

            factor++;
        }
        try {
            int i = k - revFactors.size() - same;
            return revFactors.get((revFactors.size() - i));
        } catch (Exception e) {
            return -1;
        }
    }
}
