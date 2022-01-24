package cn.hauerwu.alg.comparer;

import cn.hauerwu.utils.AlgUtil;

public class IntegerDigitalComparer implements Comparer<Integer>{
    private int digital;//比较哪一位

    public IntegerDigitalComparer(int digital){
        this.digital = digital;
    }
    public int compare(Integer from,Integer to){
        Integer fromDigital = AlgUtil.getDigitalValue(from,this.digital);
        Integer toDigital = AlgUtil.getDigitalValue(to,this.digital);

        return  fromDigital.compareTo(toDigital);
    }
}
