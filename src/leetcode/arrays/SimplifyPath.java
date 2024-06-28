package leetcode.arrays;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(new SimplifyPath().simplifyPath("/../"));
        System.out.println(new SimplifyPath().simplifyPath("/home/"));
        System.out.println(new SimplifyPath().simplifyPath("/home//foo/"));
        System.out.println(new SimplifyPath().simplifyPath("/a/./b/../../c/"));
        System.out.println(new SimplifyPath().simplifyPath("/a//b////c/d//././/.."));
        System.out.println(new SimplifyPath().simplifyPath("/hello../world"));
    }

    public String simplifyPath(String path) {
        /*
        /../home/...//subdir//file//// -> /home/.../subdir/file

        /home/subdir//..////seconddir/file -> /home/seconddir/file
         */

        Deque<String> stack = new LinkedList<>();
        path += "/";
        if (true) {
            String[] items = path.split("/");
            for (String item : items) {
                String itemX = item.replace("/", "");
                if (item.isEmpty() || ".".equals(itemX)) {
                    continue;
                } else if ("..".equals(itemX)) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(itemX);
                }
            }
        } else {
            StringBuilder currDir = new StringBuilder();
            boolean seenSlash = false;
            boolean seenSingleDot = false;
            boolean seenDoubleDot = false;
            boolean seenTripleDot = false;
            int seenTripleDotValue = 0;
            boolean seenDirNameChar = false;
            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (c == '/') {
                    if (!seenSlash) {
                        seenSlash = true;
                        if (seenDirNameChar || seenTripleDot) {
                            stack.push(currDir.toString());
                            seenTripleDot = seenDirNameChar = false;
                            seenTripleDotValue = 0;
                        } else if (seenDoubleDot) {
                            if (!stack.isEmpty()) {
                                stack.pop();
                            }
                        }
                        seenSingleDot = seenDoubleDot= false;
                    }
                } else if (c == '.') {
                    if (seenTripleDot) {
                        currDir.append(c);
                        seenTripleDotValue++;
                    } else if (seenDoubleDot) {
                        currDir = new StringBuilder("...");
                        seenTripleDot = true;
                        seenTripleDotValue = 3;
                        seenDoubleDot = false;
                    } else if (seenSingleDot) {
                        seenDoubleDot = true;
                        seenSingleDot = false;
                    } else {
                        seenSingleDot = true;
                    }
                    seenSlash = false;
                } else { //letter, digit, _
                    seenSlash = false;
                    if (!seenDirNameChar) {
                        seenDirNameChar = true;
                        if (seenTripleDot) {
                            currDir = new StringBuilder().append(".".repeat(seenTripleDotValue));
                        } else if (seenDoubleDot) {
                            currDir = new StringBuilder("..");
                        } else if (seenSingleDot) {
                            currDir = new StringBuilder(".");
                        } else {
                            currDir = new StringBuilder();
                        }
                        seenSingleDot = seenDoubleDot = seenTripleDot = false;
                        seenTripleDotValue = 0;
                    }
                    currDir.append(c);
                }
            }
        }
        String res = stack.isEmpty() ? "" : stack.pop();
        while (!stack.isEmpty()) {
            res = stack.pop() + "/" + res;
        }
        return "/" + res;
    }
}
