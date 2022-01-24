package nowcoder;

import java.util.*;


public class Solution {
    /**
     * 计算岛屿数量
     * @param grid char字符型二维数组 岛屿数组
     * @return int整型
     */
    public int numIslands (char[][] grid) {
        // write code here
        int result = 0;
        ArrayDeque<String> leftQueue = new ArrayDeque<String>();
        HashSet<String> closeMap = new HashSet<String>();

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                process(i,j,grid,leftQueue,closeMap);


                for(String k = leftQueue.poll();k !=null;k=leftQueue.poll()){
                    int []idx = key2Idx(k);
                    if(!closeMap.contains(k)){
                        process(idx[0],idx[1],grid,leftQueue,closeMap);
                    }
                }

                if(closeMap.size() >=1) {
                    result = result + 1;
                }
                for(String k:closeMap){
                    int []idx = key2Idx(k);
                    grid[idx[0]][idx[1]] = '0';
                }
                closeMap.clear();
            }
        }

        return result;
    }

    private char leftValue(char[][] grid,int i,int j){
        i = i-1;
        if(i<0){
            return '0';
        }

        return grid[i][j];
    }
    private char rightValue(char[][] grid,int i,int j){
        i = i+1;
        if(i>=grid.length){
            return '0';
        }

        return grid[i][j];
    }
    private char topValue(char[][] grid,int i,int j){
        j = j-1;
        if(j<0){
            return '0';
        }

        return grid[i][j];
    }
    private char bottomValue(char[][] grid,int i,int j){
        j = j+1;
        if(j>=grid[i].length){
            return '0';
        }

        return grid[i][j];
    }
    private String key(int i,int j){
        return ""+i+","+j;
    }
    private int[]key2Idx(String s){
        String []temp = s.split(",");
        int []result = new int[2];
        result[0] = Integer.parseInt(temp[0]);
        result[1] = Integer.parseInt(temp[1]);
        return result;
    }
    private void process(int i,int j, char [][] grid,ArrayDeque<String>  leftQue,HashSet<String> closeMap){
        if(grid[i][j] == '1'){
            if(leftValue(grid,i,j) == '1'){
                leftQue.add(key(i-1,j));
            }
            if(rightValue(grid,i,j) == '1'){
                leftQue.add(key(i+1,j));
            }
            if(topValue(grid,i,j) == '1'){
                leftQue.add(key(i,j-1));
            }
            if(bottomValue(grid,i,j) == '1'){
                leftQue.add(key(i,j+1));
            }
            closeMap.add(key(i,j));
        }
    }

    public static void test(){
        Solution s = new Solution();

        char [][]grids = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};

        s.numIslands(grids);
    }
}