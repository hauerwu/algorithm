package cn.hauerwu.alg.sort;

import org.junit.Test;
import cn.hauerwu.alg.string.KMP;

/**
 * @program algorithm
 * @description test kmp
 * @author: hauerwu
 * @create: 2022-01-11 17:50
 **/

public class TestKMP {
    @Test
    public void TestKMP(){
        KMP kmp = new KMP("ababaab1");
        kmp.search("ababbabb112abababaab1");
    }
}
