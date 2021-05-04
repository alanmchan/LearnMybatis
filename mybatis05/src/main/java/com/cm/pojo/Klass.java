package com.cm.pojo;

import java.util.List;

public class Klass {
    private int id;
    private String name;
    private List<Student> students; // 一个班级多个学生

    @Override
    public String toString() {
        return "Klass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public Klass(int id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Klass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
