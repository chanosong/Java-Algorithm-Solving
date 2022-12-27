package Programmers.OpenChat;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42888
1. db에 유저 정보 입력
2. log로 행동 기록 (uid)
3. db에서 uid에 해당하는 닉네임을 찾아 리턴
 */
public class OpenChat {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan", "Enter uid5555 Gerrard", "Change uid1234 George"};
        String[] answer;
        StringTokenizer tokenizer;
        int num = record.length;
        HashMap<String, String> nameMap = new HashMap<>();
        List<List<String>> log = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            tokenizer = new StringTokenizer(record[i]);

            String cmd = tokenizer.nextToken();
            String uid = tokenizer.nextToken();

            if (cmd.equals("Leave")) {
                log.add(new ArrayList<>(Arrays.asList(uid, "Leave")));
            }
            else {
                String nickname = tokenizer.nextToken();

                if (cmd.equals("Enter")) {
                    // 신규 참가자일 경우
                    if (!nameMap.containsKey(uid)) {
                        nameMap.put(uid, nickname);
                    }
                    // 기존에 존재하던 참가지일 경우
                    else {
                        nameMap.replace(uid, nickname);
                    }
                    log.add(new ArrayList<>(Arrays.asList(uid, "Enter")));
                }
                else {
                    nameMap.replace(uid, nickname);
                }
            }
        }

        answer = new String[log.size()];

        for (int i = 0; i < answer.length; i++) {
            String nickname = nameMap.get(log.get(i).get(0));
            String cmdCursor = log.get(i).get(1);

            if (cmdCursor.equals("Enter")) {
                answer[i] = nickname + "님이 들어왔습니다.";
            }
            else if (cmdCursor.equals("Leave")) {
                answer[i] = nickname + "님이 나갔습니다.";
            }
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}