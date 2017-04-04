package ms2016.p1289;

import java.util.Scanner;

/**
 * Created by changsheng on 2017/3/30.
 */

class IPAddress {
    static String[] bin = new String[256];
    static {
        for (int i = 0; i < 256; i++) {
            String tmp = Integer.toBinaryString(i);
            bin[i] = "";
            for (int j = 0; j < 8 - tmp.length(); j++) {
                bin[i] = bin[i] + "0";
            }
            bin[i] = bin[i] + tmp;
        }
    }
    public String binNum = "";
    public IPAddress(String s) {
        for (String tmp : s.split("\\.")) {
            binNum = binNum + bin[Integer.valueOf(tmp)];
        }
    }

}


class Rule {
    private boolean isAllow;
    private IPAddress address;
    private int mask;

    public Rule(String s) {
        String[] tmp = s.split(" ");
        isAllow = tmp[0].equals("allow") ? true : false;
        if (tmp[1].contains("/")) {
            String[] tmp2 = tmp[1].split("/");
            address = new IPAddress(tmp2[0]);
            mask = Integer.valueOf(tmp2[1]);
        }else {
            address = new IPAddress(tmp[1]);
            mask = 32;
        }
    }

    public Tuple query(String s) {
        IPAddress addressToQuery = new IPAddress(s);
        boolean first = mask == 0 ? true : address.binNum.substring(0, mask - 1).equals(addressToQuery.binNum.substring(0, mask - 1));
        boolean second = isAllow;
        return new Tuple(first, second);
    }


}

class Tuple {
    public boolean first;
    public boolean second;
    public Tuple(boolean a, boolean b) {
        first = a;
        second = b;
    }
}

public class Main {
    public static void main(String[] args) {



        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        Rule[] rules = new Rule[n];
        for (int i = 0; i < n; i++) {
            rules[i] = new Rule(sc.nextLine());
        }

        while (m-- > 0) {
            boolean pd = false;
            String s = sc.nextLine();
            int i;
            for (i = 0; i < n; i++) {
                Tuple result = rules[i].query(s);
                if (result.first) {
                    System.out.println(result.second ? "YES" : "NO");
                    pd = true;
                    break;
                }
            }

            if (!pd && i == n) {
                System.out.println("YES");
            }
        }





    }
}
