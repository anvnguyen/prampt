import java.io.*;
import java.util.*;

/*
                    0
            /       |      \
      5             3           6
      |
      4

      min = Integer.MAX;
      Stack: 0    ,
      Stack: 6 3 5, cost = 0
      Stack: 6 3 4 , cost  =5
      cost += 4 = 9
      Stack: 6 3, cost = 0


      Stack: 0 -> 5 -> 4 (9)
      Stack: 0 -> 3 - > 2 - > 1 -> 1 -> (7)

      Time complexity: O(N)
      Space complexity: O(N)

*/
class Solution {

    static class Node {

        int cost;
        ArrayList<Node> children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = new ArrayList<>();
            parent = null;
        }

        Node addNode(Node child) {
            this.children.add(child);
            child.parent = this;

            return child;
        }
    }

    static class SalesPath {

        int min = Integer.MAX_VALUE;
        int getCheapestCost(Node rootNode) {
            DS(rootNode, 0);

            return min;
        }

        void DS(Node root, int sum) {
            if(root.children.size() == 0) {
                if(min > sum) {
                    min = sum;
                }
                return;
            }

            sum += root.cost;

            for(Node n : root.children) {
                DS(n, sum);
            }
        }
    }

    /*********************************************
     * Driver program to test above method     *
     *********************************************/



    public static void main(String[] args) {
        Node root = new Node(0);

        Node child = root.addNode(new Node(5));
        child.addNode(new Node(4));

        Node child2 = root.addNode(new Node(3));
        child2 = child2.addNode(new Node(2));
        child2 = child2.addNode(new Node(1));
        child2 = child2.addNode(new Node(1));

        SalesPath sp = new SalesPath();
        System.out.println(sp.getCheapestCost(root));
    }
}