package leetcode.intchar;

class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(1) + " == A"); //A
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(27) + " == AA"); //AA
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(28) + " == AB"); //AB
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(52) + " == AZ"); //AZ
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(701) + " == ZY"); //ZY
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(702) + " == ZZ"); //ZZ
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(703) + " == AAA"); //AAA
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(801) + " == ADU"); //ADU
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(2147483647) + " == FXSHRXW"); //FXSHRXW
    }

    public String convertToTitle(int columnNumber) {
        /*
         1 = A
         2 = B
         26 = Z
         27 = AA (26 + 1)
         52 = AZ (26 + 26)
         53 = BA (26 + 26 + 1)
         BZ = 78 (26 + 26 + 26)
         CA = 79 (26 + 26 + 26 + 1)
         ...
         */

        String res = "";
        while (columnNumber != 0) {
            columnNumber--;
            res = getUpTo26(columnNumber % 26) + res;
            columnNumber = columnNumber / 26;
        }
        return res;
    }

    char getUpTo26(int i) {
        char c = (char) (i + 'A');
        return c;
    }
}
