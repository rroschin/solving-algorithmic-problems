package zyx.romros;

public class BasicLinkedListSolution {

  public static void main(String[] args) {
    BasicLinkedList bll = new BasicLinkedList();
    bll.add("St1");
    System.out.println("Added St1: " + bll.toString());
    bll.add("St2");
    System.out.println("Added St2: " + bll.toString());
    bll.add("St3");
    System.out.println("Added St3: " + bll.toString());

    bll.add(0, "St11");
    System.out.println("Added St11 at 0: " + bll.toString());
    bll.add(2, "St12");
    System.out.println("Added St12 at 2: " + bll.toString());

    bll.delete(1);
    System.out.println("Deleted at 1: " + bll.toString());
    bll.delete(bll.size() - 1);
    System.out.println("Deleted at size-1: " + bll.toString());
    bll.delete(Integer.MAX_VALUE);
  }

  static class BasicLinkedList {

    BllNode head;
    BllNode tail;

    int size = 0;

    public void add(String st) {
      if (size == 0) {
        insertFirst(st);
        return;
      }

      BllNode node = new BllNode();
      node.value = st;
      node.prev = tail;
      tail = node;
      tail.prev.next = tail;

      if (size == 1) {
        head.next = node;
      }

      size++;
    }

    private void insertFirst(String st) {
      BllNode node = new BllNode();
      node.value = st;
      head = node;
      tail = node;
      size++;
    }

    public void add(int i, String st) {
      if ((i == 0 && size == 0) || i == size - 1) {
        add(st);
        return;
      }

      int cnt = 0;
      BllNode it = head;
      while (it != null) {
        if (cnt == i) {
          BllNode ins = new BllNode();
          ins.value = st;

          boolean isHead = it == head;

          ins.next = it;
          ins.prev = it.prev;
          it.prev = ins;

          if (isHead) {
            head = ins;
          } else {
            ins.prev.next = ins;
          }

          size++;
          return;
        }
        it = it.next;
        cnt++;
      }
      if (it == null) {
        throw new IllegalArgumentException("Out Of Bounds!");
      }
    }

    public void delete(int i) {
      if (i == 0) { //delete head
        head = head.next;
        size--;
        return;
      } else if (i == size - 1) {
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        return;
      }

      int cnt = 0;
      BllNode it = head;
      while (it != null) {
        if (cnt == i) {
          BllNode del = it;
          del.prev.next = del.next;
          del.next.prev = del.prev;

          size--;
          return;
        }
        it = it.next;
        cnt++;
      }
      if (it == null) {
        throw new IllegalArgumentException("Out Of Bounds!");
      }
    }

    public int size() {
      return size;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      BllNode it = head;
      while (it != null) {
        sb.append(it.value).append(", ");
        it = it.next;
      }
      return sb.toString();
    }

    static class BllNode {

      String value;
      BllNode next;
      BllNode prev;

    }

  }

}
