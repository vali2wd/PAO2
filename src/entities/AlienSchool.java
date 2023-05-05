package entities;

public class AlienSchool {
    private int id;
    public String name;

    public AlienSchool(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AlienSchool() {
    }

    @Override
    public String toString() {
        return name + '#' + id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
