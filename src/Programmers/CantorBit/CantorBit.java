package Programmers.CantorBit;

public class CantorBit {
    static long calculate(long length, long one, long k, long zzl, long zzr) {
        long cnt = 0;

        k /= 5;



        return cnt;
    }
    public static void main(String[] args) {
        int n = 2;
        long length = 1;
        long one = 1;
        long l = 4;
        long r = 17;
        long rcnt = 0, lcnt = 0;
        // zero zone
        long zzl = length / 5 * 2 + 1;
        long zzr = length / 5 * 3;
        int answer = 0;
        String code = "11011";

        for (int i = 0; i < n; i++) {
            length *= 5;
            one *= 4;
            zzl = length / 5 * 2 + 1;
            zzr = length / 5 * 3;
        }

        rcnt = calculate(length, one, r, zzl, zzr);
        lcnt = calculate(length, one, l-1, zzl, zzr);
        answer = (int)(rcnt - lcnt);

        System.out.println(answer);
    }
}
