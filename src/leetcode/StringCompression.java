package leetcode;

class StringCompression {
    public int compress(char[] chars) {

        char groupChar = chars[0];
        int groupLen = 1;

        int j = 0;
        for (int i = 1; i <= chars.length; i++) {
            if (i == chars.length) { //last iteration
                if (groupLen == 1) {
                    chars[j] = groupChar;
                    j++;
                } else if (groupLen < 10) {
                    chars[j] = groupChar;
                    j++;
                    chars[j] = Character.forDigit(groupLen, 10);
                    j++;
                    groupLen = 1;
                } else { //groupLen >= 10
                    chars[j] = groupChar;
                    j++;
                    String len = String.valueOf(groupLen);
                    for (char c : len.toCharArray()) {
                        chars[j] = c;
                        j++;
                    }
                    groupLen = 1;
                }
            } else {
                if (chars[i] == groupChar) {
                    groupLen++;
                } else {
                    if (groupLen == 1) {
                        chars[j] = groupChar;
                        j++;
                    } else if (groupLen < 10) {
                        chars[j] = groupChar;
                        j++;
                        chars[j] = Character.forDigit(groupLen, 10);
                        j++;
                        groupLen = 1;
                    } else { //groupLen >= 10
                        chars[j] = groupChar;
                        j++;
                        String len = String.valueOf(groupLen);
                        for (char c : len.toCharArray()) {
                            chars[j] = c;
                            j++;
                        }
                        groupLen = 1;
                    }
                }
                groupChar = chars[i];
            }
        }

        return j;
    }
}
