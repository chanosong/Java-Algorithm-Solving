package Programmers.CantorBit;
// https://school.programmers.co.kr/learn/courses/30/lessons/148652
public class CantorBit {

    static long calculate(int depth, long k) {
        long cnt = 0;
        long share = k / (long) Math.pow(5, depth - 1);
        long nk = k % (long) Math.pow(5, depth - 1);
        
        if (depth == 1) {
            if (k <= 2)
            {
                return k;
            }
            else {
                return k - 1;
            }
        }
        
        // before zeros
        if (share <= 2) {
            cnt = (long) Math.pow(4, depth - 1) * share;
        }
        // after zeros
        else {
            cnt = (long) Math.pow(4, depth - 1) * (share - 1);
        }
        
        if (share == 2) {
            return cnt;
        }
    
        return calculate(depth - 1, nk) + cnt;
    }

    public static void main(String[] args) {
        int n = 2;
        long l = 4;
        long r = 17;
        long rcnt = 0, lcnt = 0;
        int answer = 0;

        rcnt = calculate(n, r);
        lcnt = calculate(n, l - 1);
        answer = (int)(rcnt - lcnt);

        System.out.println(answer);
    }
}
