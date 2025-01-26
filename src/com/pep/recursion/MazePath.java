package recursion;

import java.util.List;
import java.util.ArrayList;

public class MazePath {

    private static final int[][] DIR_3D = {{0, 1}, {1, 0}, {1, 1}};
    private static final int[][] DIR_8D = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
    private static final String[] DIR_8D_NAME = {"S", "SW", "W", "NW", "N", "NE", "E", "SE"};
    private static final char[] DIR_3D_NAME = {'H', 'V', 'D'};
    private static final int[] boardSize = {3, 3};

    public static void main(String[] args) {
        int sx = 0, sy = 0, dx = 2, dy = 2;
        List<String> resultHolder = new ArrayList<>();
        int result = threeDirectionMoves(sx, sy, dx, dy, new ArrayList<>(), resultHolder);
        System.out.printf("total paths from %d,%d to %d,%d is %d, with paths > %s\n", sx, sy, dx, dy, result, String.join(",", resultHolder));

        resultHolder = new ArrayList<>();
        result = threeDirectionMovesWithJump(sx, sy, dx, dy, new ArrayList<>(), resultHolder);
        System.out.printf("total paths from %d,%d to %d,%d is %d, with paths > %s\n", sx, sy, dx, dy, result, String.join(",", resultHolder));

        FloodfillResult floodFillResult = new FloodfillResult();
        infiniteJump(sx, sy, dx, dy, new int[dx + 1][dy + 1], new ArrayList<>(), false, floodFillResult, 0);
        System.out.printf("total paths from %d,%d to %d,%d is %d, with paths > %s\n", sx, sy, dx, dy, floodFillResult.totalSolutions, floodFillResult);

        floodFillResult = new FloodfillResult();
        infiniteJump(sx, sy, dx, dy, new int[dx + 1][dy + 1], new ArrayList<>(), true, floodFillResult, 0);
        System.out.printf("total paths from %d,%d to %d,%d is %d, with paths > %s\n", sx, sy, dx, dy, floodFillResult.totalSolutions, floodFillResult);

        floodFillResult = infiniteJump01(sx, sy, dx, dy, new int[dx + 1][dy + 1]);
        System.out.printf("total paths from %d,%d to %d,%d is %d, with paths > %s\n", sx, sy, dx, dy, floodFillResult.totalSolutions, floodFillResult);

        result = infiniteJump02(sx, sy, dx, dy, new int[dx + 1][dy + 1], 0, new ArrayList<>());
        System.out.printf("infiniteJump02 - total paths from %d,%d to %d,%d is %d, with paths > %s\n", sx, sy, dx, dy, result, floodResult);
    }

