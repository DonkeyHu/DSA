package com.donkey.msb.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 以下代码全部实现了。
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 */
public class SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 先序方式序列化
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    // 先序方式返序列化
    public static Node preBuild(Queue<String> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return preb(data);
    }

    public static Node preb(Queue<String> data) {
        String value = data.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(data);
        head.right = preb(data);
        return head;
    }


    // 后序方式序列化
    public static Queue<String> posSerial(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        pos(head, ans);
        return ans;
    }

    public static void pos(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            pos(head.left, ans);
            pos(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    // 后序方式反序列化
    public static Node posBuild(Queue<String> ans) {
        if (ans == null || ans.size() == 0) {
            return null;
        }
        // 左右中 --> stack(中右左)
        Stack<String> stack = new Stack<>();
        while (!ans.isEmpty()) {
            stack.push(ans.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> ans) {
        String v = ans.pop();
        if (v == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(v));
        // 不太理解
        head.right = posb(ans);
        head.left = posb(ans);
        return head;
    }

    // 按层遍历序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            return null;
        } else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    ans.add(String.valueOf(cur.left.value));
                } else {
                    ans.add(null);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    ans.add(String.valueOf(cur.right.value));
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    // 按层遍历反序列化
    public static Node levelBuild(Queue<String> ans) {
        if (ans == null || ans.size() == 0) {
            return null;
        }
        Node head = generateNode(ans.poll());
        if (head == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.left = generateNode(ans.poll());
            cur.right = generateNode(ans.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return head;
    }

    public static Node generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.4) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static boolean isSameStructure(Node n1, Node n2) {
        if (n1 == null && n2 != null) {
            return false;
        }
        if (n1 != null && n2 == null) {
            return false;
        }
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1.value != n2.value) {
            return false;
        }
        return isSameStructure(n1.left, n2.left) && isSameStructure(n1.right, n2.right);
    }


    public static void main(String[] args) {
        int level = 5;
        int maxValue = 100;
        int times = 100000;
        System.out.println("test begin");
        for (int i = 0; i < times; i++) {
            Node cur = generateRandomBST(level, maxValue);
            Node pre = preBuild(preSerial(cur));
            Node pos = posBuild(posSerial(cur));
            Node lb = levelBuild(levelSerial(cur));
            if (!isSameStructure(pre, pos) || !isSameStructure(pos, lb)) {
                System.out.println("Bug");
                return;
            }
        }
        System.out.println("Finish~!");
    }
}
