package com.donkey.msb.class35;

public class Code01_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        public V v;
        public K k;
        public int h;

        public AVLNode(K k, V v) {
            this.k = k;
            this.v = v;
            this.h = 1;
        }
    }


    public static class AVLTreeMap<K extends Comparable<K>, V> {
        public AVLNode<K, V> root;
        public int size;

        public AVLTreeMap() {
        }


        public AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> res = cur.l;
            cur.l = res.r;
            res.r = cur;
            cur.h = Math.max(cur.r != null ? cur.r.h : 0, cur.l != null ? cur.l.h : 0) + 1;
            res.h = Math.max(res.r != null ? res.r.h : 0, res.l != null ? res.l.h : 0) + 1;
            return res;
        }

        public AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> res = cur.r;
            cur.r = res.l;
            res.l = cur;
            cur.h = Math.max(cur.r != null ? cur.r.h : 0, cur.l != null ? cur.l.h : 0) + 1;
            res.h = Math.max(res.r != null ? res.r.h : 0, res.l != null ? res.l.h : 0) + 1;
            return res;
        }

        // 维持平衡性，四种情况LL，LR，RR，RL
        public AVLNode<K, V> balance(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHigh = cur.l != null ? cur.l.h : 0;
            int rightHigh = cur.r != null ? cur.r.h : 0;
            if (Math.abs(leftHigh - rightHigh) > 1) {
                if (leftHigh > rightHigh) {
                    int leftLeft = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    if (leftLeft >= leftRight) {
                        cur = rightRotate(cur);
                    } else {
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                } else {
                    int rightRight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    int rightLeft = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;

                    if (rightRight >= rightLeft) {
                        cur = leftRotate(cur);
                    } else {
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        public AVLNode<K, V> add(AVLNode<K, V> cur, K k, V v) {
            if (cur == null) {
                return new AVLNode<>(k, v);
            } else {
                if (k.compareTo(cur.k) < 0) {
                    cur.l = add(cur.l, k, v);
                }
                if (k.compareTo(cur.k) > 0) {
                    cur.r = add(cur.r, k, v);
                }
                cur.h = Math.max(cur.r != null ? cur.r.h : 0, cur.l != null ? cur.l.h : 0) + 1;
                return balance(cur);
            }
        }

        public AVLNode<K, V> delete(AVLNode<K, V> cur, K k) {
            if (k.compareTo(cur.k) < 0) {
                cur.l = delete(cur.l, k);
            } else if (k.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, k);
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else {
                    // 这段代码逻辑有点精妙
                    AVLNode<K, V> des = cur.r;
                    while (des.l != null) {
                        des = des.l;
                    }
                    cur.r = delete(cur.r, des.k);
                    // 下面是把des节点替换到cur节点的逻辑，我在想des右边还会有数据吗？->没数据了，因为有上一步delete逻辑
                    des.l = cur.l;
                    des.r = cur.r;
                    cur = des;
                }
            }
            if (cur != null) {
                cur.h = Math.max(cur.r != null ? cur.r.h : 0, cur.l != null ? cur.l.h : 0) + 1;
            }
            return balance(cur);
        }

        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.k) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return ans;
        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;
        }

        public int size() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;
            } else {
                size++;
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            }
            return null;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.l != null) {
                cur = cur.l;
            }
            return cur.k;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur.k;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }

    }

}
