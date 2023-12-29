package huawei;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-18
 */
public class Directory {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lines = Integer.parseInt(in.nextLine());

        for (int i = 0; i < lines; i++) {
            String curLine = in.nextLine();
        }
    }

    static class Node {
        public String value;
        public List<Node> sons;

        public Node(String value, List<Node> sons) {
            this.value = value;
            this.sons = sons;
        }
    }
}
