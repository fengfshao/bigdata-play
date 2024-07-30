import java.util.*;

/**
 * Author: shaoff
 * Date: 2020/12/17 21:07
 * Package: PACKAGE_NAME
 * Description:
 */
public class Solution1 {
    static int[] pick(int[] arr) {
        Map<Integer, Integer> num2count = new HashMap<>();
        List<Integer> tmpList = new ArrayList<>();
        for (int num : arr) {
            num2count.put(num, num2count.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> pair : num2count.entrySet()) {
            if (pair.getValue() % 2 == 1) {
                tmpList.add(pair.getKey());
            }
        }
        int[] res = new int[tmpList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = tmpList.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 3, 3, 3, 5};
        int[] res = pick(arr);
        System.out.println(Arrays.toString(res));
    }

    static int searchInRotated(int[] arr, int target) {
        return _search(arr, 0, arr.length - 1, target);
    }

    // [1,2,4,6,9]
    private static int _search(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int m = (l + r) / 2;
            if (target == nums[m]) {
                return m;
            } else if (nums[l] < nums[m]) {
                if (target >= nums[l] && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                assert nums[m + 1] < nums[r];
                if (m + 1 < nums.length && target >= nums[m + 1] && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }
}
