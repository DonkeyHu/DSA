package com.donkey.msb.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工信息的定义如下:
 * class Employee {
 * public int happy; // 这名员工可以带来的快乐值
 * List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 * <p>
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。树的头节点是公司唯一的老板。
 * 除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * <p>
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值
 */
public class Code03_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> next;

        public Employee(int happy) {
            this.happy = happy;
            next = new ArrayList<>();
        }
    }

    public static class Info {
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info process(Employee e) {
        if (e == null) {
            return new Info(0, 0);
        }
        int yes = e.happy;
        int no = 0;
        for (Employee employee : e.next) {
            Info info = process(employee);
            yes += info.no;
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes, no);
    }

    public static int maxHappy1(Employee e) {
        if (e == null) {
            return 0;
        }
        Info info = process(e);
        return Math.max(info.yes, info.no);
    }


    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process(Employee e, boolean up) {
        if (up) {
            int h = 0;
            for (Employee employee : e.next) {
                int x = process(employee, false);
                h += x;
            }
            return h;
        } else {
            int p1 = e.happy;
            int p2 = 0;
            for (Employee employee : e.next) {
                p1 += process(employee, true);
                p2 += process(employee, false);
            }
            return Math.max(p1, p2);
        }
    }

    public static int maxHappy2(Employee e) {
        if (e == null) {
            return 0;
        }
        return process(e, false);
    }


    public static void generateNext(Employee e, int level, int maxLevel, int maxNext, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int size = (int) (Math.random() * maxNext);
        for (int i = 0; i < size; i++) {
            Employee employee = new Employee((int) (Math.random() * maxHappy));
            e.next.add(employee);
            generateNext(employee, level + 1, maxLevel, maxNext, maxHappy);
        }
    }

    public static Employee generateBoss(int maxLevel, int maxNext, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        generateNext(boss, 1, maxLevel, maxNext, maxHappy);
        return boss;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxNext = 7;
        int maxHappy = 100;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            Employee boss = generateBoss(maxLevel, maxNext, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops");
                return;
            }
        }
        System.out.println("Finish");
    }

}
