package Baekjoon.bj14575;

// https://www.acmicpc.net/problem/14575
// 14575 뒤풀이

import java.util.*;

public class bj14575 {

    static int n;
    static int t;
    static int biggestMin = 1;
    static int biggestMax = 1;
    static int[] min;
    static int[] max;
    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        answer = -1;

        n = sc.nextInt();
        t = sc.nextInt();

        // 최소값들의 합, 최대값들의 합
        int leftSum = 0;
        int rightSum = 0;

        min = new int[n];
        max = new int[n];

        // 사람 주량 정보 입력 받기
        for (int i = 0; i < n; i++) {
            int nowMin = sc.nextInt();
            int nowMax = sc.nextInt();

            min[i] = nowMin;
            max[i] = nowMax;

            leftSum += nowMin;
            rightSum += nowMax;

            biggestMin = Math.max(biggestMin, nowMin);
            biggestMax = Math.max(biggestMax, nowMax);
        }

        // t를 맞출 수 있는 경우 S를 맞춰가는 과정 진행
        if (t >= leftSum && t <= rightSum) {
            binarySearch(biggestMin, biggestMax);
        }

        System.out.println(answer);
    }

    static void binarySearch(int left, int right) {
        // 못찾은 경우 -1
        if (left > right) return;

        int mid = (left + right) / 2;
        int sum = 0;
        int remain = 0;

        for (int i = 0; i < n; i++) {
            remain += min[i];
            sum += Math.min(max[i], mid);
        }

        remain -= sum;

        // 만약 딱 맞춘 경우 종료
        if (sum == t) {
            answer = mid;
            return;
        }

        // 모자란 경우 더 커야한다
        if (sum < t) {
            binarySearch(mid + 1, right);
            return;
        }

        // 과한 경우 더 작아야한다 혹은 좀 덜어낸 경우에는 범위 안에 들어온 경우 가능할 수도 있으니 answer 갱신
        if (sum + remain <= t && sum >= t) answer = mid;
        binarySearch(left, mid - 1);
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
