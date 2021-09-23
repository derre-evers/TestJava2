package fact.it.projectthemepark.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/* Derre Evers - r0832729 */
public class Staff extends Person{
    private LocalDate startDate;
    private boolean student;

    public Staff(String firstName, String surName) {
        super(firstName, surName);
        startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    @Override
    public String toString() {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (student) {
            return "Staff member " + super.toString() + " (working student) is employed since "+startDate.format(formattedDate);
        }
        else{
            return "Staff member " + super.toString() + " is employed since "+startDate.format(formattedDate);
        }
    }
}
