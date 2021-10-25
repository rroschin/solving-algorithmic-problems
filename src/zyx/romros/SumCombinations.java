package zyx.romros;

import java.util.ArrayList;

class SumCombinations {

    public static void main(String[] args) {
        System.out.println(new SumCombinations().print_all_sum(5));
    }

    static void print_all_sum_rec(
            int target,
            int current_sum,
            int start, ArrayList<ArrayList<Integer>> output,
            ArrayList<Integer> result) {

        if (target == current_sum) {
            output.add((ArrayList) result.clone());
        }

        for (int i = start; i < target; ++i) {
            int temp_sum = current_sum + i;
            if (temp_sum <= target) {

                result.add(i);
                print_all_sum_rec(target, temp_sum, i, output, result);
                result.remove(result.size() - 1);
            } else {
                return;
            }
        }
    }

    static ArrayList<ArrayList<Integer>> print_all_sum(int target) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        print_all_sum_rec(target, 0, 1, output, result);
        return output;
    }
}
