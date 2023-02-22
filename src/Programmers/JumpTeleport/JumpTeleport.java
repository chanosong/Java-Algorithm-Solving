package Programmers.JumpTeleport;
//https://school.programmers.co.kr/learn/courses/30/lessons/12980
public class JumpTeleport {
    public static void main(String[] args) {
        int n = 5000;
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
                continue;
            }
            n--;
            ans++;
        }
        System.out.println(ans);
    }
}
