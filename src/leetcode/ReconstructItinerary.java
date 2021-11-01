package leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class ReconstructItinerary {

    public static void main(String[] args) {
        System.out.println(new ReconstructItinerary().findItinerary(List.of(
                List.of("JFK", "KUL"),
                List.of("JFK", "NRT"),
                List.of("NRT", "JFK")
        )));
        System.out.println(new ReconstructItinerary().findItinerary(List.of(
                List.of("MUC", "LHR"),
                List.of("JFK", "MUC"),
                List.of("SFO", "SJC"),
                List.of("LHR", "SFO")
        )));

        System.out.println(new ReconstructItinerary().findItinerary(List.of(
                List.of("JFK", "SFO"),
                List.of("JFK", "ATL"),
                List.of("SFO", "ATL"),
                List.of("ATL", "JFK"),
                List.of("ATL", "SFO")
        )));

    }

    public List<String> findItinerary(List<List<String>> tickets) {

        /*
        from     to
        "MUC" -> "LHR"
        "JFK" -> "MUC"
        "SFO" -> "SJC"
        "LHR" -> "SFO"

        Ticket {
          ticket.start
          ticket.end
        }

        start = JFK
        step 1: find JFK

        until has more tickets (remove ticket once used)
        step 2:
        mapOfTickets.get(JFK) -> MUC
        add MUC to the list, remove ticket, current = MUC, continue


        "JFK": ["ATL", "SFO"] - sorted
        "SFO": ["ATL"]
        "ATL": ["JFK", "SFO"]
        */

        Map<String, PriorityQueue<String>> flights = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!flights.containsKey(from)) {
                flights.put(from, new PriorityQueue<>());
            }
            flights.get(from).add(to);
        }

        /*
        List<String> itinerary = new LinkedList<>();
        String current = "JFK";
        while (!flights.isEmpty()) {
            itinerary.add(current);
            PriorityQueue<String> destinations = flights.get(current);
            String nextDestination = destinations.poll();

            if (destinations.isEmpty()) {
                flights.remove(current);
            }

            current = nextDestination;
        }
         */

        List<String> itinerary = new LinkedList<>();
        Deque<String> stack = new LinkedList<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (flights.containsKey(stack.peek()) && !flights.get(stack.peek()).isEmpty()) {
                stack.push(flights.get(stack.peek()).poll());
            }
            itinerary.add(0, stack.pop());
        }

        return itinerary;
    }
}
