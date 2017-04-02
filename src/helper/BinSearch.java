package helper;

import java.util.Arrays;

/**
 * Created by changsheng on 2017/3/31.
 */
public class BinSearch {
    private int[] array;
    public BinSearch(int[] a) {
        this.array = a;
        Arrays.sort(array);
    }
    public int[] search(int value, int range) {
        if (array == null) {
            return null;
        }
        int left = 0;
        int right = array.length - 1;

        while (right > left) {
            int p = (left + right) / 2;
            if (array[p] == value) {
                left = p;
                right = p;
            } else {
                if (array[p] > value) {
                    right = p;
                } else {
                    left = p + 1;
                }
            }
        }

        while (left >= 0 && value - array[left] <= range) {left--;}
        while (right < array.length && array[right] - value <= range) {right++;}
        left++;
        right--;
        int[] ans = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
            ans[i - left] = array[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {2,1};
        BinSearch binSearch = new BinSearch(a);
        System.out.println(Arrays.toString(binSearch.search(3,2)));
    }
}
