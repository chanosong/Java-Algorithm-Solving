package Programmers.Hamburger;
// https://school.programmers.co.kr/learn/courses/30/lessons/133502

import java.util.*;
public class Hamburger {
    public static void main(String[] args) {
        int[] ingredient = {2,1,1,2,3,1,2,3,1};
        int answer = 0;
        Stack<Integer> st = new Stack<>();
        
        for (int i : ingredient) {
            // 이번에 넣을 것이 빵이면서 스택 크기가 3 이상인 경우
            if (i == 1 && st.size() >= 3) {
                // 순서 확인
                if (st.lastIndexOf(1) == st.size() - 3 && st.lastIndexOf(2) == st.size() - 2 && st.lastIndexOf(3) == st.size() - 1) {
                    answer++;
                    for (int j = 0; j < 3; j++) st.pop();
                    continue;
                }
            }
            
            st.add(i);
        }
        
        System.out.println(answer);
    }
    
}
