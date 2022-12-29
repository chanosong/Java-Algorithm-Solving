package Programmers.SoloPlaying;
// https://school.programmers.co.kr/learn/courses/30/lessons/131130
public class SoloPlaying {
    public static void main(String[] args) {
        int[] cards = {8,6,3,7,2,5,1,4};
        int answer = 0;

        for (int i = 0; i < cards.length; i++) {
            int[] open = new int[cards.length];
            int score1 = 0;
            int score2 = 0;
            int idx = i;
            // check open
            open[idx] = 1;
            score1++;

            while (true) {
                if (open[cards[idx] - 1] == 1) {
                    break;
                }
                idx = cards[idx] - 1;
                score1++;
                open[idx] = 1;
            }

            for (int j = i; j < cards.length; j++) {
                idx = j;
                int tmp = 0;
                while (true) {
                    if (open[cards[idx] - 1] == 1) break;
                    idx = cards[idx] - 1;
                    tmp++;
                    open[idx] = 1;
                }

                score2 = Integer.max(score2, tmp);
            }
            answer = Integer.max(answer, score1 * score2);
        }

        System.out.println(answer);
    }
}
