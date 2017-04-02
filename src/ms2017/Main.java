package ms2017;

import java.util.Scanner;

/**
 * Created by changsheng on 2017/3/31.
 */

public class Main {
    static int P,Q,N;
    static double[][] table;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        P = sc.nextInt();
        Q = sc.nextInt();
        N = sc.nextInt();
        table = new double[101][N + 1];
        for (int i = 0; i < 101; i++) {
            for (int j = 0;  j < N + 1; j++) {
                table[i][j] = -1;
            }
        }
        System.out.println(String.format("%.2f", foo(P, N)));
    }


    public static double foo(int y, int k) {
        if (table[y][k] != -1) {
            return table[y][k];
        }

        double ans;
        double success;
        double fail;

        if (k == 0) {
            ans = 0;
        } else {
            if (y == 0) {
                success = 0;
                fail = foo(y + Q > 100 ? 100 : y + Q, k);
            } else {
                if (y == 100) {
                    success = foo((int) Math.floor( P / (Math.pow(2,N - k + 1))), k - 1);
                    fail = 0;
                }else {
                    success= y / 100.0 * foo((int) Math.floor( P / (Math.pow(2,N - k + 1))), k - 1);
                    fail = (1 - y / 100.0 ) * foo(y + Q > 100 ? 100 : y + Q, k);
                }
            }
            ans = 1 + success + fail;

        }
        table[y][k] = ans;
        return ans;
    }
}
