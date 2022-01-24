package cn.hauerwu.structure;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestLinkList {

    @Test
    public void test(){
        LinkList<Integer> linkList = new LinkList<>();
        linkList.add(10);
        linkList.add(100);
        linkList.add(123);
        linkList.add(99);
        linkList.del(10);
        linkList.del(99);
        linkList.del(123);
        linkList.del(100);

//        for (Integer it:linkList
//             ) {
//            System.out.println(it);
//        }

        linkList.forEach((v)->{
            System.out.println(v);
        });

        assertEquals(linkList.getSize(),0);
    }

}
