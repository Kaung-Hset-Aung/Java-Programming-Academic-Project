package JPRG_CA1_v2;

/*
 * @author Kaung Hset Aung
 *         DIT/FT/2B/23
 *         P2340698
 */

import java.util.ArrayList;

public class Student {
    //==============================================================
    // Attributes / Properties
    //==============================================================
    private String stdClass;
    private String admin_number;
    private String name;
    private double gpa;
    private ArrayList<Module> modules_taken;
    

    //==============================================================
    // Constructors
    //==============================================================
    public Student() {
        stdClass = "";
        admin_number = "";
        name = "";
        gpa = 0.0;
        this.modules_taken = new ArrayList<>();
    }
    
    public Student (String stdClass, String admin_number, String name, ArrayList<Module> module_list) {
        this.stdClass = stdClass;
        this.admin_number = admin_number;
        this.name = name;
        this.modules_taken = module_list;
        this.gpa = modelCalculateGpa();
    }

    public Student (String stdClass, String admin_number, String name) {
        this.stdClass = stdClass;
        this.admin_number = admin_number;
        this.name = name;
        this.modules_taken = new ArrayList<>();
    }
    
    
    //==============================================================
    // Getters / Setters
    //==============================================================
    public String getStdClass() {
        return stdClass;
    }
    public String getAdmin_number() {
        return admin_number;
    }
    public String getName() {
        return name;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public ArrayList<Module> getModules_taken() {
        return modules_taken;
    }
    

    //==============================================================
    // Methods
    //==============================================================
    public void addModule(Module module) {
        this.modules_taken.add(module);
        modelCalculateGpa();
    }
    
    public void dropModule (int index) {
         this.modules_taken.remove(index);
         modelCalculateGpa();
    }
    
    public double modelCalculateGpa () {
        int numerator = 0;
        int denominator = 0;
        if (!this.modules_taken.isEmpty()){
            for (int i = 0 ; i < modules_taken.size() ; i++) {
                char grade = modules_taken.get(i).getGrade();
                int gradePoint = modelConvertToGradePoint(grade);
                int creditPoint = modules_taken.get(i).getModuleCredit();

                numerator += (gradePoint*creditPoint);
                denominator += creditPoint;
            }
            this.gpa = (double)numerator/denominator;
            double gpa2 = (double)numerator/denominator;
            String gpa3 = String.format("%.2f",gpa2);
            return Double.parseDouble(gpa3);
        } else 
            return 0.0;
    }
    
    public int modelConvertToGradePoint (char grade) {
        switch (grade) {
            case 'A' : return 4;
            case 'B' : return 3;
            case 'C' : return 2;
            case 'D' : return 1;
            default : return 0;
        }
    }
    
    public String viewInfoAndListTheTakenModulesWithGrade () {
        String finalOutput="";
        finalOutput += "Name: " + this.name + "\n";
        finalOutput += "Admin: " + this.admin_number + "\n";
        finalOutput += "Class: " + this.stdClass + "\n";
        finalOutput += "Modules Taken:\n";
        
        for (int i = 1 ; i < modules_taken.size()+1 ; i++){
            finalOutput += (i + ". " + modules_taken.get(i-1).toString() + "\n");
        }
        return finalOutput;
    } 
    
    @Override
    public String toString() {
        return String.format("%s \n%s (%s) \nNumber of modules taken : %d\nGPA: %.2f \n", this.admin_number,this.name, this.stdClass, this.modules_taken.size(), this.modelCalculateGpa());
    }
    
    private int totalCredits () {
        int totalCredits = 0;
        for (Module m :this.modules_taken) {
            totalCredits += m.getModuleCredit();
        }
        
        return totalCredits;
    }
}
