package Programmers.FoodFight;
//https://school.programmers.co.kr/learn/courses/30/lessons/134240
public class FoodFight {
    public static void main(String[] args) {
        int[] food = {1,3,4,6};
        String answer = "";
        String str = "";
        StringBuffer sb;

        for (int i = 1; i < food.length; i++) {
            for (int j = 0; j < food[i] / 2; j++) {
                str += i;
            }
        }

        sb = new StringBuffer(str);

        answer = str + "0" + sb.reverse();

        System.out.println(answer);
    }
}
