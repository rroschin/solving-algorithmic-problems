package codesignal;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Practice {

    public static void main(String[] args) {

    }

    String[] solution(String[][] queries) {
        String[] out = new String[queries.length];

        Map<String, TaskFile> cloudStorage = new HashMap<>();

        for (int i = 0; i < queries.length; i++){
            String[] query = queries[i];
            String action = query[0];

            if ("ADD_FILE".equals(action)) {
                String name = query[1];
                if (cloudStorage.containsKey(name)) {
                    out[i] = "false";
                } else {
                    String size = query[2];
                    TaskFile file = new TaskFile();
                    file.name = name;
                    file.size = Integer.valueOf(size);
                    cloudStorage.put(name, file);
                    out[i] = "true";
                }
            } else if ("COPY_FILE".equals(action)) {
                String nameFrom = query[2];
                String nameTo = query[2];
                if (!cloudStorage.containsKey(nameFrom)) { //nameFrom points to a file that does not exist
                    out[i] = "false";
                    //} else if (!nameFrom.contains(".")) { //nameFrom points to a directory
                    //    out[i] = "false";
                } else if (cloudStorage.containsKey(nameTo)) { //specified file already exists at nameTo
                    out[i] = "false";
                } else {
                    TaskFile file = cloudStorage.get(nameFrom);
                    TaskFile copy = new TaskFile();
                    copy.name = nameTo;
                    copy.size = file.size;
                    copy.copyOf = nameFrom;
                    cloudStorage.put(nameTo, copy);
                    out[i] = "true";
                }
            } else if ("GET_FILE_SIZE".equals(action)) {
                String name = query[1];
                TaskFile file = cloudStorage.get(name);
                if (file != null) {
                    out[i] = file.size + "";
                } else {
                    out[i] = "";
                }
            } else if ("FIND_FILE".equals(action)) {
                String prefix = query[1];
                String suffix = query[2];

                PriorityQueue<TaskFile> searchResults = new PriorityQueue<>(
                        Comparator.comparingInt(TaskFile::getSize)
                                .reversed()
                                .thenComparing(TaskFile::getName)
                );
                for (String fullName : cloudStorage.keySet()) {
                    if (fullName.startsWith(prefix) && fullName.endsWith(suffix)) {
                        TaskFile file = cloudStorage.get(fullName);
                        searchResults.add(file);
                    }
                }

                if (searchResults.isEmpty()) {
                    out[i] = "";
                } else {
                    TaskFile file = searchResults.poll();
                    StringBuilder sb = new StringBuilder("%s (%s)".formatted(file.name, file.size));
                    while (!searchResults.isEmpty()) {
                        sb.append(", ");
                        file = searchResults.poll();
                        sb.append("%s (%s)".formatted(file.name, file.size));
                    }
                    out[i] = sb.toString();
                }
            }
        }

        return out;
    }

    class TaskFile {
        String name;
        Integer size;
        String copyOf;

        public String getName() { return name; }
        public Integer getSize() { return size; }

    }

}
