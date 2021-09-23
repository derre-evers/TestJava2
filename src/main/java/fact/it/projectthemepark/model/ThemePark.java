package fact.it.projectthemepark.model;

import java.util.ArrayList;

/* Derre Evers - r0832729 */
public class ThemePark {
    private String name;
    private int numberVisitors;
    private ArrayList<Attraction> attractions= new ArrayList<>();

    public ThemePark(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberVisitors() {
        return numberVisitors;
    }

    public void setNumberVisitors(int numberVisitors) {
        this.numberVisitors = numberVisitors;
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public int getNumberOfAttractions(){
        return attractions.size();
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public Attraction searchAttractionByName(String name){
        for (Attraction attraction : attractions) {
            if (attraction.getName().equals(name)) {
                return attraction;
            }
        }
        return null;
    }
    public void registerVisitor(Visitor visitor){
        numberVisitors +=1;
        visitor.setThemeParkCode(numberVisitors);
    }
}
