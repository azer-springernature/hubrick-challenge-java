package model;

public class Employee {
    private Department department;
    private String name;
    private Gender gender;
    private Double salary;
    private Integer age;

    public Employee(Department department, String name, Gender gender, Double salary, Integer age) {
        this.department = department;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }
}
