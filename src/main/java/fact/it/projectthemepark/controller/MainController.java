package fact.it.projectthemepark.controller;

import fact.it.projectthemepark.model.Attraction;
import fact.it.projectthemepark.model.Staff;
import fact.it.projectthemepark.model.ThemePark;
import fact.it.projectthemepark.model.Visitor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.beans.Visibility;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/* Derre Evers - r0832729*/
@Controller
public class MainController {

    private ArrayList<Staff> staffMembers;
    private ArrayList<Visitor> visitors;
    private ArrayList<ThemePark> themeParks;

@PostConstruct
private void fillData(){
    staffMembers = fillStaffMembers();
    visitors = fillVisitors();
    themeParks = fillThemeParks();

}


//Write your code here
@RequestMapping("/index")
    public String index(){
    return "index";
}

@RequestMapping("/1_newVisitor")
    public String newVisitor(Model model){
    model.addAttribute("themeParks", themeParks);
    return "1_newVisitor";
}

@RequestMapping("/submitNewVisitor")
    public String submitNewVisitor(HttpServletRequest request, Model model){
    String firstname = request.getParameter("firstname");
    String surname = request.getParameter("surname");
    Integer yearOfBirth = Integer.parseInt(request.getParameter("birthyear"));

    Visitor visitor = new Visitor(firstname, surname);
    visitor.setYearOfBirth(yearOfBirth);

    int parkIndex = Integer.parseInt(request.getParameter("parkIndex"));
    ThemePark returnThemePark = themeParks.get(parkIndex);
    returnThemePark.registerVisitor(visitor);
    model.addAttribute("visitor", visitor);

    return "2_visitorDetails";
}

@RequestMapping("/3_newStaffMember")
    public String newStaffMember(){
    return "3_newStaffMember";
}

@RequestMapping("/submitNewStaffMember")
    public String submitNewStaffMember(HttpServletRequest request, Model model){
    String firstname = request.getParameter("firstname");
    String surname = request.getParameter("surname");
    LocalDate employedSince = LocalDate.parse(request.getParameter("employedSince"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    boolean student = (request.getParameter("student") != null);

    Staff staff = new Staff(firstname, surname);
    staff.setStartDate(employedSince);
    staff.setStudent(student);

    model.addAttribute("staff", staff);
    staffMembers.add(staff);
    return "4_staffMemberDetails";
}

@RequestMapping("/5_staffList")
    public String staffList(Model model){

    model.addAttribute("staffMembers", staffMembers);

    return "5_staffList";
}
@RequestMapping("/6_visitorsList")
    public String visitorsList(Model model){
    model.addAttribute("visitors", visitors);

    return "6_visitorsList";
}

@RequestMapping("/7_newThemePark")
public String newThemePark(){return "7_newThemePark";}

@RequestMapping("/submitNewThemePark")
public String submitNewThemePark(HttpServletRequest request, Model model){
    String parkname = request.getParameter("parkName");
    ThemePark themePark = new ThemePark(parkname);
    themeParks.add(themePark);
    model.addAttribute("themeParks", themeParks);

    return "8_themeParksList";
}
@RequestMapping("/8_themeParksList")
public String themeParksList(Model model){
    model.addAttribute("themeParks", themeParks);

    return "8_themeParksList";
}

@RequestMapping("/10_attractionList")
public String attractionList(HttpServletRequest request, Model model){
    int parkIndex = Integer.parseInt(request.getParameter("parkIndex"));
    ThemePark returnThemePark = themeParks.get(parkIndex);
    model.addAttribute("themePark", returnThemePark);
    return "10_attractionList";
}
@RequestMapping("/9_newAttraction")
public String newAttraction(Model model, Model model2){
    model.addAttribute("themeParks", themeParks);
    model2.addAttribute("staff", staffMembers);
    return "9_newAttraction";
}

@RequestMapping("/submitNewAttraction")
public String submitNewAttraction(HttpServletRequest request, Model model){
    int staffMemberIndex = Integer.parseInt(request.getParameter("staffMember"));
    int themeParkIndex = Integer.parseInt(request.getParameter("themePark"));

    if (themeParkIndex < 0)
    {
        model.addAttribute("errorMessage", "You didn't choose a theme park!");
        return "error";
    }
    else if(staffMemberIndex < 0){
        model.addAttribute("errorMessage", "You didn't choose a staff member!");
        return "error";
    }
    else {
        ThemePark themePark = themeParks.get(themeParkIndex);
        String attractionName = request.getParameter("attractionName");
        int duration = Integer.parseInt(request.getParameter("duration"));
        Staff staff = staffMembers.get(staffMemberIndex);
        String picture = request.getParameter("picture");
        Attraction attraction = new Attraction(attractionName, duration);
        attraction.setPhoto(picture);
        attraction.setResponsible(staff);
        themePark.addAttraction(attraction);
        model.addAttribute("themePark", themePark);
    }


    return "10_attractionList";

}

@RequestMapping("/searchAttraction")
public String searchAttraction(HttpServletRequest request, Model model){
    String attraction = request.getParameter("nameAttraction");
    Attraction attraction1 = null;
    for (int i =0; i< themeParks.size();i++) {
        if (attraction1 == null){
        ThemePark themePark = themeParks.get(i);
        attraction1 = themePark.searchAttractionByName(attraction);
    }
    }
    if(attraction1 == null){
        model.addAttribute("errorMessage", "There is no attraction with the name '"+attraction+"'");
        return "error";
    }
    else{
    model.addAttribute("attraction", attraction1);
    return "11_attraction";
    }
}

    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Visitor> fillVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();
        Visitor visitor1 = new Visitor("Dominik", "Mioens");
        visitor1.setYearOfBirth(2001);
        Visitor visitor2 = new Visitor("Zion", "Noops");
        visitor2.setYearOfBirth(1996);
        Visitor visitor3 = new Visitor("Maria", "Bonetta");
        visitor3.setYearOfBirth(1998);
        Visitor visitor4 = new Visitor("Derre", "Evers");
        visitor4.setYearOfBirth(2002);
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.add(visitor4);
        visitors.get(0).addToWishList("De grote golf");
        visitors.get(0).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Piratenboot");
        visitors.get(1).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Halvar the ride");
        visitors.get(1).addToWishList("DreamCatcher");
        visitors.get(2).addToWishList("DinoSplash");
        visitors.get(3).addToWishList("Sky Scream");
        visitors.get(3).addToWishList("Noimage");
        return visitors;
    }

    private ArrayList<ThemePark> fillThemeParks() {
        ArrayList<ThemePark> themeparks = new ArrayList<>();
        ThemePark themepark1 = new ThemePark("Plopsaland");
        ThemePark themepark2 = new ThemePark("Plopsa Coo");
        ThemePark themepark3 = new ThemePark("Holiday Park");
        Attraction attraction1 = new Attraction("Anubis the Ride", 60);
        Attraction attraction2 = new Attraction("De grote golf", 180);
        Attraction attraction3 = new Attraction("Piratenboot",150);
        Attraction attraction4 = new Attraction("SuperSplash", 258);
        Attraction attraction5 = new Attraction("Dansende fonteinen");
        Attraction attraction6 = new Attraction("Halvar the ride",130);
        Attraction attraction7 = new Attraction("DinoSplash", 240);
        Attraction attraction8 = new Attraction("Bounty Tower", 180);
        Attraction attraction9 = new Attraction("Sky Scream",50);
        attraction1.setPhoto("/img/anubis the ride.jpg");
        attraction2.setPhoto("/img/de grote golf.jpg");
        attraction3.setPhoto("/img/piratenboot.jpg");
        attraction4.setPhoto("/img/supersplash.jpg");
        attraction5.setPhoto("/img/dansende fonteinen.jpg");
        attraction6.setPhoto("/img/halvar the ride.jpg");
        attraction7.setPhoto("/img/dinosplash.jpg");
        attraction8.setPhoto("/img/bountytower.jpg");
        attraction9.setPhoto("/img/sky scream.jpg");
        attraction1.setResponsible(staffMembers.get(0));
        attraction2.setResponsible(staffMembers.get(1));
        attraction3.setResponsible(staffMembers.get(2));
        attraction4.setResponsible(staffMembers.get(3));
        attraction5.setResponsible(staffMembers.get(4));
        attraction6.setResponsible(staffMembers.get(5));
        attraction7.setResponsible(staffMembers.get(6));
        attraction8.setResponsible(staffMembers.get(7));
        attraction9.setResponsible(staffMembers.get(8));
        themepark1.addAttraction(attraction1);
        themepark1.addAttraction(attraction2);
        themepark1.addAttraction(attraction3);
        themepark1.addAttraction(attraction4);
        themepark2.addAttraction(attraction5);
        themepark2.addAttraction(attraction6);
        themepark3.addAttraction(attraction7);
        themepark3.addAttraction(attraction8);
        themepark3.addAttraction(attraction9);
        themeparks.add(themepark1);
        themeparks.add(themepark2);
        themeparks.add(themepark3);
        return themeparks;
    }

}

