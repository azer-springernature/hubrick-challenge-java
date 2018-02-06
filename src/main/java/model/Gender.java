package model;

public enum Gender {
    MALE, FEMALE, OTHER, UNDEFINED;

    public static Gender from(String gender) {
        switch (gender) {
            case "m":
                return MALE;
            case "f":
                return FEMALE;
            case "":
                return UNDEFINED;
            default:
                return OTHER;
        }
    }
}
