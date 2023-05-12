package Programmers.ConvertWord;

// https://school.programmers.co.kr/learn/courses/30/lessons/43163
// 단어 변환

import java.util.LinkedList;
import java.util.Queue;

public class ConvertWord {
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog",new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog",new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    static int solution(String begin, String target, String[] words) {
        int answer;

        boolean canMakeTarget = false;

        for (String word : words) {
            if (word.equals(target)) {
                canMakeTarget = true;
                break;
            }
        }

        // 만들 수 없는 경우
        if (!canMakeTarget) return 0;

        Word beginWord = new Word(begin);
        Word targetWord = new Word(target);
        Word[] wordArr = new Word[words.length];

        for (int i = 0; i < words.length; i++) {
            wordArr[i] = new Word(words[i]);
        }

        answer = bfs(beginWord, targetWord, wordArr);

        return answer;
    }

    static int bfs(Word beginWord, Word targetWord, Word[] wordArr) {
        int ans = 0;

        // 방문 처리
        boolean[] visited = new boolean[wordArr.length];
        Queue<Procedure> queue = new LinkedList<>();
        queue.add(new Procedure(0, beginWord));
        
        // 반복
        while (!queue.isEmpty()) {
            Procedure now = queue.poll();

            // 타겟 워드와 같아진 경우 종료
            if (now.word.getSameAlphabetCount(targetWord) == targetWord.getWordLength()) {
                ans = now.depth;
                break;
            }

            // 변환 가능한 단어인 경우
            for (int i = 0; i < wordArr.length; i++) {
                // 이미 사용한 경우, 한 자리만 바꿔서 안되는 경우 스킵
                if (visited[i] || now.word.getSameAlphabetCount(wordArr[i]) != now.word.getWordLength() - 1) continue;

                // 한 자리만 바꿔서 가능한 경우 진행
                queue.add(new Procedure(now.depth + 1, wordArr[i]));
                visited[i] = true;
            }
        }

        return ans;
    }
}

class Procedure {
    int depth;
    Word word;

    Procedure(int depth, Word word) {
        this.depth = depth;
        this.word = word;
    }
}

class Word {
    String[] alphabet;

    Word(String str) {
        alphabet = str.split("");
    }

    public int getSameAlphabetCount(Word word) {
        int cnt = 0;

        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i].equals(word.alphabet[i])) cnt++;
        }

        return cnt;
    }

    public int getWordLength() {
        return alphabet.length;
    }
}
