public class Student {
    private String id;
    private String name;
    private double marks;

    // Constructor
    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Phương thức xếp hạng sinh viên
    public String rank() {
        if (marks >= 9.0) return "Excellent";
        if (marks >= 7.5) return "Very Good";
        if (marks >= 6.5) return "Good";
        if (marks >= 5.0) return "Medium";
        return "Fail";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank();
    }
}
