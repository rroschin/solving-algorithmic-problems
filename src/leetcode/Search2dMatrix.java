package leetcode;

class Search2dMatrix {

    public static void main(String[] args) {
        System.out.println(new Search2dMatrix().searchMatrix(new int[][] {
                                                                     { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 }
                                                             },
                                                             300));
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        //O(n*m) <> O(log(n*m))

        int l = matrix.length * matrix[0].length - 1;
        return search(matrix, 0, l, target);

    }

    boolean search(int[][] matrix, int start, int end, int target) {
        if (start > end) {
            return false;
        }
        int midi = (end - start) / 2 + start; //k
        int[] ij = kToIj(midi, matrix);
        int i = ij[0];
        int j = ij[1];
        int mid = matrix[i][j];

        if (mid == target) {
            return true;
        } else if (mid > target) {
            return search(matrix, start, midi - 1, target);
        } else {
            return search(matrix, midi + 1, end, target);
        }
    }

    int[] kToIj(int k, int[][] matrix) { //convert k to i,j
        int n = matrix[0].length;

        int i = k / n;
        int j = k % n;

        return new int[] { i, j };
    }

}















