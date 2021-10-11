package coursera; /******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;

public class JustifyString2dApproach {

    public static String justify(String text, int n) {
        if (text.length() == n) {
            return text;
        }
        if (n < 1) {
            return "";
        }

        StringBuilder res = new StringBuilder();

        Queue<String> q = new LinkedList<>();
        for (String s : text.split(" ")) {
            q.add(s);
        }

        StringBuilder line = new StringBuilder();
        char space = ' ';
        int spaceLength = 1;
        while (!q.isEmpty()) {
            String curr = q.peek();
            if (line.length() == 0) {
                line.append(curr);
                q.poll();
                continue;
            }
            int currLineLength = line.length() + spaceLength + curr.length();
            if (currLineLength == n) {
                res.append(line).append(space).append(curr).append("\n");
                line = new StringBuilder();
                q.poll();
            } else if (currLineLength < n) {
                line.append(space).append(curr);
                q.poll();
            } else if (currLineLength > n) {
                String goodLine = line.toString();
                int spacesToDistribute = n - goodLine.length(); //spaces to evenly distribute
                //Online Java Compiler. Code = 26 -> 30

                String[] glarr = goodLine.split(" ");
                int[] sparr = new int[glarr.length - 1]; //+1
                int i = 0;
                while (spacesToDistribute > 0) {
                    if (i == sparr.length) {
                        i = 0;
                    }
                    sparr[i] += 1;
                    i++;
                    spacesToDistribute--;
                }

                StringBuilder toRes = new StringBuilder();
                for (int j = 0; j < glarr.length; j++) {
                    toRes.append(glarr[j]);
                    if (j != glarr.length - 1) {
                        int numOfSp = sparr[j] + 1;
                        while (numOfSp > 0) {
                            toRes.append(" ");
                            numOfSp--;
                        }
                    }
                }
                res.append(toRes).append("\n");
                line = new StringBuilder();
            }
        }

        return res.append(line).append("\n").toString();
    }

	public static void main(String[] args) {
		String s = "Online Java Compiler. Code, Compile, Run and Debug java program online. Write your code in this editor and press 'Run' button to execute it.";
		System.out.println(justify(s, 70));
		System.out.println(justify(s, 50));
		System.out.println(justify(s, 30));
	}
}
