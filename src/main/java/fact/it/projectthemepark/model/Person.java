package fact.it.projectthemepark.model;



/* Derre Evers - r0832729 */
public class Person {
    private String firstName;
    private String surName;

    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return surName.toUpperCase() + " "+ firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
    }
}
