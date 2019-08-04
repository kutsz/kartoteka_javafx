package db;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String birthDate;

    Person(String name, String surname, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    Person(int id,String name, String surname, String birthDate) {
        this.id =id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public  void  setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return name + " " + surname + " " + "(" + birthDate + ")";

    }


}