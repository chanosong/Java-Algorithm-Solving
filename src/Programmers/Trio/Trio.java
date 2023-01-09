package Programmers.Trio;
//https://school.programmers.co.kr/learn/courses/30/lessons/131705
public class Trio {
    public static void main(String[] args) {
        int[] number = {-2,3,0,2,-5};
        int answer = 0;

        for (int i = 0; i < number.length; i++) {
            for (int j = i + 1; j < number.length; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    if (number[i] + number[j] + number[k] == 0) answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
