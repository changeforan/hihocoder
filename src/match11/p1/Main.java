package match11.p1;

import java.util.*;

/**
 * Created by changsheng on 2017/3/31.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(hihoString(s));
    }

    private static int hihoString(String s) {

        Map<Character,Integer> max = new HashMap<>();
        max.put('h',2);
        max.put('i',1);
        max.put('o',1);

        Map<Character,Integer> count = new HashMap<>();
        count.put('h',0);
        count.put('i',0);
        count.put('o',0);

        LinkedList<Character> list = new LinkedList<>();

        int min = -1;
        for (int i = 0 ; i < s.length(); i++) {
            Character c = s.charAt(i);

            if (foo(c)) {
                int tmp = count.get(c);
                count.put(c, tmp + 1);
                list.add(c);
                for (Character ch : count.keySet()) {
                    if (count.get(ch) > max.get(ch)) {
                        Iterator<Character> iterator = list.iterator();
                        Character q = iterator.next();
                        while (!q.equals(ch)) {
                            iterator.remove();
                            if (foo(q)) {
                                count.put(q,count.get(q) - 1);
                            }
                            q = iterator.next();

                        }
                        iterator.remove();
                        count.put(ch, count.get(ch) - 1);

                        while (!foo(iterator.next())){iterator.remove();}
                    }
                }



            } else {
                if ( list.size() > 0) {
                    list.add(c);
                }
            }

            if (count.get('h').equals(2) && count.get('i').equals(1) && count.get('o').equals(1) ) {
                min = min == -1 ? list.size() : list.size() < min ? list.size() : min;
                System.out.println(list.toString());
            }

        }


        return min;
    }

    private static boolean foo(char c) {
        return c == 'h' || c == 'i' || c == 'o';
    }

}
