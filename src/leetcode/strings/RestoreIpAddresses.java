package leetcode.strings;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    int counter = 0;
    public static void main(String[] args) {
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("25525511135"));
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("255255111255"));
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("101023"));
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return List.of();
        }
        if (s.length() == 4) {
            return List.of(String.format("%s.%s.%s.%s", s.charAt(0), s.charAt(1), s.charAt(2), s.charAt(3)));
        }

        //max length == 255.255.255.255 -> 12

        List<String> res = new ArrayList<>();

        trackIps(s, 1, new ArrayList<>(), res);

        System.out.println(counter);
        return res;
    }

    void trackIps(String s, int level, List<String> currIp, List<String> res) {
        counter++;
        if (level == 4) {
            if (isValidIpPart(s)) {
                currIp.add(s);
                res.add(String.join(".", currIp));
                currIp.remove(currIp.size() - 1);
            }
            return;
        }
        for (int maxLength = 1; maxLength <= 3; maxLength++) {
            if (s.length() > maxLength - 1) {
                String ipPartCandidate = s.substring(0, maxLength);
                if (isValidIpPart(ipPartCandidate)) {
                    List<String> tmpCurrIp = new ArrayList<>(currIp);
                    tmpCurrIp.add(ipPartCandidate);
                    trackIps(s.substring(maxLength), level + 1, tmpCurrIp, res);
                }
            }
        }
    }

    boolean isValidIpPart(String ipPart) {
        if (ipPart.isEmpty() || ipPart.length() > 3) {
            return false;
        }
        if (ipPart.length() > 1 && ipPart.charAt(0) == '0') {
            return false;
        }
        if (Integer.parseInt(ipPart) > 255) {
            return false;
        }
        return true;
    }
}
