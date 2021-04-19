package com.payulatam.recruiting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NetworkPrioritization {

    public enum Criteria {
        RESPONSE_TIME, COST
    }

    private static List<Integer> arrayToList(int[] array){

        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    public int[] prioritizeNetwork(int[] responseTimes, int[] cost, Criteria priority) {
        int[] range = IntStream.range(0, responseTimes.length).toArray();
        List<Integer> index= arrayToList(range);
        List<Integer> list = arrayToList(responseTimes);
        List<Integer> list1 = arrayToList(cost);
        Comparator<Integer> comp;
        if (priority.equals(Criteria.RESPONSE_TIME)) {
            // Comparator.comparing(list::get) is equivalent to
            // **Comparator<Integer> comp = (idx1, idx2) ->
            //      list.get(idx1).compareTo(list.get(idx2));**
            comp = Comparator.comparing(list::get).thenComparing(list1::get);
        }
        else{
            comp = Comparator.comparing(list1::get).thenComparing(list::get);
        }
        index.sort(comp);

        return index.stream().mapToInt(i -> i).toArray();
    }
}
