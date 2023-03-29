package Programmers.SmallestRectangle;
// https://school.programmers.co.kr/learn/courses/30/lessons/86491
public class SmallestRectangle {
    public static void main(String[] args) {
        int[][] sizes = {{60,50},{30,70},{60,30},{80,40}};
        int answer = 0;
        int maxBig = 0;
        int maxMin = 0;

        for (int i = 0; i < sizes.length; i++) {
            maxBig = Math.max(maxBig, Math.max(sizes[i][0], sizes[i][1]));
            maxMin = Math.max(maxMin, Math.min(sizes[i][0], sizes[i][1]));
        }

        answer = maxBig * maxMin;

        System.out.println(answer);
    }
}
