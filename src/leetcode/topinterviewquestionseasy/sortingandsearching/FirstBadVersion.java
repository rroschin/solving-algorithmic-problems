package leetcode.topinterviewquestionseasy.sortingandsearching;

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class FirstBadVersion extends VersionControl {

    static int maxGoodVersion = -1;

    public static int firstBadVersionLR(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public static int firstBadVersion(int n) {
        if (isBadVersion(1)) {
            return 1;
        }

        long maxGoodVersion = -1;
        long maxBadVersion = n;
        long nextN = (n % 2 == 0) ? (n / 2) : (n / 2 + 1);
        while (true) {
            final boolean badVersion = isBadVersion((int) nextN);
            long mid;
            if (badVersion) {
                maxBadVersion = nextN;
                mid = nextN + maxGoodVersion;
            } else {
                maxGoodVersion = nextN;
                mid = nextN + maxBadVersion;
            }
            nextN = (mid % 2 == 0) ? (mid / 2) : (mid / 2 + 1);
            if (maxGoodVersion + 1 == nextN && badVersion) {
                return (int) nextN;
            }
        }
    }

    public static int firstBadVersionBruteforce(int n) {
        for (int i = 1; i <= n; i++) {
            if (isBadVersion(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int firstBadVersionRecursion(int n) {
        if (n == 1) {
            return isBadVersion(1) ? 1 : -1;
        }

        int nextN = (n % 2 == 0) ? (n / 2) : (n / 2 + 1);
        final boolean badVersion = isBadVersion(nextN);
        if (badVersion) {
            if (maxGoodVersion + 1 == nextN) {
                return nextN;
            }
            return firstBadVersionRecursion(nextN);
        } else {
            maxGoodVersion = nextN;
            return firstBadVersionRecursion(nextN + n);
        }
    }

    public static void main(String[] args) {
        //                        System.out.println(firstBadVersion(5));
        //        System.out.println(firstBadVersion(3));
        //        System.out.println(firstBadVersion(2126753390));
        //        System.out.println(firstBadVersion(2));
        //        System.out.println(firstBadVersion(4));
        System.out.println(firstBadVersion(1182691386));
    }

}

class VersionControl {

    static boolean isBadVersion(int version) {
        //                        return version >= 4;
        //        return version >= 1;
        //        return version >= 1702766719;
        //        return version >= 1;
        //        return version >= 4;
        return version >= 662351799;
    }

}
