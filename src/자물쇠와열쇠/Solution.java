package 자물쇠와열쇠;

public class Solution {
    public static boolean solution(int[][] key, int[][] lock) {
        int[][] lockExtend =
                new int[lock.length + ((key.length - 1) * 2)][lock.length + ((key.length - 1) * 2)];

        int holeCount = calculateHoleInLock(lock);

        insertLockExtend(lock, lockExtend, key.length - 1);

        for (int rotate = 0; rotate < 4; rotate++) {
            if (isKeyOpenLock(key, lockExtend, holeCount)) {
                return true;
            }
            key = rotateKey(key);
        }

        return false;
    }


    private static int[][] rotateKey(int[][] key) {
        int[][] rotateKey = new int[key[0].length][key.length];
        for (int i = 0; i < rotateKey.length; i++) {
            for (int j = 0; j < rotateKey[0].length; j++) {
                rotateKey[i][j] = key[key.length - j - 1][i];
            }
        }
        return rotateKey;
    }

    private static boolean isKeyOpenLock(int[][] key, int[][] lockExtend, int holeCount) {
        for (int i = 0; i < lockExtend.length - key.length + 1; i++) {
            for (int j = 0; j < lockExtend.length - key.length + 1; j++) {
                int keyMatchCount = 0;
                boolean isMatchStatus = true;
                for (int k = 0; k < key.length; k++) {
                    for (int l = 0; l < key.length; l++) {
                        if (lockExtend[i + k][j + l] == 0 && key[k][l] == 1) {
                            keyMatchCount++;
                        }
                        else if (lockExtend[i + k][j + l] == 1 && key[k][l] == 1) {
                            isMatchStatus = false;
                            break;
                        }
                    }
                }
                if (isMatchStatus &&
                        keyMatchCount == holeCount) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int calculateHoleInLock(int[][] lock) {
        int holeCount = 0;

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) {
                    holeCount++;
                }
            }
        }

        return holeCount;
    }

    private static void insertLockExtend(int[][] lock, int[][] lockExtend, int start) {
        for (int i = 0; i < lockExtend.length; i++) {
            for (int j = 0; j < lockExtend.length; j++) {
                lockExtend[i][j] = 2;
            }
        }
        for (int i = start; i < lockExtend.length - start; i++) {
            for (int j = start; j < lockExtend.length - start; j++) {
                lockExtend[i][j] = lock[i - start][j - start];
            }
        }
    }

    public static void main(String[] args) {
        int[][] key = {{1, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        System.out.println(solution(key, lock));
    }
}
