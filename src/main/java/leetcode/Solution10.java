package leetcode;

/**
 * @program algorithm
 * @description 正则表达式匹配
 * @author: hauerwu
 * @create: 2022-01-25 16:04
 **/

public class Solution10 {
    private class CompileContext{
        private String compiled;
        private int[] wildNums;

        private CompileContext(String compiled,int[] wildNums){
            this.compiled = compiled;
            this.wildNums = wildNums;
        }
    }

    public boolean isMatch(String s,String p){
        CompileContext compileContext = compile(p);
        int sPos = 0;
        int cPos = 0;
        return isMatchInternal(s,sPos,compileContext,cPos);
    }

    private boolean isMatchInternal(String s,int sPos,CompileContext compileContext,int cPos){
        String compiled = compileContext.compiled;
        int []wildNums = compileContext.wildNums;
        int i=sPos,j=cPos;
        for(;i<s.length()&&j<compiled.length();){
            char c = compiled.charAt(j);
            //是一个字符
            if(isAlphabet(c)){
                //精确匹配一个字符
                if(c == s.charAt(i)){
                    i++;
                    j++;
                    continue;
                }else{
                    //匹配失败
                    return false;
                }
            }else if(isDot(c)){
                //匹配.
                i++;
                j++;
                continue;
            }else if(isWild1(c)){
                //匹配x*
                //计算出最多和最少需要匹配的x，然后递归
                int minMatch = wildNums[j];
                int maxMatch = 0;
                for(int k=i;k<s.length() && s.charAt(k) == fromWild(c);k++){
                    maxMatch++;
                }
                int leftAlphaAndDotNum = computeAlphAndDotNum(compileContext,j+1);
                int leftSNum = s.length()-i;
                maxMatch = maxMatch >= (leftSNum-leftAlphaAndDotNum) ? leftSNum-leftAlphaAndDotNum: maxMatch;

                for(int k=minMatch;k<=maxMatch;k++){
                    boolean isMatch=true;
                    int n=i;
                    for(;n<i+k && n<s.length();n++){
                        if(s.charAt(n) != fromWild(c)) {
                            isMatch = false;
                            break;
                        }
                    }
                    if(isMatch){
                        //递归
                        boolean isLeftMatch = isMatchInternal(s,n,compileContext,j+1);
                        if(isLeftMatch){
                            return true;
                        }
                    }
                }

                return false;
            }else if(isWildAny(c)){
                //匹配.*
                //最少匹配0个字符，计算出最多能匹配的字符，然后递归
                int minMatch = 0;
                int maxMatch = computeAlphAndDotNum(compileContext,j+1);
                int leftSNum = s.length()-i;
                maxMatch = leftSNum-maxMatch;

                for(int k=minMatch;k<=maxMatch;k++){
                    //递归
                    boolean isLeftMatch = isMatchInternal(s,i+k,compileContext,j+1);
                    if(isLeftMatch){
                        return true;
                    }
                }

                return false;
            }

        }

        //处理掉末尾的.*或x*

        if(i==s.length() && j == compiled.length()){
            return true;
        }else if(i==s.length() && j <compiled.length()){
            for(int k=j;k<compiled.length();k++){
                char c = compiled.charAt(k);
                if(!isWild1(c) && !isWildAny(c)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private CompileContext compile(String p){
        CompileContext compileContext = new CompileContext(null,new int[p.length()]);
        StringBuilder result = new StringBuilder();

        int length = p.length();
        for(int i=0;i<length;){
            char c = p.charAt(i);
            if(isAlphabet(c)){
                int num = 1;
                int starNum =0;
                int j = i+1;
                for(;j<length;j++){
                    if(p.charAt(j) == c){
                        num++;
                    }else if(isStar(p.charAt(j))){
                        starNum++;
                    }else{
                        break;
                    }
                }
                if(starNum >0){
                    result.append(toWild(c));
                    num = num -starNum;
                    compileContext.wildNums[result.length()-1]=num;
                    i = j;
                }else{
                    result.append(c);
                    i++;
                }
            }else if(isDot(c)){
                if(i+1<length && isStar(p.charAt(i+1))){
                    result.append(':');
                    i+=2;
                }else{
                    result.append(c);
                    i++;
                }
            }else{
                //error
                return compileContext;
            }
        }

        compileContext.compiled = result.toString();

        return compileContext;
    }

    private boolean isAlphabet(char c){
        return c >= 'a' && c <= 'z';
    }

    private boolean isStar(char c){
        return c == '*';
    }

    private boolean isDot(char c){
        return c == '.';
    }

    private boolean isWildAny(char c){
        return c == ':';
    }

    private boolean isWild1(char c){
        return c >= 'A' && c <= 'Z';
    }

    private char fromWild(char c){
        return (char)(c - 'A' + 'a');
    }

    private char toWild(char c){
        return (char)(c + 'A' - 'a');
    }

    private int computeAlphAndDotNum(CompileContext compileContext,int start){
        int result = 0;
        String s = compileContext.compiled;
        int []wildNums = compileContext.wildNums;
        for(int i=start;i<s.length();i++){
            char c = s.charAt(i);
            if(isAlphabet(c) || isDot(c)){
                result++;
            }else if(isWild1(c)){
                result += wildNums[i];
            }
        }
        return result;
    }

    public static void main(String []args){
        Solution10 solution10 = new Solution10();
//        String []s = {"mississippi","mississippi","aab","ab","aa","aa","aaa"};
//        String []p= {"mis*is*ip*.","mis*is*p*.","c*a*b",".*","a*","a","a*a"};
        String []s = {"a"};
        String []p= {"ab*"};

//        String []s = {"acaabbaccbbacaabbbb"};
//        String []p={"a*.*b*.*a*aa*a*"};

        for(int i=0;i<s.length;i++){
            boolean isMatch = solution10.isMatch(s[i],p[i]);
            System.out.println(isMatch);
        }
    }
}
