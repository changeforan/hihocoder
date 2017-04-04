package ms2016.p1288;


import java.util.Scanner;

/**
 * Created by changsheng on 2017/3/30.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tasks = sc.nextInt();
        sc.nextLine();
        int n, p, w, h;
        int[] a;
        while (tasks-- > 0) {
            String s = sc.nextLine();
            String[] nums = s.split(" ");
            n = Integer.valueOf(nums[0]);
            p = Integer.valueOf(nums[1]);
            w = Integer.valueOf(nums[2]);
            h = Integer.valueOf(nums[3]);

            a = new int[n];
            s = sc.nextLine();
            nums = s.split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.valueOf(nums[i]);
            }
            System.out.println(foo(n,p,w,h,a));
        }

    }

    private static int foo(int n, int p, int w, int h, int[] a) {
        int chsCount = 0;
        for (int tmp : a) {
            chsCount = chsCount + tmp;
        }

        int size = (int) Math.floor(Math.sqrt(w * h * p / (double) chsCount));
        while (size > 0) {
            int width = (int) Math.floor(w /  (double) size);
            int height = (int) Math.floor(h / (double) size);
            int pagesUsed = 0;
            int line = 0;
            if (width * height > 0) {
                for (int tmp : a) {
                    line += (int) Math.ceil(tmp /(double) width);
                }
                pagesUsed = (int) Math.ceil(line / (double) height);
                if (pagesUsed <= p) {
                    break;
                }

            }
            size--;
        }
        return size;

    }
}
