package leetcode;

import cn.hauerwu.structure.PriorityQueue;

/**
 * @program algorithm
 * @description Z字变形
 * @author: hauerwu
 * @create: 2022-01-20 17:37
 **/

public class Solution6 {
    public static String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        StringBuilder result[] = new StringBuilder[numRows];
        int step = 2*numRows-2;
        int length = s.length();
        for(int i=0;i<numRows;i++){
            result[i] = new StringBuilder();
        }

        for(int i=0;i<length;i+=step){
            for(int j=i;j<i+numRows&&j<length;j++){
                result[j-i].append(s.charAt(j));
            }

            for(int j=i+numRows;j<i+2*numRows-2 && j<length;j++){
                result[2*numRows+i-j-2].append(s.charAt(j));
            }
        }

        StringBuilder total = new StringBuilder();
        for(int i=0;i<numRows;i++){
            total.append(result[i].toString());
        }

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Integer[10]);


        return total.toString();
    }
}
