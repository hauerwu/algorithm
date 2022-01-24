package cn.hauerwu.utils;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Random;

public class TestUtils {
    @Test
    public void testDigital(){
        Random rand = new Random();
        for(int i=0;i<100;i++){
            int orign = rand.nextInt(10000);
            int digitalLen = AlgUtil.getDigitalLen(orign);
            int []darray = new int[digitalLen];

            for(int j=0;j<digitalLen;j++){
                darray[j]=AlgUtil.getDigitalValue(orign,j+1);
            }

            int gen = 0;
            for(int j=digitalLen-1;j>=0;j--){
                gen = gen*10 + darray[j];
            }

            assertEquals(orign,gen);
        }
    }

}
