package nowcoder;

import java.lang.String;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

/**
 * @program algorithm
 * @description now coder input/output test
 * @author: hauerwu
 * @create: 2022-01-21 09:26
 **/

public class IOTest {
    private static class Node{

        private int data;
        private Node(int i){
            data = i;
        }
    }

    public static void main(String []args){
        //testRef();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String temp[] = line.split(" ");

            Arrays.sort(temp, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return -(o1.compareTo(o2));
                }
            });

            for(String i:temp){
                System.out.print(i);
            }
            System.out.println();
        }

//        Integer i = new Integer(1000);
//        Integer j = new Integer(1001);
//        System.out.println(i.compareTo(j));

    }

    public static void testRef(){
        Node n1 = new Node(1);
        Node n2 = foo(n1);
        System.out.println("n1: "+n1.data);
        System.out.println("n2: "+n2.data);
    }

    public static Node foo(Node arg){
        arg.data = 3;
        arg = new Node(2);
        return arg;
    }
}
