import java.util.ArrayList;
import java.util.List;

public class BaseUtils {
    /*
    * ShortFirst DFS algorithm initialization
    */
    public String initDFS(char startStn, char destStn, int[][] distMap) {
        int start = startStn - 'A';
        int end = destStn - 'A';
        List<Integer> pathList = new ArrayList<Integer>();
        int bestDist = 0;
        bestDist = dfsProc(start, end, distMap, pathList);
        if (bestDist == -1 || bestDist == 0) {
            return "ERROR";
        } else {
            return Integer.toString(bestDist);
        }
    }

    /*
    * ShortFirst DFS algorithm Processing
    */
    int dfsProc(int start, int end, int[][] distMap, List<Integer> pathList) {
        int bestDist = 0;
        int curtDist = 0;
        pathList.add(start);
        int len = pathList.size();
        if (len > 1 && pathList.get(len - 1) == end) {
            pathList.remove(len - 1);
            return 0;
        }
        for (int i = 0; i < distMap[start].length; i++) {
            if (distMap[start][i] > 0) {
                if (len > 1 && pathList.subList(1, len).contains(i))
                    continue;
                else {
                    int dist = dfsProc(i, end, distMap, pathList);
                    if (dist == -1)
                        continue;
                    else {
                        curtDist = distMap[start][i] + dist;
                        if (bestDist == 0 || curtDist < bestDist) {
                            bestDist = curtDist;
                        }
                    }
                }
            }
        }
        if (bestDist != 0) {
            pathList.remove(len - 1);
            return bestDist;
        } else {
            pathList.remove(len - 1);
            return -1;
        }
    }

    /*
    * MaxConstrain DFS algorithm Processing
    */
    public int initMaxConsDFS(int maxStep, int[][] distMap, char startStn, char destStn) {
//
        int start = startStn - 'A';
        int end = destStn - 'A';
        List<Integer> pathList = new ArrayList<Integer>();
        ArrayList<List<Integer>> count = new ArrayList<List<Integer>>();
        maxConsDFSProc(start, end, count, distMap, pathList, maxStep);
        return count.size();
    }

    public void maxConsDFSProc(int start, int end, ArrayList<List<Integer>> count, int[][] distMap, List<Integer> pathList, int maxStep) {
        pathList.add(start);
        int len = pathList.size();
        if (len - 1 >= maxStep) {
            if (start == end) {
                count.add(pathList);
            }
            pathList.remove(len - 1);
            return;
        }
        if (start == end && len - 1 > 0) {
            count.add(pathList);
        }
        for (int i = 0; i < distMap[start].length; i++) {
            if (distMap[start][i] > 0) {
                maxConsDFSProc(i, end, count, distMap, pathList, maxStep);
            }
        }
        pathList.remove(len - 1);
    }


    /*
    * ExectConstrain DFS algorithm Processing
    */
    public int initExectConsDFS(int exectStep, int[][] distMap, char startStn, char destStn) {
        int start = startStn - 'A';
        int end = destStn - 'A';
        List<Integer> pathList = new ArrayList<Integer>();
        ArrayList<List<Integer>> count = new ArrayList<List<Integer>>();
        ExectConsDFSProc(start, end, count, distMap, pathList, exectStep);
        return count.size();
    }

    public void ExectConsDFSProc(int start, int end, ArrayList<List<Integer>> count, int[][] distMap, List<Integer> pathList, int exectStep) {
        pathList.add(start);
        int len = pathList.size();
        if (len - 1 >= exectStep) {
            if (start == end) {
                count.add(pathList);
            }
            pathList.remove(len - 1);
            return;
        }
        for (int i = 0; i < distMap[start].length; i++) {
            if (distMap[start][i] > 0) {
                ExectConsDFSProc(i, end, count, distMap, pathList, exectStep);
            }
        }
        pathList.remove(len - 1);
    }

    /*
    * MaxConstrain DFS algorithm Processing
    */
    public int initDistConsDFS(int maxDist, int[][] distMap, char startStn, char destStn) {
        int start = startStn - 'A';
        int end = destStn - 'A';
        List<Integer> pathList = new ArrayList<Integer>();
        int pathDist = 0;
        ArrayList<List<Integer>> count = new ArrayList<List<Integer>>();
        distConsDFSProc(start, end, count, distMap, pathList, maxDist, pathDist);
        return count.size();
    }

    public void distConsDFSProc(int start, int end, ArrayList<List<Integer>> count, int[][] distMap, List<Integer> pathList, int maxDist, int pathDist) {
        pathList.add(start);
        int len = pathList.size();
        if (pathDist >= maxDist) {
            pathList.remove(len - 1);
            return;
        }
        if (start == end && len - 1 > 0) {
//  System.out.println(pathList);
//  System.out.println(pathDist);
            count.add(pathList);
        }
        for (int i = 0; i < distMap[start].length; i++) {
            if (distMap[start][i] > 0) {
                distConsDFSProc(i, end, count, distMap, pathList, maxDist, pathDist + distMap[start][i]);
            }
        }
        pathList.remove(len - 1);
    }
}