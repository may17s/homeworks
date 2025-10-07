import java.util.ArrayList;
import java.util.List;

public class Sequence {
    public static int[] filter(int[] array, ByCondition condition) {
        if (array == null || condition == null) {
            return new int[] {};
        }

        List<Integer> result = new ArrayList<>();
        for (int num : array) {
            if (condition.isOk(num)) {
                result.add(num);
            }
        }

       return result.stream().mapToInt(Integer::intValue).toArray();
    }
}