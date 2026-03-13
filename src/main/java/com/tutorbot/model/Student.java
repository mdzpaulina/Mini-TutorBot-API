package com.tutorbot.model;

public class Student {
 
    private int id;
    private String name;
    private String email;
    private String level;
 
    public Student() {}
 
    public Student(int id, String name, String email, String level) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.level = level;
    }
 
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getLevel() { return level; }
 
    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setLevel(String level) { this.level = level; }
}
 