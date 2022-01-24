package cn.hauerwu.alg.converter;

import cn.hauerwu.utils.AlgUtil;

public class DigitalConverter implements Converter<Integer> {
    private int digital;

    public DigitalConverter(int digital){
        this.digital = digital;
    }

    public Integer convert(Integer value){
        return AlgUtil.getDigitalValue(value,this.digital);
    }
}
