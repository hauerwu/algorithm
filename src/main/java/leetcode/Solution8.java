package leetcode;

/**
 * @program algorithm
 * @description 实现atoi
 * @author: hauerwu
 * @create: 2022-01-24 15:17
 **/

public class Solution8 {
    private enum STATUS {
        START,BLANK,NUMBER,END
    }

    public int myAtoi(String s) {
        STATUS status = STATUS.START;
        boolean isNegative = false;
        char []numChars = new char[s.length()];
        int charNum = 0;


        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(status == STATUS.START){
                if(c ==' '){
                    status = STATUS.BLANK;
                }else if(c == '+'){
                    isNegative = false;
                    status = STATUS.NUMBER;
                }else if(c == '-'){
                    isNegative = true;
                    status = STATUS.NUMBER;
                }else if(isDigital(c)){
                    isNegative = false;
                    numChars[charNum++] = c;
                    status = STATUS.NUMBER;
                }else{
                    status = STATUS.END;
                    break;
                }
            }else if(status == STATUS.BLANK){
                if(c ==' '){
                    status = STATUS.BLANK;
                }else if(c == '+'){
                    isNegative = false;
                    status = STATUS.NUMBER;
                }else if(c == '-'){
                    isNegative = true;
                    status = STATUS.NUMBER;
                }else if(isDigital(c)){
                    isNegative = false;
                    numChars[charNum++] = c;
                    status = STATUS.NUMBER;
                }else{
                    status = STATUS.END;
                    break;
                }
            }else if(status == STATUS.NUMBER){
                if(c ==' '){
                    status = STATUS.END;
                    break;
                }else if(c == '+'){
                    status = STATUS.END;
                    break;
                }else if(c == '-'){
                    status = STATUS.END;
                    break;
                }else if(isDigital(c)){
                    numChars[charNum++] = c;
                    status = STATUS.NUMBER;
                }else{
                    status = STATUS.END;
                    break;
                }
            }
        }

        long result = 0;
        if(isNegative){
            for(int i=0;i<charNum;i++){

                result = result*10 - charValue(numChars[i]);
                if(result <Integer.MIN_VALUE){
                    result = Integer.MIN_VALUE;
                    break;
                }
            }
        }else {
            for (int i = 0; i < charNum; i++) {

                result = result * 10 + charValue(numChars[i]);
                if (result > Integer.MAX_VALUE) {
                    result = Integer.MAX_VALUE;
                    break;
                }
            }
        }

        return (int)result;
    }

    private boolean isDigital(char c){
        return c >= '0' && c<= '9';
    }

    private int charValue(char c){
        return c - '0';
    }

    public static void main(String []args){
        Solution8 solution8 = new Solution8();
        int result = solution8.myAtoi("-6147483648");
        System.out.println(result);
    }
}
