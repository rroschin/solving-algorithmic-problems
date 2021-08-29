package zyx.romros;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MergeMeetingsSolution {

  public static class Meeting {

    private int startTime;
    private int endTime;

    Meeting(final int startTime, final int endTime) {
      // number of 30 min blocks past 9:00 am
      this.startTime = startTime;
      this.endTime = endTime;
    }

    @Override
    public boolean equals(final Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof Meeting)) {
        return false;
      }
      final Meeting meeting = (Meeting) o;
      return this.startTime == meeting.startTime && this.endTime == meeting.endTime;
    }

    @Override
    public int hashCode() {
      int result = 17;
      result = result * 31 + this.startTime;
      result = result * 31 + this.endTime;
      return result;
    }

    @Override
    public String toString() {
      return String.format("(%d, %d)", this.startTime, this.endTime);
    }

  }

  public static List<Meeting> mergeRanges(final List<Meeting> meetings) {
    if (meetings.size() == 1) {
      return meetings;
    }

    meetings.sort(Comparator.comparingInt((Meeting o) -> o.startTime).thenComparingInt(o -> o.endTime));

    final List<Meeting> res = new ArrayList<>(meetings.size());

    int mergeStart = meetings.get(0).startTime;
    int mergeEnd = meetings.get(0).endTime;
    for (int i = 1; i < meetings.size(); i++) { //O(n)
      final Meeting currMeeting = meetings.get(i);

      if (currMeeting.startTime <= mergeEnd) { //merge
        mergeEnd = Math.max(mergeEnd, currMeeting.endTime);
      } else { //save
        res.add(new Meeting(mergeStart, mergeEnd));
        mergeStart = currMeeting.startTime;
        mergeEnd = currMeeting.endTime;
      }
    }
    res.add(new Meeting(mergeStart, mergeEnd));

    return res;

  }

  // tests

  @Test
  public void meetingsOverlapTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsTouchTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(5, 6), new Meeting(6, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(5, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingContainsOtherMeetingTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsStaySeparateTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void multipleMergedMeetingsTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 4), new Meeting(2, 5), new Meeting(5, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsNotSortedTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(5, 8), new Meeting(1, 4), new Meeting(6, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 4), new Meeting(5, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void oneLongMeetingContainsSmallerMeetingsTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 10), new Meeting(2, 5), new Meeting(6, 8), new Meeting(9, 10), new Meeting(10, 12));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 12));
    assertEquals(expected, actual);
  }

  @Test
  public void sampleInputTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8), new Meeting(10, 12), new Meeting(9, 10));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(0, 1), new Meeting(3, 8), new Meeting(9, 12));
    assertEquals(expected, actual);
  }

}
