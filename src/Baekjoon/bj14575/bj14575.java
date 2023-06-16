package Baekjoon.bj14575;

// https://www.acmicpc.net/problem/14575
// 14575 뒤풀이

import java.util.*;

public class bj14575 {

    static int n;
    static int t;
    static int totalMin = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer = -1;

        n = sc.nextInt();
        t = sc.nextInt();

        PriorityQueue<Person> personList = new PriorityQueue<>();

        // 최소치,최대치 카운터
        int atLeast = 0;
        int full = 0;

        // 사람 주량 정보 입력 받기
        for (int i = 0; i < n; i++) {
            int nowMin = sc.nextInt();
            int nowMax = sc.nextInt();

            atLeast += nowMin;
            full += nowMax;
            totalMin = Math.max(totalMin, nowMin);

            personList.add(new Person(nowMin, nowMax));
        }

        // 술 최소치를 모두 넘겨줄 수 있는 경우
        if (atLeast <= t && full >= t) {
            int remain = t - atLeast;
            
            // 리스트가 비거나 다 털때까지 돌린다
            while (!personList.isEmpty()) {

                int size = personList.size();

                for (int i = 0; i < size; i++) {
                    Person now = personList.poll();

                    // 최대치가 전체 최소치의 최대 이하인 경우 채우고 바로 버림
                    if (now.max <= totalMin) {
                        remain -= now.max - now.min;
                    }
                    // 최대치가 전체 최소치의 최대보다 큰 경우 S를 건들지 않는 선까지 땡기고 다시 삽입
                    else {
                        remain -= totalMin - now.drunk;
                        now.drunk = totalMin;
                        personList.add(now);
                    }

                    // 음수가 된 경우 강제로 맞춰줌
                    remain = Math.max(0, remain);

                    // 다 턴 경우 탈출
                    if (remain == 0) break;
                }

                // 다 턴 경우 탈출
                if (remain == 0) break;

                // 그렇지 못한 경우 커트를 한 단계 올린다
                totalMin++;
            }

            answer = totalMin;
        }

        if (personList.isEmpty()) answer = -1;
        System.out.println(answer);
    }
}

/*
    나의 논리

    1. 일단 각자 최소치를 다 충족시켜 놓고나서 생각해도 괜찮다
    2. 각 사람의 최소치와 최대값 계속 따지면서 최소치의 최대를 갱신
    3. 만약 최소치를 다 충족시켰는데 이미 T를 넘어간 상황이라면 불가능
    4. 최소치를 다 충족시켰는데 T와 동일해진 경우 최소치의 최대값이 정답
    5. 최소치를 다 충족시켰는데 T보다 작다면 남은 것들을 처리해야함
        - 최소치, 최대치 순으로 대소비교하여 우선순위 큐에 삽입해왔음
        - 우선순위 큐에서 가장 작은 것부터 차례로 빼내면서 확인
        - 최소치의 최대보다 현재 사람의 최대 주량이 같거나 작은 경우 다 마시게 하고 큐 제외
        - 최소치의 최대보다 현재 사람의 최대 주량이 큰 경우 최소치의 최대까지 마시고 큐에 다시 삽입
        - 한바퀴를 돌렸는데 아직 큐에 남은 경우 최소치의 최대를 1 증가시키고 다시 반복
        - 우선순위 큐가 비거나, 남는 것이 0이 되는 경우엔 탈출
    6. 결국 최소치의 최대값이 정답이 된다.
 */

class Person implements Comparable<Person> {
    int min;
    int max;
    int drunk;

    Person (int min, int max) {
        this.min = min;
        this.max = max;
        this.drunk = min;
    }

    @Override
    public int compareTo(Person o) {
        if (this.min < o.min) return -1;
        else if (this.min == o.min) {
            if (this.max < o.max) return -1;
            else if (this.max == o.max) return 0;
            return 1;
        }
        return 1;
    }
}
