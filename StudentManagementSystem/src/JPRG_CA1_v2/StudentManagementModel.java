package JPRG_CA1_v2;

/*
 * @author Kaung Htet Thu | Kaung Hset Aung
 *         DIT/FT/2B/23   | DIT/FT/2B/23
 *         P2340768       | P2340698
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class StudentManagementModel {
    //==========================================================================
    // Common models
    //==========================================================================
    public boolean isBlank(String strInput) {
        return strInput.isBlank();
    }

    public boolean isInRange(Integer input, int a, int b) {
        return input >= a && input <= b;
    }
    
    public Integer getIntValue(String input) {
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    public  String nameFormatter(String name) {
        if(name.length() == 0)
            return "";
        String formattedName = "";
        String[] words = name.split("\\s+");
        for(String word : words) {
            formattedName += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            formattedName += " ";
        }
        
        return formattedName.trim();
    }
    
    
    //==========================================================================
    // User Models
    //==========================================================================
    
    //======================================
    // User Option 1 [DISPLAY ALL STUDENTS]
    //======================================
    public String showLastThreeStudents (ArrayList<Student> studentList) {
        String output = "";
        output += "Displaying last 3 enrolled students \nout of " + studentList.size() + " total enrolled students\n";
        int lastIndex = studentList.size()-1;
        
        for (int counter = 1 ; counter <= 3 ; counter ++, lastIndex --) {
            output += "\nStudent " + (counter) + ":\n"; // Adjusted student number
            output += studentList.get(lastIndex).viewInfoAndListTheTakenModulesWithGrade();
            output += MyUtilities.viewUnderline();
        }

        return output;
    }
    
    public String showAllStudent(ArrayList<Student> studentList) {
        String output = "";
        for (int i = 1; i <= studentList.size(); i++) {
                output += "\nStudent " + i + ":\n";
                output += studentList.get(i - 1).viewInfoAndListTheTakenModulesWithGrade();
                output += MyUtilities.viewUnderline();
            }
        
        return output;
    }
    
    //========================================
    // User Option 2 [SEARCH STUDENT BY CLASS]
    //========================================
    public String validateInputClassFormat(String classToSearch) {
        String errorMessage = "";
        String[] partsOfTheInput = classToSearch.split("/");
        if (partsOfTheInput.length != 4) {
            errorMessage = "Please provide \n- diplomaName\n- studentType\n- classType and \n- classNumber\n\neg. DIT/FT/2B/23";
            return errorMessage;
            
        } else if (partsOfTheInput.length == 4) {
            String diploma = partsOfTheInput[0].trim();
            String diplomaType = partsOfTheInput[1].trim();
            String classType = partsOfTheInput[2].trim();
            Integer classNumber = getIntValue(partsOfTheInput[3].trim());
            
            if (!isAlphabet(diploma) || diploma.length() < 3 || diploma.length() > 5) {
                errorMessage += "Diploma name must be an alphabetic string and 3 to 5 characters long. eg. DIT/xx/xx/xx\n";
            }
            
            if (!diplomaType.toUpperCase().equals("FT") && !diplomaType.toUpperCase().equals("PT")) {
                errorMessage += "Student type must be either FT or PT only!. eg. xxx/FT/xx/xx\n";
            }
            
            if (classType.length() != 2 || !Character.isDigit(classType.charAt(0)) || !Character.isAlphabetic(classType.charAt(1))) {
                errorMessage += "Class type must be one numeric and one alphabet. eg. xxx/xx/2B/xx\n";
            }
            
            if (classNumber == null || classNumber <= 0) {
                errorMessage += "Class number must be a number greater than 0. eg. xxxx/xx/xx/23\n";
            
            } else if (classNumber >= 100) {
                int confirmation = StudentManagementView.displayConfirmClassNumber("Entered class number is too big, are you sure?", "Confirmation");
                if (confirmation == JOptionPane.YES_OPTION) {
                    errorMessage = "";
                } else {
                    errorMessage = " ";
                }
            }
        }
        
        return errorMessage;
    }
    
    public boolean isAlphabet(String input) {
        for (char ch : input.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                return false;
            }
        }
        
        return true;
    }
    
    public String formatTheClass(String classToSearch) {
        String[] partsOfTheInput =  classToSearch.split("/");  
        String diploma = partsOfTheInput[0].trim();
        String diplomaType = partsOfTheInput [1].trim();
        String classType = partsOfTheInput[2].trim();
        Integer classNumber = getIntValue(partsOfTheInput[3].trim());
        String strClassNumber = String.format("%02d",classNumber);
        
        return String.format("%s/%s/%s/%s", diploma.toUpperCase(), diplomaType.toUpperCase(), classType.toUpperCase(), strClassNumber);
    }
    
    public int getCountOfStudentsInClass (ArrayList<Student> studentList, String classToSearch) {
        int counter = 0;
        for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getStdClass().toUpperCase().equals(classToSearch.toUpperCase())) {
                    counter++;
                    
                }
            }
        
        return counter;
    }
    
    public double getSumOfAllGPAInClass (ArrayList<Student> studentList, String classToSearch) {
        double sumOfAllGPA = 0.0;
        for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getStdClass().toUpperCase().equals(classToSearch.toUpperCase())) {
                   
                    sumOfAllGPA += studentList.get(i).modelCalculateGpa();
                }
            }
        
        return sumOfAllGPA;
    }
    
    public double calculateGpa (double numerator, double denominator) {
        return (double) (numerator/ denominator);
    }
    
    public String showOutputForClassToSearch (String classToSearch, int counter, double avgGPA) {
        String output = "";
        output += "Number of student(s) in " + classToSearch.toUpperCase() + ": ";
        output += counter + "\n";
        output += "Average GPA: " + String.format("%.2f ", avgGPA);
        
        return output;
    }
    
    //=======================================
    // User Option 3 [SEARCH STUDENT BY NAME]
    //=======================================
    public ArrayList<Student> getnamedStudents (ArrayList<Student> studentList, String nameToSearch) {
        ArrayList<Student> namedStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().equals(nameToSearch.toLowerCase())) {
                namedStudents.add(student);
            }
        }
        
        return namedStudents;
    }
    
    public String showOneNamedStudent (ArrayList<Student> namedStudents) {
        String output = "";
        output += namedStudents.get(0).viewInfoAndListTheTakenModulesWithGrade();
        output += "\nGPA: " + String.format("%.2f", namedStudents.get(0).modelCalculateGpa()) + "\n";
        output += MyUtilities.viewUnderline();
        
        return output;
    }
    
    public String showAllNamedStudents (ArrayList<Student> namedStudents, String nameToSearch) {
        String output = "";
        output += String.format("%d students named \"%s\" are found\n", namedStudents.size(), nameFormatter(nameToSearch));
            for (int i = 1; i <= namedStudents.size(); i++) {
                output += "\nStudent " + i + ":\n";
                output += namedStudents.get(i - 1).viewInfoAndListTheTakenModulesWithGrade();
                output += "\nGPA: " + String.format("%.2f", namedStudents.get(i-1).modelCalculateGpa()) + "\n";
                output += MyUtilities.viewUnderline();
            }
            
        return output;
    }
    
    public String showFirstTwoStudents (ArrayList<Student> namedStudents, String nameToSearch) {
        String output = "";
        output += "Showing first 2 results out of " + namedStudents.size() + " students \nwho named as " + nameFormatter(nameToSearch) + "\n";
            for (int i = 1; i <= 2; i++) {
                output += "\nStudent " + i + ":\n";
                output += namedStudents.get(i - 1).viewInfoAndListTheTakenModulesWithGrade();
                output += "\nGPA: " + String.format("%.2f", namedStudents.get(i-1).modelCalculateGpa()) + "\n";
                output += MyUtilities.viewUnderline();
            }
            
        return output;
    }
    
    //==========================================
    // User Option 4 [PRINT DIRECTOR HONOR ROLL]
    //==========================================
    public boolean validateInputDiplomaFormat(String DiplomaToSearch) {
        return isAlphabet(DiplomaToSearch) && DiplomaToSearch.length() >= 3 && DiplomaToSearch.length() <= 5;
    }
       
    public ArrayList<Student> filterDiploma(ArrayList<Student> allStudents, String diploma) {
        ArrayList<Student> filteredStudents = new ArrayList<>();
        for (Student student : allStudents) {
            String storedStdClass = student.getStdClass();
            String[] partsOfTheClass = storedStdClass.split("/");
            String storedDiploma = partsOfTheClass[0];
            if (storedDiploma.toUpperCase().equals(diploma.toUpperCase())) {
                filteredStudents.add(student);
            }
        }
        
        return filteredStudents;
    }
    
    public ArrayList<Double> getAllGPAs (ArrayList<Student> diplomaStudent) {
        ArrayList<Double> allGpa = new ArrayList<>();
        for (Student student : diplomaStudent) {
            allGpa.add(student.modelCalculateGpa());
        }
        
        return allGpa;
    }
    
    public ArrayList<Student> getDHR (ArrayList <Student> diplomaStudent, double tenPercentGpa) {
        ArrayList <Student> diplomaStudentDHR = new ArrayList<>();
        for (Student student : diplomaStudent) {
            if (student.modelCalculateGpa() >= tenPercentGpa) {
                diplomaStudentDHR.add(student);
            }
        }
        return diplomaStudentDHR;
    }
    
    public String showFirstFiveDHRStudents (String diploma, ArrayList <Student> diplomaStudentDHR, int dipStudent, int dhrStudent, double highestGpa) {
        String output = "" ;
        output += String.format("%s Director Honor Roll (DHR)\n%s\n", diploma.toUpperCase(),MyUtilities.viewUnderline());
        output += String.format("Total students enrolled under %s: %d\n",diploma.toUpperCase(), dipStudent);
        output += String.format("Total students on %S - DHR: %d\n", diploma.toUpperCase(),dhrStudent);
        output += String.format("Highest GPA: %.2f\n\n", highestGpa);
        output += String.format("(Displaying first 5 students on %s - DHR)\n\n", diploma.toUpperCase());
        for (int i = 0 ; i < 5 ; i ++) {
            output += diplomaStudentDHR.get(i).toString() + "\n";
        }
        
        return output;
    }
    
    public String showAllDHRStudents (String diploma, ArrayList <Student> diplomaStudentDHR, int dipStudent, int dhrStudent, double highestGpa) {
        String output = "";
        output += String.format("%s Director Honor Roll (DHR)\n%s\n", diploma.toUpperCase(),MyUtilities.viewUnderline());
        output += String.format("Total students enrolled under %s: %d\n",diploma.toUpperCase(), dipStudent);
        output += String.format("Total students on %S - DHR: %d\n", diploma.toUpperCase(),dhrStudent);
        output += String.format("Highest GPA: %.2f\n\n", highestGpa);
            
        for (Student student : diplomaStudentDHR) {
            output+= student.toString() + "\n";
        }
        
        return output;
    }
    
    
    //==========================================================================
    // Admin Models
    //==========================================================================
    public boolean searchStudentByAdminNumber(String p, ArrayList<Student> studentList) {
        boolean have = false;
        for (Student stu : studentList) {
            if (stu.getAdmin_number().equals(p)) {
                have = true;
            }
        }
        return have;
    }

    public boolean moduleNameExist(String name, ArrayList<Module>  moduleList) {
        for (Module m : moduleList) {
            if (m.getModuleName().equals(name)) {
                return true;
            }
        }
        
        return false;
    }

    public boolean moduleAlreadyTaken(String adminNo, String code, ArrayList<Student>  studentList) {
        for (Student stu : studentList) {
            if (stu.getModules_taken() == null) {
                return false;
            }
            if (stu.getAdmin_number().equals(adminNo)) {
                for (Module i : stu.getModules_taken()) {
                    if (i.getModuleCode().equals(code)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean moduleExistInModuleList(String code, ArrayList<Module>  moduleList) {
        for (Module m : moduleList) {
            if (m.getModuleCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
    
    public Module getModuleInList(String code, ArrayList<Module> moduleList, Integer module_mark) {
        for (Module m : moduleList) {
            if (m.getModuleCode().equals(code)) {
                m.setMark(module_mark);
                return m;
            }
        }
        return null;
    }

    public boolean validateAdminNo(String adminNo) {
        String regexAdmin = "^p\\d{7}$";
        return Pattern.matches(regexAdmin, adminNo);
    }

    public boolean validateName(String name) {
        String regexName = "^([A-Za-z]+)(\\s[A-Za-z]+)*$";
        return Pattern.matches(regexName, name);
    }

    public boolean validModuleCode(String code) {
        String regexModuleCode = "^[A-Za-z]{2}\\d{4}$";
        return Pattern.matches(regexModuleCode, code);
    }

    public boolean validModuleName(String name) {
        String regexModuleName = "^[A-Za-z]{2,5}\\d*$";
        return Pattern.matches(regexModuleName, name);
    }

    boolean wantToAddMore() {
        int response = JOptionPane.showConfirmDialog(null, "New module has been added successfully.\nDo you want to add more?", "Confirm", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }
    
}
