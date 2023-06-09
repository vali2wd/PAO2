package entities;

public class AlienChild {
    int id;
    String name;
    int yearOfStudy;
    char qwerty;
    char qwertz;
    char qzerty;
    char azerty;
    char dvorak;
    int schoolID;


    public AlienChild() {
    }

    public AlienChild(int id, String name, int yearOfStudy, char qwerty, char qwertz, char qzerty, char azerty, char dvorak, int schoolID) {
        this.id = id;
        this.name = name;
        this.yearOfStudy = yearOfStudy;
        this.qwerty = qwerty;
        this.qwertz = qwertz;
        this.qzerty = qzerty;
        this.azerty = azerty;
        this.dvorak = dvorak;
        this.schoolID = schoolID;
    }

    @Override
    public String toString() {
        return name + '#' + id;
    }

    public String toFile(){
        return this.id+" "+
        this.name+" "+
        this.yearOfStudy+" "+
        this.qwerty+" "+
        this.qwertz+" "+
        this.qzerty+" "+
        this.azerty+" "+
        this.dvorak+" "+
        this.schoolID;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public char getQwerty() {
        return qwerty;
    }

    public char getQwertz() {
        return qwertz;
    }

    public char getQzerty() {
        return qzerty;
    }

    public char getAzerty() {
        return azerty;
    }

    public char getDvorak() {
        return dvorak;
    }

    public int getschoolID() {
        return schoolID;
    }
}
