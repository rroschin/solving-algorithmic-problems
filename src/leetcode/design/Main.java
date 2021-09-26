package leetcode.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MinStack {

    List<Integer> stack;
    int head = -1;

    int min = Integer.MAX_VALUE;

    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(int val) {
        head++;
        stack.add(head, val);
        if (stack.size() == 1) {
            min = stack.get(0);
        } else {
            min = Math.min(min, val);
        }
    }

    public void pop() {
        stack.remove(head);
        head--;
        if (stack.size() == 1) {
            min = stack.get(0);
        } else {
            stack.stream().min(Comparator.comparingInt(e -> e)).ifPresent(nm -> min = nm);
        }
    }

    public int top() {
        return stack.get(head);
    }

    public int getMin() {
        return min;
    }
}

public class Main {
    public static void main(String[] args) {
        MinStackWithRollingMin minStack = new MinStackWithRollingMin();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.top();    // return 0
        System.out.println(minStack.getMin());
    }
}

class MinStackWithLinkedList {

    Node currentNode;

    /**
     * initialize your data structure here.
     */
    public MinStackWithLinkedList() {

    }

    public void push(int val) {
        if (currentNode == null) {
            currentNode = new Node(val, val, null, null);
        } else {
            currentNode.nextNode = new Node(Math.min(currentNode.min, val), val, currentNode, null);
            currentNode = currentNode.nextNode;
        }
    }

    public void pop() {
        currentNode = currentNode.previousNode;
    }

    public int top() {
        return currentNode.data;
    }

    public int getMin() {
        return currentNode.min;
    }

    class Node {
        int min;
        int data;
        Node nextNode;
        Node previousNode;

        Node(int min, int data, Node previousNode, Node nextNode) {
            this.min = min;
            this.data = data;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }
    }
}

class MinStackWithRollingMin {

    static class StackElement {
        int val;
        int min;
    }

    List<StackElement> stack;
    int head = -1;

    public MinStackWithRollingMin() {
        stack = new ArrayList<>();
    }

    public void push(int val) {
        int prevMin = head < 0 ? val : stack.get(head).min;
        head++;
        StackElement next = new StackElement();
        next.val = val;
        next.min = Math.min(val, prevMin);

        stack.add(head, next);
    }

    public void pop() {
        stack.remove(head);
        head--;
    }

    public int top() {
        return stack.get(head).val;
    }

    public int getMin() {
        return stack.get(head).min;
    }
}
