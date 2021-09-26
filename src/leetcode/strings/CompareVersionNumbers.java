package leetcode.strings;

class CompareVersionNumbers {

    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        final String[] v1 = version1.split("\\.");
        final String[] v2 = version2.split("\\.");

        int l = Math.max(v1.length, v2.length);
        for (int i = 0; i < l; i++) {
            String v1Str = i < v1.length ? v1[i] : "0";
            String v2Str = i < v2.length ? v2[i] : "0";

            int v1Int = Integer.parseInt(v1Str);
            int v2Int = Integer.parseInt(v2Str);

            if (v1Int == v2Int) {
                continue;
            } else if (v1Int < v2Int) {
                return -1;
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("0.1", "1.1"));
    }
}
