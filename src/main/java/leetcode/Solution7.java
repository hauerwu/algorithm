package leetcode;

/**
 * @program algorithm
 * @description 整数反转
 * @author: hauerwu
 * @create: 2022-01-24 14:49
 **/

public class Solution7 {
    public static void main(String[] argv){
        Solution7 solution7 = new Solution7();
        solution7.reverse(1534236469);
    }

    public int reverse(int x) {
        boolean isNagetive = x <0;
        Integer i = new Integer(Math.abs(x));
        StringBuilder builder = new StringBuilder().append(i);
        String reverse = builder.reverse().toString();
        try {
            i = Integer.parseInt(reverse);
        }catch (Exception e){
            i = 0;
        }
        if(isNagetive){
            i = 0 - i;
        }

        return i.intValue();
    }
}
