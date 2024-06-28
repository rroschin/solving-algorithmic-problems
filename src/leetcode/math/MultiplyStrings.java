package leetcode.math;

public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(new MultiplyStrings().multiply("2", "3")); //6
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        StringBuilder numOne = new StringBuilder(num1);
        StringBuilder numTwo = new StringBuilder(num2);

        numOne.reverse();
        numTwo.reverse();

        StringBuilder resRev = new StringBuilder("0");
        for (int i = 0; i < numTwo.length(); i++) {
            StringBuilder tmpRes = multiplyOne(numOne, numTwo.charAt(i), i);
            resRev = addNumbers(resRev, tmpRes);
        }

        resRev.reverse();
        return resRev.toString();
    }

    StringBuilder multiplyOne(StringBuilder numOne, char single, int zeros) {
        StringBuilder ans = new StringBuilder();
        ans.append("0".repeat(Math.max(0, zeros)));

        int carry = 0;
        int d2 = Integer.valueOf(single + "");
        for (int i = 0; i < numOne.length(); i++) {
            int d1 = Integer.valueOf(numOne.charAt(i) + "");
            int product = d1 * d2 + carry;
            ans.append(product % 10);
            carry = product / 10;
        }

        if (carry > 0) {
            ans.append(carry);
        }
        return ans;
    }


    StringBuilder addNumbers(StringBuilder numOneRev, StringBuilder numTwoRev) {
        StringBuilder ans = new StringBuilder();
        boolean carry = false;

        for (int i = 0; i < numOneRev.length() || i < numTwoRev.length(); i++) {
            int d1 = i < numOneRev.length() ? Integer.valueOf(numOneRev.charAt(i) + "") : 0;
            int d2 = i < numTwoRev.length() ? Integer.valueOf(numTwoRev.charAt(i) + "") : 0;

            int sum = d1 + d2 + (carry ? 1 : 0);
            ans.append(sum % 10);
            carry = sum > 9;
        }

        if (carry) {
            ans.append("1");
        }
        return ans;
    }
}
