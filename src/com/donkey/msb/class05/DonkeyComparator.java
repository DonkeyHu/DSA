package com.donkey.msb.class05;

import java.util.*;

public class DonkeyComparator {

    public static class Student{
        private String name;
        private int age;
        private int id;

        public Student(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    // ID升序，ID相同时，Age降序
    public static class IDAscendingAgeDescending implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? o1.id - o2.id : o2.age - o1.age;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("A",18,3);
        Student s2 = new Student("B",20,3);
        Student s3 = new Student("C",30,2);
        Student s4 = new Student("D",25,1);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.sort(new IDAscendingAgeDescending());

        for (Student student : list) {
            System.out.println(student.toString());
        }

        TreeMap<Student,String> treeMap = new TreeMap<>(new IDAscendingAgeDescending());
        treeMap.put(s1,"NO1");
        treeMap.put(s2,"NO2");
        treeMap.put(s3,"NO3");
        treeMap.put(s4,"NO4");

        System.out.println("========");

        for (Student s : treeMap.keySet()) {
            System.out.println(s.toString());
        }
    }

}
