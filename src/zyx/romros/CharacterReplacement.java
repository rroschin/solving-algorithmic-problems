package zyx.romros;

class CharacterReplacement {

    public static void main(String[] args) {
        System.out.println(new CharacterReplacement().findLength("aabccbb", 2));//    Output: 5

        System.out.println(new CharacterReplacement().findLength("abbcb", 1));//    Output: 4

        System.out.println(new CharacterReplacement().findLength("abccde", 1));  //    Output: 3
    }

    public int findLength(String str, int k) {
        if (str.length() == k) {
            return k;
        }

        int start = 0;
        int end = 0;
        int max = 1;

        while (start <= end && end < str.length()) {
            char c = str.charAt(start); //a
            int k1 = k;
            while (end + 1 < str.length() && (str.charAt(end + 1) == c || k1 > 0)) {
                end++;
                if (str.charAt(end) != c) {
                    k1--;
                }
            }
            max = Math.max(max, end - start + 1);
            start++;
            end = start;

        }

        return max;
    }
}
