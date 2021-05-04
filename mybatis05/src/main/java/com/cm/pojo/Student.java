package com.cm.pojo;


public class Student {
    private int id;
    private String name;
    private Teacher teacher;
    private int kid;

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", kid=" + kid +
                '}';
    }

    public Student(int id, String name, Teacher teacher, int kid) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.kid = kid;
    }

    public Student() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
