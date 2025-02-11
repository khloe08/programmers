import java.util.*;

class Solution {
    static int width, height, total;
    static int[][] dr = { {-1,0}, {1,0}, {0,-1}, {0,1} }; // 상, 하, 좌, 우 이동
    static char[][] charMatrix;

    static class Point {
        int row, col;
        Point(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    public int solution(String[] storage, String[] requests) {
        height = storage.length;
        width = storage[0].length();
        total = height * width;
        charMatrix = convertToCharMatrix(storage);

        for (String request : requests) {
            char target = request.charAt(0);

            if (request.length() == 1) {  // 지게차 (외부 연결된 블록만 제거)
                boolean[][] visited = new boolean[height][width];
                List<Point> toRemove = new ArrayList<>();

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (charMatrix[i][j] == target && isAccessible(i, j)) {
                            toRemove.add(new Point(i, j));
                        }
                    }
                }

                for (Point p : toRemove) {
                    charMatrix[p.row][p.col] = '.';
                    total--;
                }

            } else {  // 크레인 (해당 문자를 전체에서 제거)
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (charMatrix[i][j] == target) {
                            charMatrix[i][j] = '.';
                            total--;
                        }
                    }
                }
            }
        }
        return total;
    }

    // 창고 외부와 연결된 블록인지 확인하는 함수 (BFS 사용)
    public static boolean isAccessible(int row, int col) {
        boolean[][] visited = new boolean[height][width];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (int[] d : dr) {
                int nr = curr.row + d[0];
                int nc = curr.col + d[1];

                if (nr < 0 || nr >= height || nc < 0 || nc >= width) {
                    return true; // 외부와 연결됨
                }
                if (!visited[nr][nc] && charMatrix[nr][nc] == '.') {
                    queue.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        return false;
    }

    // String 배열을 char 2D 배열로 변환
    public static char[][] convertToCharMatrix(String[] strArray) {
        int rows = strArray.length;
        int cols = strArray[0].length();
        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = strArray[i].toCharArray();
        }
        return matrix;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] storage1 = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests1 = {"A", "BB", "A"};
        System.out.println("남은 컨테이너 개수: " + sol.solution(storage1, requests1)); // Expected: 11

        String[] storage2 = {"HAH", "HBH", "HHH", "HAH", "HBH"};
        String[] requests2 = {"C", "B", "B", "B", "B", "H"};
        System.out.println("남은 컨테이너 개수: " + sol.solution(storage2, requests2)); // Expected: 4
    }
}