    private static final FloodfillResult floodResult = new FloodfillResult();
    private static int infiniteJump02(int sRow, int sCol, int dRow, int dCol, int[][] grid, int runningPathLen, List<String> runningPath) {
        if (sRow == dRow && sCol == dCol) {
            if (runningPathLen > floodResult.maxDistancePathLen) {
                floodResult.maxDistancePathLen = runningPathLen;
                floodResult.maxDistancePath = String.join("", runningPath);
            }
            if (runningPathLen < floodResult.minDistancePathLen) {
                floodResult.minDistancePathLen = runningPathLen;
                floodResult.minDistancePath = String.join("", runningPath);
            }
            return 1;
        }

        grid[sRow][sCol] = 1;
        int count = 0;
        for (int dir = 0; dir < DIR_8D.length; dir++) {
            for (int jump = 1; jump < Math.max(grid.length, grid[0].length); jump++) {
                int row = sRow + (DIR_8D[dir][0] * jump);
                int col = sCol + (DIR_8D[dir][1] * jump);
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 0) {
                    runningPath.add(DIR_8D_NAME[dir] + jump);
                    count += infiniteJump02(row, col, dRow, dCol, grid, runningPathLen + 1, runningPath);
                    runningPath.remove(runningPath.size() - 1);
                }
            }
        }
        grid[sRow][sCol] = 0;
        return count;
    }

    private static FloodfillResult infiniteJump01(int sx, int sy, int dx, int dy, int[][] grid) {
        if (sx == dx && sy == dy) {
            FloodfillResult floodFillResult = new FloodfillResult();
            floodFillResult.totalSolutions = 1;
            floodFillResult.maxDistancePathLen = 0;
            floodFillResult.minDistancePathLen = 0;
            return floodFillResult;
        }

        grid[sx][sy] = 1;
        FloodfillResult floodFillResult = new FloodfillResult();
        floodFillResult.totalSolutions = 0;
        floodFillResult.maxDistancePathLen = Integer.MIN_VALUE;
        floodFillResult.minDistancePathLen = Integer.MAX_VALUE - 1;

        for (int i = 0; i < DIR_8D.length; i++) {
            for (int jump = 1; jump < Math.max(grid.length, grid[0].length); jump++) {
                int x = sx + (DIR_8D[i][0] * jump);
                int y = sy + (DIR_8D[i][1] * jump);
                if (x >= 0 && y >= 0 && x < grid[0].length && y < grid.length && grid[x][y] != 1) {
                    FloodfillResult floodFillResultTemp = infiniteJump01(x, y, dx, dy, grid);
                    if (floodFillResultTemp.maxDistancePathLen > floodFillResult.maxDistancePathLen) {
                        floodFillResult.maxDistancePathLen = floodFillResultTemp.maxDistancePathLen;
                        floodFillResult.maxDistancePath = DIR_8D_NAME[i] + jump + floodFillResultTemp.maxDistancePath;
                    }
                    if (floodFillResultTemp.minDistancePathLen < floodFillResult.minDistancePathLen) {
                        floodFillResult.minDistancePathLen = floodFillResultTemp.minDistancePathLen;
                        floodFillResult.minDistancePath = DIR_8D_NAME[i] + jump + floodFillResultTemp.minDistancePath;
                    }
                    floodFillResult.totalSolutions = floodFillResult.totalSolutions + floodFillResultTemp.totalSolutions;
                } else break;
            }
        }

        floodFillResult.minDistancePathLen += 1;
        floodFillResult.maxDistancePathLen += 1;
        grid[sx][sy] = 0;
        return floodFillResult;
    }

    private static void infiniteJump(int sx, int sy, int dx, int dy, int[][] grid, List<String> path, boolean isMultiMove, FloodfillResult result, int runningPathLength) {
        if (sx == dx && sy == dy) {
            String pathString = String.join("", path);
            result.resultHolder.add(pathString);
            result.totalSolutions += 1;
            // handle max path
            if (runningPathLength > result.maxDistancePathLen) {
                result.maxDistancePathLen = runningPathLength;
                result.maxDistancePath = pathString;
            }
            // handle min path
            if (runningPathLength < result.minDistancePathLen) {
                result.minDistancePathLen = runningPathLength;
                result.minDistancePath = pathString;
            }
            // handle avg path
            // todo : running average
            return;
        }
        grid[sx][sy] = 1;
        for (int i = 0; i < DIR_8D.length; i++) {
            for (int jump = 1; jump < (isMultiMove ? Math.max(grid[0].length, grid.length) : 2); jump++) {
                int x = sx + (DIR_8D[i][0] * jump);
                int y = sy + (DIR_8D[i][1] * jump);
                if (x <= dx && y <= dy && x >= 0 && y >= 0 && grid[x][y] != 1) {
                    path.add(DIR_8D_NAME[i] + jump);
                    infiniteJump(x, y, dx, dy, grid, path, isMultiMove, result, runningPathLength + 1);
                    path.remove(path.size() - 1);
                } else break;
            }
        }
        grid[sx][sy] = 0;
    }

    private static int threeDirectionMovesWithJump(int sx, int sy, int dx, int dy, List<String> runningPath, List<String> pathHolder) {
        if (sx == dx && sy == dy) {
            pathHolder.add(String.join("", runningPath));
            return 1;
        }

        int count = 0;
        for (int i = 0; i < DIR_3D.length; i++) {
            for (int jump = 1; jump <= Math.max(boardSize[0], boardSize[1]); jump++) {
                int x = sx + (DIR_3D[i][0] * jump), y = sy + (DIR_3D[i][1] * jump);
                if (x < boardSize[0] && y < boardSize[1]) {
                    runningPath.add(String.valueOf(DIR_3D_NAME[i]) + jump);
                    count += threeDirectionMovesWithJump(x, y, dx, dy, runningPath, pathHolder);
                    runningPath.remove(runningPath.size() - 1);
                } else break;
            }
        }
        return count;
    }


    private static int threeDirectionMoves(int sx, int sy, int dx, int dy, List<String> runningPath, List<String> pathHolder) {
        if (sx == dx && sy == dy) {
            pathHolder.add(String.join("", runningPath));
            return 1;
        }

        int count = 0;
        for (int i = 0; i < DIR_3D.length; i++) {
            int x = sx + DIR_3D[i][0], y = sy + DIR_3D[i][1];
            if (x < boardSize[0] && y < boardSize[1]) {
                runningPath.add(String.valueOf(DIR_3D_NAME[i]));
                count += threeDirectionMoves(x, y, dx, dy, runningPath, pathHolder);
                runningPath.remove(runningPath.size() - 1);
            }
        }
        return count;
    }

    static class FloodfillResult {
        public int totalSolutions, maxDistancePathLen, minDistancePathLen = Integer.MAX_VALUE, avgDistance;
        public List<String> resultHolder = new ArrayList<>();
        public String maxDistancePath = "", minDistancePath = "";

        public String toString() {
            return String.format("\ntotalSolutions = %d,\nmaxDistancePathLen = %d,\nminDistancePathLen = %d,\navgDistance = %d,\nmaxDistancePath = %s,\nminDistancePath = %s,\nresultHolder = %s,\n",
                    totalSolutions, maxDistancePathLen, minDistancePathLen, avgDistance, maxDistancePath, minDistancePath, String.join(",", resultHolder));
        }
    }
}
