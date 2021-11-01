package amzn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxVmUsage {

    public static void main(String[] args) {
        System.out.println(new MaxVmUsage().calculateMaxVmUsage(List.of(
                "User A,00:00,00:05,3 instances",
                "User A,00:05,00:10,4 instances",
                "User B,00:05,00:15,5 instances",
                "User A,00:10,00:15,1 instances",
                "User A,00:15,00:35,4 instances",
                "User B,00:15,00:30,6 instances",
                "User C,00:20,00:40,2 instances"
//                "User C,00:40,00:50,20 instances"
        ))); //6+4+2 = 12
    }

    int calculateMaxVmUsage(List<String> logs) {
        List<Log> parsedLogs = parseLogs(logs);

        int max = 0;
        Map<Integer, Integer> usage = new HashMap<>();
        for (Log log : parsedLogs) {
            for (int i = log.start; i < log.end; i++) {
                usage.merge(i, log.val, Integer::sum);
                max = Math.max(max, usage.get(i));
            }
        }

        return max;
    }

    int calculateMaxVmUsage2(List<String> logs) {
        List<Log> parsedLogs = parseLogs(logs);

        Map<String, Map<Integer, Integer>> usageHistory = populateUsageHistory(parsedLogs);

        Map<Integer, Integer> globalUsage = new HashMap<>();
        for (Map<Integer, Integer> userUsageHistory : usageHistory.values()) {
            for (Map.Entry<Integer, Integer> usage : userUsageHistory.entrySet()) {
                globalUsage.merge(usage.getKey(), usage.getValue(), Integer::sum);
            }
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> usage : globalUsage.entrySet()) {
            sum += usage.getValue();
            max = Math.max(max, sum);
        }

        return max;
    }

    private List<Log> parseLogs(final List<String> logs) {
        //"User B,00:15,00:30,6 instances"
        List<Log> parsedLogs = new ArrayList<>();

        for (String s : logs) {
            String[] data = s.split(",");
            Log l = new Log();
            l.userId = data[0];
            l.start = Integer.parseInt(data[1].replace(":", ""));
            l.end = Integer.parseInt(data[2].replace(":", ""));
            l.val = Integer.parseInt(data[3].replace(" instances", ""));
            parsedLogs.add(l);
        }

        return parsedLogs;
    }

    private Map<String, Map<Integer, Integer>> populateUsageHistory(final List<Log> parsedLogs) {

        Map<String, Integer> lastToRemove = new HashMap<>();
        Map<String, Map<Integer, Integer>> map = new HashMap<>();
        for (Log log : parsedLogs) {

            if (!map.containsKey(log.userId)) {
                map.put(log.userId, new HashMap<>());
            }

            map.get(log.userId).merge(log.start, log.val, (_old, _new) -> _new - _old);
            map.get(log.userId).put(log.end, log.val);

            lastToRemove.put(log.userId, log.end);
        }

        for (String userId : lastToRemove.keySet()) {
            map.get(userId).remove(lastToRemove.get(userId));
        }

        return map;
    }

    static class Log {
        String userId;
        int start;
        int end;
        int val;
    }
}
