class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int tt = attacks[attacks.length - 1][0]; // 마지막 공격 시간이 최종 시간
        int heal = health;
        int band = bandage[0];  // 연속 성공 시 추가 체력
        int hp = bandage[1];  // 초당 회복량
        int pls = bandage[2];  // 연속 회복 보너스 기준

        int bandcnt = 0; // 연속 성공 횟수
        int attackIndex = 0; // 공격 배열의 인덱스

        for (int i = 0; i <= tt; i++) { // 초 단위로 진행
            if (attackIndex < attacks.length && i == attacks[attackIndex][0]) {
                // 공격이 발생하는 경우
                heal -= attacks[attackIndex][1]; // 체력 감소
                if (heal <= 0) return -1; // 체력이 0 이하이면 즉시 종료
                bandcnt = 0; // 연속 회복 실패 (공격 당했으므로)
                attackIndex++; // 다음 공격으로 이동
            } else {
                // 공격이 없는 경우 회복 진행
                bandcnt++;
                heal += hp; // 초당 회복
                if (heal > health) heal = health; // 최대 체력 초과 방지

                // 연속 회복 보너스 적용
                if (bandcnt == band) {
                    heal += pls; // 추가 회복
                    if (heal > health) heal = health; // 최대 체력 초과 방지
                    bandcnt = 0; // 연속 회복 초기화
                }
            }
        }

        return heal; // 남은 체력 반환
    }
}
