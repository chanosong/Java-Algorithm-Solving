package Programmers.PreviousSong;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/17683
 */

import java.util.*;

public class PreviousSong {
    public static void main(String[] args) {
        String m = "A#BA";
        String[] musicinfos = {"03:00,03:10,FOO,A#BA#A#BA#"};
        String answer = "(None)";
        StringTokenizer st;

        HashMap<Integer, String> map = new HashMap<>();
        String[] mArr = m.split("");

        for (int i = 0; i < musicinfos.length; i++) {
            // musicinfos tokenize
            st = new StringTokenizer(musicinfos[i], ",");
            while (st.hasMoreTokens()) {
                String start = st.nextToken();
                String end = st.nextToken();
                String name = st.nextToken();
                String[] code = st.nextToken().split("");
                List<String> realcode = new LinkedList<>();

                // calculate time
                int[] stime = Arrays.stream(start.split(":")).mapToInt(Integer::parseInt).toArray();
                int[] etime = Arrays.stream(end.split(":")).mapToInt(Integer::parseInt).toArray();
                int diff;

                int mindiff = etime[1] - stime[1];
                if (mindiff < 0) {
                    etime[0]--;
                    mindiff += 60;
                }
                int timediff = etime[0] - stime[0];
                if (timediff < 0) timediff += 24;

                diff = timediff * 60 + mindiff;
                int tmpdiff = diff;
                int cur = 0;
                for (int j = 0; j < tmpdiff; j++) {
                    cur = j % code.length;
                    if (code[cur].equals("#")) tmpdiff++;
                    realcode.add(code[cur]);
                }
                if (cur < code.length - 1) {
                    if (code[cur + 1].equals("#"))
                        realcode.add("#");
                }

                // get whole code
                String compare = String.join("", realcode);
                System.out.println(compare);

                int midx = 0;

                // comparing
                // M에 #이 있는 경우
                if (m.charAt(m.length() - 1) == '#') {
                    if (compare.contains(m)){
                        if (map.get(diff) == null) map.put(diff, name);
                    }
                }
                // M에 #이 없는 경우
                else {
                    int sx = 0;

                    while (true) {
                        int idx = compare.indexOf(m,sx);

                        // not found
                        if (idx == -1) break;

                        // found, no doubt
                        if (idx + m.length() >= compare.length()){
                            if (map.get(diff) == null) map.put(diff, name);
                            break;
                        }

                        // found, check
                        if (compare.charAt(idx + m.length()) != '#'){
                            if (map.get(diff) == null) map.put(diff, name);
                            break;
                        }

                        sx += m.length() - 1;
                    }
                }
            }
        }

        // get proper music
        if (map.size() != 0) {
            Integer max = Collections.max(map.keySet());
            answer = map.get(max);
        }
        System.out.println(map);
        System.out.println(answer);
    }
}
