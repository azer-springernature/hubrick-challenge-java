package csv.input;

public class EmployeeCsv {
    private Integer departmentId;
    private String name;
    private String gender;
    private Double salary;

    public EmployeeCsv(Integer departmentId, String name, String gender, Double salary) {
        this.departmentId = departmentId;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Double getSalary() {
        return salary;
    }
}
