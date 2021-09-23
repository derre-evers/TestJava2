package fact.it.projectthemepark.model;

import java.util.ArrayList;

/* Derre Evers - r0832729 */
public class Visitor extends Person{
    private int yearOfBirth;
    private int themeParkCode;
    private ArrayList<String> wishList = new ArrayList<>();

    public Visitor(String firstName, String surName) {
        super(firstName, surName);
        themeParkCode = -1;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getThemeParkCode() {
        return themeParkCode;
    }

    public void setThemeParkCode(int themeParkCode) {
        this.themeParkCode = themeParkCode;
    }

    public boolean addToWishList(String attractionName){

        if (wishList.size() < 5){
            wishList.add(attractionName);
            return true;
        }
        else {
            return false;
        }
    }

    public int getNumberOfWishes(){
        return wishList.size();
    }

    public ArrayList<String> getWishList() {
        return wishList;
    }

    @Override
    public String toString() {
        return "Visitor "+ super.toString() + " with theme park code "+themeParkCode;
    }
}
