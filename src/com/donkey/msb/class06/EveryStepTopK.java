package com.donkey.msb.class06;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr = [ 3   ,   3   ,   1   ,  2,      1,      2,      5…
 * op = [ T   ,   T,      T,     T,      F,      T,       F…
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品
 */
public class EveryStepTopK {

    public static class Customer {
        private int id;
        private int buyNum;
        private int enterTime;

        public Customer(int id, int buyNum, int enterTime) {
            this.id = id;
            this.buyNum = buyNum;
            this.enterTime = enterTime;
        }
    }

    public static class CandidateCustomer implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyNum != o2.buyNum ? o2.buyNum - o1.buyNum : o1.enterTime - o2.enterTime;
        }
    }

    public static class DaddyCustomer implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyNum != o2.buyNum ? o1.buyNum - o2.buyNum : o1.enterTime - o2.enterTime;
        }
    }

    public static class WhosYourDaddy {
        private Map<Integer, Customer> map;
        private HeapGreater<Customer> candi;
        private HeapGreater<Customer> daddy;
        private final int daddyLimit;

        public WhosYourDaddy(int daddyLimit) {
            this.daddyLimit = daddyLimit;
            this.map = new HashMap<>();
            this.candi = new HeapGreater<>(new CandidateCustomer());
            this.daddy = new HeapGreater<>(new DaddyCustomer());
        }

        public void operator(int time, int id, boolean buyOrRefund) {
            if (!buyOrRefund && !map.containsKey(id)) {
                return;
            }
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buyNum++;
            } else {
                c.buyNum--;
            }
            if (c.buyNum == 0) {
                map.remove(c.id);
            }
            if (!candi.contain(c) && !daddy.contain(c)) {
                if (daddy.size() < daddyLimit) {
                    c.enterTime = time;
                    daddy.push(c);
                } else {
                    c.enterTime = time;
                    candi.push(c);
                }
            } else if (daddy.contain(c)) {
                if (c.buyNum == 0) {
                    daddy.remove(c);
                } else {
                    daddy.resign(c);
                }
            } else {
                if (c.buyNum == 0) {
                    candi.remove(c);
                } else {
                    candi.resign(c);
                }
            }
            move(time);
        }

        public void move(int time) {
            if (candi.isEmpty()) {
                return;
            }
            if (daddy.size() < daddyLimit) {
                Customer customer = candi.pop();
                customer.enterTime = time;
                daddy.push(customer);
            } else {
                if (daddy.peek().buyNum < candi.peek().buyNum) {
                    Customer oldC = daddy.pop();
                    Customer newC = candi.pop();
                    oldC.enterTime = time;
                    newC.enterTime = time;
                    daddy.push(newC);
                    candi.push(oldC);
                }
            }
        }


        public List<Integer> getDaddies() {
            return daddy.getAllElement().stream().map(x -> x.id).collect(Collectors.toList());
        }
    }


    /**
     * 用堆来求解
     *
     * @param arr
     * @param op
     * @param k
     * @return
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourDaddy wyd = new WhosYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            wyd.operator(i, arr[i], op[i]);
            ans.add(wyd.getDaddies());
        }
        return ans;
    }


    /**
     * 暴力解法
     *
     * @return
     */
    public static List<List<Integer>> violentWay(int[] arr, boolean[] op, int k) {
        Map<Integer, Customer> map = new HashMap<>();
        List<Customer> candi = new ArrayList<>();
        List<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            // 1. 退货 && 没这人，无效事件
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurDaddy(daddy));
                continue;
            }
            // 2. 下单 && 没这人
            //    下单 && 有这人
            //    退货 && 有这人
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buyNum++;
            } else {
                c.buyNum--;
            }
            if (c.buyNum == 0) {
                map.remove(id);
            }
            if (!candi.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    daddy.add(c);
                    c.enterTime = i;
                } else {
                    candi.add(c);
                    c.enterTime = i;
                }
            }
            cleanZeroBuy(daddy);
            cleanZeroBuy(candi);
            daddy.sort(new DaddyCustomer());
            candi.sort(new CandidateCustomer());
            move(candi, daddy, k, i);
            ans.add(getCurDaddy(daddy));
        }
        return ans;
    }

    public static void move(List<Customer> candi, List<Customer> daddy, int k, int time) {
        if (candi.isEmpty()) {
            return;
        }
        if (daddy.size() < k) {
            Customer c = candi.get(0);
            daddy.add(c);
            c.enterTime = time;
            candi.remove(c);
        } else {
            if (candi.get(0).buyNum > daddy.get(0).buyNum) {
                Customer newC = candi.get(0);
                Customer oldC = daddy.get(0);
                candi.remove(newC);
                daddy.remove(oldC);
                newC.enterTime = time;
                oldC.enterTime = time;
                daddy.add(newC);
                candi.add(oldC);
            }
        }
    }

    public static List<Integer> getCurDaddy(List<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer customer : daddy) {
            ans.add(customer.id);
        }
        return ans;
    }

    /**
     * 这里需要注意： 我直接这样复制 arr = list，结果外层main函数arr的值还是没有变过来！
     * @param arr
     */
    public static void cleanZeroBuy(List<Customer> arr) {
        List<Customer> list = arr.stream().filter(x -> x.buyNum != 0).collect(Collectors.toList());
        arr.clear();
        for (Customer customer : list) {
            arr.add(customer);
        }
    }
//    测试上面方法使用
//    public static void main(String[] args) {
//        Customer x = new Customer(1,1,0);
//        Customer y = new Customer(2,0,1);
//        List<Customer> arr = new ArrayList<>();
//        arr.add(x);
//        arr.add(y);
//        cleanZeroBuy(arr);
//        System.out.println(arr);
//    }

    public static class Data {
        private int[] arr;
        private boolean[] op;

        public Data(int[] arr, boolean[] op) {
            this.arr = arr;
            this.op = op;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "arr=" + Arrays.toString(arr) +
                    ", op=" + Arrays.toString(op) +
                    '}';
        }
    }

    public static Data randomArr(int maxValue, int maxLen) {
        int[] arr = new int[(int) (Math.random() * maxLen)];
        boolean[] op = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxLen);
            op[i] = Math.random() > 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    public static boolean sameAns(List<List<Integer>> l1, List<List<Integer>> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            List<Integer> a = l1.get(i);
            List<Integer> b = l2.get(i);
            if (a.size() != b.size()) {
                return false;
            }
            a.sort((x, y) -> x - y);
            b.sort((x, y) -> x - y);
            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) != b.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 100;
        int maxK = 6;
        for (int i = 0; i < 10000; i++) {
            Data data = randomArr(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            List<List<Integer>> listA = topK(data.arr, data.op, k);
            List<List<Integer>> listB = violentWay(data.arr, data.op, k);
            if (!sameAns(listA,listB)){
                System.out.println(data.toString());
                System.out.println(listA);
                System.out.println(listB);
                System.out.println(k);
                System.out.println("Have BUG");
                break;
            }
        }
        System.out.println("End");
    }
}
