package zyx.romros;

public class ReverseStringRecursively {

    public static void main(String[] args) {
        System.out.println(new ReverseStringRecursively().reverse("String"));
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        visit(0, str.length(), sb, str);
        return sb.toString();
    }

    private void visit(int idx, int n, StringBuilder sb, String str) {
        if (idx == n) {
            return;
        }
        visit(idx + 1, n, sb, str);
        sb.append(str.charAt(idx));
    }
}
