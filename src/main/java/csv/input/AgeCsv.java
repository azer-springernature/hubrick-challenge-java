package csv.input;

public class AgeCsv {
    private String name;
    private Integer age;

    public AgeCsv(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
