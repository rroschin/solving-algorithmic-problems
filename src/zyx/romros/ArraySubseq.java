package zyx.romros;

public class ArraySubseq {

    public static void main(String[] args) {
        System.out.println(new ArraySubseq().isSubseq(new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 4}));
        System.out.println(new ArraySubseq().isSubseq(new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 7}));
        System.out.println(new ArraySubseq().isSubseq(new int[]{1, 2, 3, 4, 5}, new int[]{0, 2, 3, 4}));
    }

    private boolean isSubseq(int[] arr, int[] seq) {
        if (arr.length < seq.length) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < arr.length && j < seq.length) {
            if (arr[i] == seq[j]) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == seq.length;
    }

}
