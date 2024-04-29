package model;

public class Director {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public Director(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Director{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
