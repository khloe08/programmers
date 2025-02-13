class Solution {           
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int a = 0; a < schedules.length; a++) {
            // 출근 희망 시각 계산 (+10분 증가)
            Times hopeTime;
             hopeTime = seperateTime(schedules[a] ,10);
         
              boolean allTimeOn = true;
            // 일주일간 출근 기록을 확인
            for (int b = 0; b < timelogs[a].length; b++) {
                int currentDay = (startday+b)%7;
                
                if (currentDay == 6 || currentDay == 0) { // 주말 제외
                    continue;
                }

                Times realTime = seperateTime(timelogs[a][b],0);

                if (hopeTime.si < realTime.si || 
                    hopeTime.si == realTime.si && hopeTime.bun < realTime.bun) {
                    allTimeOn = false;
                    break;
                }
            }
            
             if(allTimeOn) {
                    answer++;
                }
        }

        return answer;
    }

    // 시각을 분리하는 메서드
    public static Times seperateTime(int schedule, int add) {
        schedule += add;
        int hour = schedule / 100; // 시각 부분
        int minute = schedule % 100; // 분 부분

        // 60분을 초과하면 시각 +1, 분 조정
        if (minute >= 60) {
            hour += 1;
            minute -= 60;
        }

        // 24시간 형식 유지
        if (hour >= 24) {
            hour = 0;
        }

        return new Times(hour, minute);
    }

    // Times 클래스 정의
    static class Times {
        int si;
        int bun;

        public Times(int si, int bun) {
            this.si = si;
            this.bun = bun;
        }
    }
    
    

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] schedules = {700, 800, 1100}; // 배열 선언 수정
        int[][] requests1 = {
            {710, 2359, 1050, 700, 650, 631, 659},
            {800, 801, 805, 800, 759, 810, 809},
            {1105, 1001, 1002, 600, 1059, 1001, 1100}
        };

        System.out.println("남은 개수: " + sol.solution(schedules, requests1, 5)); // Expected: 3
    }


    
}
