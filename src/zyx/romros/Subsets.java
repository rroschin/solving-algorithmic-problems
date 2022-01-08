package zyx.romros;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[] { 1, 5, 3 }));
    }

    List<List<Integer>> subsets(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i : a) {
            List<List<Integer>> subres = new ArrayList<>();
            for (List<Integer> set : res) {
                final ArrayList<Integer> l = new ArrayList<>(set);
                l.add(i);
                subres.add(l);
            }
            res.addAll(subres);
        }

        return  res;

    }

}
