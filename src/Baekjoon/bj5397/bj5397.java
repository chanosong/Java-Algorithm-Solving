package Baekjoon.bj5397;
// https://www.acmicpc.net/problem/5397
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj5397 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();


        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split("");
            List<String> list = new ArrayList<>();
            int cur = 0;

            for (String s: str) {
                switch (s) {
                    case "<":
                        if (cur != 0) cur -= 1;
                        break;
                    case ">":
                        if (cur < list.size()) cur += 1;
                        break;
                    case "-":
                        if (cur != 0) list.remove(cur - 1);
                        cur--;
                        break;
                    default:
                        list.add(cur, s);
                        cur++;
                }
            }
            StringBuffer sb = new StringBuffer();
            for (String s : list) sb.append(s);
            System.out.println(sb);
        }
    }
}
