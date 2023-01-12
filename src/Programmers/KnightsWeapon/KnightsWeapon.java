package Programmers.KnightsWeapon;
//https://school.programmers.co.kr/learn/courses/30/lessons/136798

public class KnightsWeapon {
    static int getDivisor(int n) {
        int cnt = 0;

        for (int i = 1; i * i <= n; i++) {
            if (i * i == n) cnt++;
            else if (n % i == 0) cnt += 2;
        }

        return cnt;
    }
    public static void main(String[] args) {
        int number = 10;
        int limit = 3;
        int power = 2;
        int answer = 0;
        
        for (int i = 1; i <= number; i++) {
            int damage = getDivisor(i);

            if (damage > limit) {
                answer += power;
                continue;
            }

            answer += damage;
        }

        System.out.println(answer);
    }
}
