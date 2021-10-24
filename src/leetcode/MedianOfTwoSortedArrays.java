package leetcode;

class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];
        int j = 0, i = 0, k = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                arr[k] = nums1[i];
                k++;
                i++;
            } else {
                arr[k] = nums2[j];
                j++;
                k++;
            }
        }
        while (i < n1) {
            arr[k] = nums1[i];
            k++;
            i++;
        }
        while (j < n2) {
            arr[k] = nums2[j];
            k++;
            j++;
        }
        if (arr.length % 2 == 0) {
            int mid = (arr.length) / 2;
            double ans = (double) (arr[mid] + arr[mid - 1]) / 2;
            return ans;
        } else {
            int mid = (arr.length) / 2;
            double ans = (double) arr[mid];
            return ans;
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] out = new int[nums1.length + nums2.length];

        int i = 0;
        int j = 0;
        int h = 0;
        while (i < nums1.length || j < nums2.length) {
            Integer choice = null;
            if (i >= nums1.length) {
                choice = nums2[j];
            }
            if (j >= nums2.length) {
                choice = nums1[i];
            }
            if (choice != null) {
                out[h] = choice;
                i++;
                j++;
            } else {
                int left = nums1[i];
                int right = nums2[j];
                if (left <= right) {
                    out[h] = left;
                    i++;
                } else {
                    out[h] = right;
                    j++;
                }
            }
            h++;
        }

        if (out.length % 2 == 0) {
            double res = ((double) (out[out.length / 2] + out[out.length / 2 - 1])) / 2.0;
            return res;
        } else {
            return (double) out[out.length / 2];
        }
    }

    /*
        a = [1,3,5,9,100] -> M = 5(2)
        b = [0,6,8,10,20,30,40,1000,2000] -> M = 20(4)
        o = [0,1,3,5,6,8,9,10,20,30,40,100,1000,2000] //l=14 -> 14 / 2 = (7 + 7-1) -> (9+10)/2 = 9.5

        [9,10] OR 9.5


        a = [1,2,3,4,5] -> M = 3
        b = [5001,5002,5003,5004,5005,5006,5007,5008,5009,5010] -> M = (5005+5006)/2 = 5005.5
        0 = [1,2,3,4,5,5001,5002,5003,5004,5005,5006,5007,5008,5009,5010] -> M = 5003

    */

}
