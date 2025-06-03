package com.donkey.msb.class36;

/**
 * SB树
 */
public class Code01_SizeBalancedTreeMap {

    public static class SBTNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        public int size;

        public SBTNode(K k, V v) {
            this.k = k;
            this.v = v;
            this.size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        public SBTNode<K, V> root;

        public SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> ans = cur.left;
            cur.left = ans.right;
            ans.right = cur;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            ans.size = (ans.left != null ? ans.left.size : 0) + (ans.right != null ? ans.right.size : 0) + 1;
            return ans;
        }

        public SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> ans = cur.right;
            cur.right = ans.left;
            ans.left = cur;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            ans.size = (ans.left != null ? ans.left.size : 0) + (ans.right != null ? ans.right.size : 0) + 1;
            return ans;
        }

        public SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int left = cur.left != null ? cur.left.size : 0;
            int right = cur.right != null ? cur.right.size : 0;

            int leftLeft = cur.left != null ? cur.left.left != null ? cur.left.left.size : 0 : 0;
            int leftRight = cur.left != null ? cur.left.right != null ? cur.left.right.size : 0 : 0;
            int rightRight = cur.right != null ? cur.right.right != null ? cur.right.right.size : 0 : 0;
            int rightLeft = cur.right != null ? cur.right.left != null ? cur.right.left.size : 0 : 0;

            if (leftLeft > right) {
                cur = rightRotate(cur);
//                cur.right = maintain(cur.right);
//                cur = maintain(cur);
            } else if (leftRight > right) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
//                cur.left = maintain(cur.left);
//                cur.right = maintain(cur.right);
//                cur = maintain(cur);
            } else if (rightRight > left) {
                cur = leftRotate(cur);
//                cur.left = maintain(cur.left);
//                cur = maintain(cur);
            } else if (rightLeft > left) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
//                cur.left = maintain(cur.left);
//                cur.right = maintain(cur.right);
//                cur = maintain(cur);
            }else{
                return cur;
            }
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
            return cur;
        }

        public SBTNode<K, V> add(SBTNode<K, V> cur, K k, V v) {
            if (cur == null) {
                return new SBTNode<>(k, v);
            }
            if (k.compareTo(cur.k) < 0) {
                cur.left = add(cur.left, k, v);
            } else if (k.compareTo(cur.k) > 0) {
                cur.right = add(cur.right, k, v);
            }
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            return maintain(cur);
        }

        public SBTNode<K, V> delete(SBTNode<K, V> cur, K k) {
            if (k.compareTo(cur.k) < 0) {
                cur.left = delete(cur.left, k);
            } else if (k.compareTo(cur.k) > 0) {
                cur.right = delete(cur.right, k);
            } else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else {
                    SBTNode<K, V> dest = cur.right;
                    while (dest.left != null) {
                        dest = dest.left;
                    }
                    cur.right = delete(cur.right, dest.k);
                    dest.left = cur.left;
                    dest.right = cur.right;
                    cur = dest;
                }
            }
            if (cur != null) {
                cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            }
            return cur;
        }

        public SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> cur = root;
            SBTNode<K, V> pre = root;
            while (cur != null) {
                pre = cur;
                if (cur.k.compareTo(key) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        public SBTNode<K, V> findLastNoSmallIndex(K key) {
            SBTNode<K, V> ans = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        public SBTNode<K, V> findLastNoBigIndex(K key) {
            SBTNode<K, V> ans = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.left;
                } else {
                    ans = cur;
                    cur = cur.right;
                }
            }
            return ans;
        }

        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
            if (kth == (cur.left != null ? cur.left.size : 0) + 1) {
                return cur;
            } else if (kth <= (cur.left != null ? cur.left.size : 0)) {
                return getIndex(cur.left, kth);
            } else {
                return getIndex(cur.right, kth - (cur.left != null ? cur.left.size : 0) - 1);
            }
        }

        public int size() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).k;
        }

        public V getIndexValue(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).v;
        }

        public void put(K key, V value) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;
            } else {
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            } else {
                return null;
            }
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.k;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.k;
        }

        public K floorKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }

    }

    // for test
    public static void printAll(SBTNode<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    // for test
    public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + "(" + head.k + "," + head.v + ")" + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    // for test
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
        sbt.put("d", 4);
        sbt.put("c", 3);
        sbt.put("a", 1);
        sbt.put("b", 2);
        // sbt.put("e", 5);
        sbt.put("g", 7);
        sbt.put("f", 6);
        sbt.put("h", 8);
        sbt.put("i", 9);
        sbt.put("a", 111);
        System.out.println(sbt.get("a"));
        sbt.put("a", 1);
        System.out.println(sbt.get("a"));
        for (int i = 0; i < sbt.size(); i++) {
            System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
        }
        printAll(sbt.root);
        System.out.println(sbt.firstKey());
        System.out.println(sbt.lastKey());
        System.out.println(sbt.floorKey("g"));
        System.out.println(sbt.ceilingKey("g"));
        System.out.println(sbt.floorKey("e"));
        System.out.println(sbt.ceilingKey("e"));
        System.out.println(sbt.floorKey(""));
        System.out.println(sbt.ceilingKey(""));
        System.out.println(sbt.floorKey("j"));
        System.out.println(sbt.ceilingKey("j"));
        sbt.remove("d");
        printAll(sbt.root);
        sbt.remove("f");
        printAll(sbt.root);

    }

}

