package service.model;

import java.io.Serializable;

public class Student implements Serializable {
    private Long id;
    private String username;
    private int age;
    public Student(){}
    public Student(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
    public Student(String username, int age) {
        this.username = username;
        this.age = age;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: "+this.getId()+", USERNAME: "+this.getUsername()+", AGE: "+this.getAge();
    }
}
