package JPRG_CA1_v2;

/*
 * @author Kaung Htet Thu | Kaung Hset Aung
 *         DIT/FT/2B/23   | DIT/FT/2B/23
 *         P2340768       | P2340698
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;

public class StudentManagementController {
    //==========================================
    // Booting up
    //========================================== 
    private ArrayList<Student> studentList;
    private ArrayList<Module> moduleList;
    private StudentManagementModel model = null;
    private StudentManagementView view = null;
    
    
    //==========================================
    // Constructor
    //==========================================
    public StudentManagementController (StudentManagementModel model, StudentManagementView view) {
        this.studentList = new ArrayList<>();
        this.moduleList = new ArrayList<>();
        this.model = model;
        this.view = view;
    }
    
    
    //==========================================
    // Methods
    //========================================== 
    public Integer takeInputForMenu(ArrayList<String> menu, String title) {
        //==========================================
        // Booting up
        //==========================================
        String strInput;
        String options = "";
        Integer input = 0;
        for (String option : menu) {
            options += option + "\n";
        }
        
        //==========================================
        // Getting user input
        //==========================================
        while (true) {
            strInput = view.getMenuInput(options, title);
            
        //==========================================
        // Processing and presentation
        //==========================================
            if (strInput == null) {
                return null;
            }
            else 
                strInput = strInput.trim();
            
            input = model.getIntValue(strInput);
            if (model.isBlank(strInput)) {
                view.displayIsBlank();
                continue;
            }
            if (input == null) {
                view.displayIsNotInteger();
                continue;
            }
            if (!model.isInRange(input, 1, menu.size())) {
                view.displayIsNotInRange(1, menu.size());
                continue;
            }
            break;
        }
        return input;
    }
        
        
    //==========================================================================
    // Generate dummy students
    //==========================================================================
    public void GenerateAssignStudents() {
        //==========================================
        // Booting up and processing
        //==========================================
        GenerateStudents generate = new GenerateStudents();
        ArrayList<Student> generatedList = generate.generateStudentList(1000);
        studentList.addAll(generatedList);
        moduleList.addAll(generate.getModuleList());

        //==========================================
        // Presentation (backend)
        //==========================================
        for (Student s : studentList) {
            System.out.println(s.toString());
        }

    }
    

    //=========================================================================
    // Run
    //==========================================================================
    public void run() {
        //==========================================
        // Booting up
        //==========================================
        ArrayList<String> adminOrUser = view.getRoleRequestMenu();
        boolean quit = false;
        GenerateAssignStudents();

        //==========================================
        // Getting user input
        //==========================================
        while(!quit) {
            Integer input = takeInputForMenu(adminOrUser, "Student Management System");

        //==========================================
        // Processing and presentation
        //==========================================
            switch (input) {
                case 1:
                    user();
                    break;
                case 2:
                    admin();
                    break;
                case null:
                    view.displayProgramEnd();
                    quit = true;
                    break;
                default:
                    view.promptUserForCorrectInput();
            }
        }
    }
    
    
    //==========================================================================
    // Operate User
    //==========================================================================
    private void user(){
        //==========================================
        // Booting up
        //========================================== 
        ArrayList<String> userMenu = view.getUserMenu();
        boolean quitUser = false;
        Integer intOption;

        //==========================================
        // Getting user input
        //========================================== 
        while (!quitUser) {
        intOption = takeInputForMenu(userMenu, "Student Enquiry System");
        
        //==========================================
        // Processing and presentation 
        //========================================== 
            switch (intOption) {
                case null:
                    return;
                case 1 :  
                    displayAllStudent();
                    break;
                case 2 : 
                    searchStudentByClass();
                    break;
                case 3 :
                    searchStudentByName();
                    break;
                case 4 :
                    printDirectorHonor();
                    break;
                case 5 :
                    return;
                case 6 : 
                    quitUser = true;
                    view.displayProgramEnd();
                    System.exit(0);
                    break;
                default:
                    view.promptUserForCorrectInput();
            }
        }

    }

    
    //==========================================================================
    // Operate Admin
    //==========================================================================
    private void admin() {
        //==========================================
        // Booting Up
        //========================================== 
        ArrayList<String> adminMenu = view.getAdminMenu();
        boolean quitAdmin = false;
        Integer input;
        
        //==========================================
        // Getting user input
        //========================================== 
        while(!quitAdmin) {
            input = takeInputForMenu(adminMenu, "Student Admin System"); 
            
        //==========================================
        // Processing and presentation 
        //========================================== 
            switch (input) {
                case null: 
                    return;
                case 1:
                    addNewStudent();
                break;
                case 2:
                    deleteStudent();
                break;
                case 3:
                    addModuleForStudent();
                break;
                case 4:
                    dropModuleForStudent();
                break;
                case 5:
                    return;
                case 6:
                    view.displayProgramEnd();
                    System.exit(0);
                    break;
                default:
                    view.promptUserForCorrectInput();
            }
        }
    }
    
  
    //==========================================================================
    // User Option 1 [DISPLAY ALL STUDENTS]
    //==========================================================================
    public void displayAllStudent() {
        //==========================================
        // Booting up
        //==========================================
        String output;
            
        //==========================================
        // Processing 
        //==========================================
        if (studentList.size() > 3) {
            output = model.showLastThreeStudents(studentList);   
        } else {
            output = model.showAllStudent(studentList); 
        }
        
        //==========================================
        // Presentation
        //==========================================
        view.displayAllStudents(output);        
    }
  
    
    //==========================================================================
    // User Option 2 [SEARCH STUDENT BY CLASS]
    //==========================================================================
    public void searchStudentByClass() {
        while (true) {
            //==========================================
            // Booting up 
            //==========================================
            String classToSearch;
            String validation;
            
            //==========================================
            // Getting user input
            //==========================================
            classToSearch = view.getInputClassNameToSearchStudentByClass();
            
            //==========================================
            // Processing and presentation
            //==========================================
            if(classToSearch == null) 
                return;
            else 
                classToSearch = classToSearch.trim();
            
            if (model.isBlank(classToSearch)) {
                view.displayIsBlank();
            } else {
                validation = model.validateInputClassFormat (classToSearch);
                if (validation.isEmpty()) {
                    displaySearchStudentByClass(model.formatTheClass(classToSearch), studentList);
                    break;
                } else {
                    view.displayInvalidClassFormat (validation);
                }
            } 
        }
    }
  
    public void displaySearchStudentByClass(String classToSearch, ArrayList<Student> studentList) {
        //==========================================
        // Booting up
        //==========================================
        String output;
        int counter;
        double sumOfAllGPA;
        double avgGPA;
            
        //==========================================
        // Processing and presentation
        //==========================================
        counter = model.getCountOfStudentsInClass(studentList, classToSearch);
        sumOfAllGPA = model.getSumOfAllGPAInClass(studentList, classToSearch);
        avgGPA = model.calculateGpa(sumOfAllGPA, counter);
            
        if (counter > 0) {
            output = model.showOutputForClassToSearch (classToSearch, counter, avgGPA);
            view.displaySearchByClass(output);
        } else if (counter == 0) {
            view.displayNoStudentFoundFromClass();
        }
    }
 
  
    //==========================================================================
    // User Option 3 [SEARCH STUDENT BY NAME]
    //==========================================================================
    public void searchStudentByName () {
        //==========================================
        // Booting up
        //==========================================
        String nameToSearch;
        
        //==========================================
        // Getting user input
        //==========================================
        while (true) {
            nameToSearch = view.getInputStudentNameToSearchStudentByName();
        //==========================================
        // Processing and presentation
        //==========================================
        
            if(nameToSearch == null) 
                return;
            else 
                nameToSearch = nameToSearch.trim();
            
            if (model.isBlank(nameToSearch)) {
                view.displayIsBlank();
                continue;
            }

            if (!(model.validateName(nameToSearch))) {
                view.displayInvalidName();
                continue;
            }  
            
            break;
        }
        displaySearchStudentByName(nameToSearch);
    }
    
    public void displaySearchStudentByName(String nameToSearch) {
        //==========================================
        // Booting up
        //==========================================
        String output;
        ArrayList<Student> namedStudents;
     
        //==========================================
        // Processing and presentation
        //==========================================
        namedStudents = model.getnamedStudents(studentList, nameToSearch);
        if (namedStudents.size() == 1) {
            output = model.showOneNamedStudent (namedStudents);
            view.displaySearchbyName(output);
            
        } else if (namedStudents.size() > 1 && namedStudents.size() <= 2) {
            output = model.showAllNamedStudents (namedStudents, nameToSearch);
            view.displaySearchbyName(output);
              
        } else if (namedStudents.size() > 2) {
            output = model.showFirstTwoStudents(namedStudents, nameToSearch);
            view.displaySearchbyName(output);
              
        } else {
            view.displayNoStudentFoundWithName(model.nameFormatter(nameToSearch));              
        }
        
        //==========================================
        // Cleaning up
        //==========================================
        namedStudents.clear();
    }
  
  
    //==========================================================================
    // User Option 4 [PRINT DIRECTOR HONOR ROLL]
    //==========================================================================
    public void printDirectorHonor () {
        //==========================================
        // Booting up 
        //==========================================
        String diplomaToSearch;
        
        //==========================================
        // Getting user input
        //==========================================
        while (true) {
            diplomaToSearch = view.getInputDiplomaToSearchDirectorHonorRoll();
         
        //==========================================
        // Processing and presentation
        //==========================================
            if(diplomaToSearch == null) 
                return;
            else 
                diplomaToSearch = diplomaToSearch.trim();
            
            if (model.isBlank(diplomaToSearch)) {
              view.displayIsBlank();
            } else {
                if (model.validateInputDiplomaFormat (diplomaToSearch)) {
                    displayTop10Percent(studentList, diplomaToSearch);
                    break;
                } else {
                  view.displayInvalidDiplomaName();
                }

            } 
        }
    }   
    
    public void displayTop10Percent (ArrayList<Student> allStudents, String diploma) {
        //==========================================
        // Booting up
        //==========================================
        String output;
        ArrayList<Student> diplomaStudent; 
        int count10Percent;
        double tenPercentGpa;
        ArrayList<Double> allGpa;
        ArrayList <Student> diplomaStudentDHR;
        double highestGpa;
        
        //==========================================
        // Processing
        //==========================================
        diplomaStudent = model.filterDiploma (allStudents, diploma);
        
        if (!diplomaStudent.isEmpty()) {
            
            count10Percent = (int) Math.ceil(diplomaStudent.size()*0.1);
            allGpa = model.getAllGPAs(diplomaStudent);
            allGpa.sort(Collections.reverseOrder());
            highestGpa = allGpa.get(0);
            tenPercentGpa = allGpa.get(count10Percent -1);
            
            diplomaStudentDHR = model.getDHR(diplomaStudent,tenPercentGpa);
            int dipStudent = diplomaStudent.size();
            int dhrStudent = diplomaStudentDHR.size();
          
            if (dhrStudent > 5) {
                output = model.showFirstFiveDHRStudents (diploma, diplomaStudentDHR, dipStudent, dhrStudent, highestGpa);      
            } else {
                output = model.showAllDHRStudents (diploma, diplomaStudentDHR, dipStudent, dhrStudent, highestGpa);
            }
            
        //==========================================
        // Presentation 
        //==========================================     
            view.displayDirectorHonorRoll(output);
          
        //==========================================
        // Cleaning up 
        //==========================================   
            diplomaStudent.clear();
            diplomaStudentDHR.clear();
            allGpa.clear();
        } else {
            view.displayDiplomaNotFound();
        }

    }

    
    //==========================================================================
    // Admin Option 1 [ADD NEW STUDENT]
    //==========================================================================
    public void addNewStudent() {
        //==========================================
        // Booting up
        //==========================================
        String name = "";
        String adminNo = "";
        String stdClass = "";
        String strModuleCount = "";
        Integer moduleCount = 0;
        String validation = "";
        Module newModule = new Module();

        //==========================================
        // Getting user input and processing 
        //==========================================
        while(true) {
            name = view.takeInput("name");
            if(name == null)
                return;
            else
                name = name.trim();
            
            if (model.isBlank(name)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validateName(name)){
                view.displayErrorInvalidName();
                continue;
            }
            break;
        }

        while(true) {
            adminNo = view.takeInput("Admin");
            if(adminNo == null)
                return;
            else
                adminNo = adminNo.toLowerCase().trim();
            if(!model.validateAdminNo(adminNo)) {
                view.displayErrorInvalidAdminNo();
                continue;
            }
            if(model.searchStudentByAdminNumber(adminNo, studentList)) {
                view.displayErrorAdminNoConflict();
                continue;
            }
            break;
        }

        while(true) {
            stdClass = view.takeInput("Class");
            if(stdClass == null)
                return;
            else
                stdClass = stdClass.toUpperCase().trim();
            
            if(stdClass == null) 
                return;
            else 
                stdClass = model.formatTheClass(stdClass.trim());
            
            validation = model.validateInputClassFormat (stdClass);
                if (!validation.isEmpty()) {
                    view.displayInvalidClassFormat (validation);
                    continue;
                }
            break;
        }

        studentList.add(new Student(stdClass, adminNo, model.nameFormatter(name)));

        while(true) {
            strModuleCount = view.takeInput("number of Modules Taken");
            if(strModuleCount == null)
                return;
            else
                strModuleCount = strModuleCount.trim();
            
            moduleCount = model.getIntValue(strModuleCount);

            if(model.isBlank(strModuleCount)){
                view.displayIsBlank();
                continue;
            }
            if(moduleCount == null){
                view.displayIsNotInteger();
                continue;
            }
            if(!model.isInRange(moduleCount, 1, 10))
                if(view.displayAreYouSure(moduleCount) != JOptionPane.YES_OPTION)
                    continue;
            break;
        }
        
        if(moduleCount > 1) {
            for(int i = 1; i <= moduleCount; i++) {
                newModule = getNewModule(adminNo, i);
                if(newModule != null)
                studentList.getLast().addModule(newModule);  
            }
        } else {
            newModule = getNewModule(adminNo);
            if(newModule != null)
                studentList.getLast().addModule(newModule);
        }
//      studentList.getLast().modelCalculateGpa();
        //==========================================
        // Presentation
        //==========================================
        view.displayNewStudentAdded();
    }
    
    public Module getNewModule(String adminNo, int i) {
        //==========================================
        // Booting up
        //==========================================
        String module_code = "";
        String module_name = "";
        String strModule_credit = "";
        String strModule_mark = "";
        Integer module_credit = -1;
        Integer module_mark = -1;

        
        //==========================================
        // Getting user input and processing 
        //==========================================
        while(true) {
            module_code = view.takeModuleInput("Module code", i);
            if(module_code == null) 
                return null;
            else
                module_code = module_code.toUpperCase().trim();
            
            if(model.isBlank(module_code)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validModuleCode(module_code)) {
                view.displayErrorInvalidModuleCode();
                continue;
            }
            if(model.moduleAlreadyTaken(adminNo, module_code, studentList)){
                view.displayModuleTakenAldy();
                continue;
            }
            if(model.moduleExistInModuleList(module_code, moduleList)){
                view.displayModuleExistInList();
                while(true) {
                    strModule_mark = view.takeModuleInput("Module Marks", i);
                    module_mark = model.getIntValue(strModule_mark);
                    if(strModule_mark == null)
                        return null;
                    else
                        strModule_mark = strModule_mark.trim();
                    if(model.isBlank(strModule_mark)) {
                        view.displayIsBlank();
                        continue;
                    }
                    if(module_mark == null) {
                        view.displayIsNotInteger();
                        continue;
                    }
                    if(!model.isInRange(module_mark, 0, 100)) {
                        view.displayIsNotInRange(0, 100);
                        continue;
                    }
                    break;
                }
            
                return model.getModuleInList(module_code, moduleList, module_mark);
            }
          
            break;
        }

        while(true) {
            module_name = view.takeModuleInput("Module name", i);
            if(module_name == null)
                return null;
            else
                module_name = module_name.toUpperCase().trim();
            
            if(model.isBlank(module_name)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validModuleName(module_name)) {
                view.displayErrorInvaildModuleName();
                continue;
            }
            if(model.moduleNameExist(module_name, moduleList)){
                view.displayModuleExistAldy();
                continue;
            }
            break;
        }

        while(true) {
            strModule_credit = view.takeModuleInput("Credit Unit", i);
            if(strModule_credit == null)
                return null;
            else
                strModule_credit = strModule_credit.trim();
            
            module_credit = model.getIntValue(strModule_credit);
            if(model.isBlank(strModule_credit)) {
                view.displayIsBlank();
                continue;
            }
            if(module_credit == null) {
                view.displayIsNotInteger();
                continue;
            }
            if(!model.isInRange(module_credit, 1, 16)) {
                view.displayIsNotInRange(1, 16);
                continue;
            }
            break;
        }
        
        while(true) {
            strModule_mark = view.takeModuleInput("Module Marks", i);
            if(strModule_mark == null)
                return null;
            else
                strModule_mark = strModule_mark.trim(); 
            
            module_mark = model.getIntValue(strModule_mark);
            if(model.isBlank(strModule_mark)) {
                view.displayIsBlank();
                continue;
            }
            if(module_mark == null) {
                view.displayIsNotInteger();
                continue;
            }
            if(!model.isInRange(module_mark, 0, 100)) {
                view.displayIsNotInRange(0, 100);
                continue;
            }
            break;
        }
        moduleList.add(new Module(module_code, module_name, module_credit, 0));
        return new Module(module_code, module_name, module_credit, module_mark);
    }
    
    public Module getNewModule(String adminNo) {
        //==========================================
        // Booting up
        //==========================================
        String module_code = "";
        String module_name = "";
        String strModule_credit = "";
        String strModule_mark = "";
        Integer module_credit = -1;
        Integer module_mark = -1;

        //==========================================
        // Getting user input and processing
        //==========================================
        while(true) {
            module_code = view.takeModuleInput("Module code");
            if(module_code == null)
                return null;
            else
                module_code = module_code.toUpperCase().trim();
            
            if(model.isBlank(module_code)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validModuleCode(module_code)) {
                view.displayErrorInvalidModuleCode();
                continue;
            }
            if(model.moduleAlreadyTaken(adminNo, module_code, studentList)){
                view.displayModuleTakenAldy();
                continue;
            }
            if(model.moduleExistInModuleList(module_code, moduleList)){
              view.displayModuleExistInList();
              // get mark straight away and return
              while(true) {
                strModule_mark = view.takeModuleInput("Module Marks");
                if(strModule_mark == null)
                    return null;
                else
                    strModule_mark = strModule_mark.trim(); 
                
                module_mark = model.getIntValue(strModule_mark);
                if(model.isBlank(strModule_mark)) {
                    view.displayIsBlank();
                    continue;
                }
                if(module_mark == null) {
                    view.displayIsNotInteger();
                    continue;
                }
                if(!model.isInRange(module_mark, 0, 100)) {
                    view.displayIsNotInRange(0, 100);
                    continue;
                }
                break;
              }
            
            return model.getModuleInList(module_code, moduleList, module_mark);
          }
            break;
        }

        while(true) {
            module_name = view.takeModuleInput("Module name");
            if(module_name == null)
                return null;
            else
                module_name = module_name.toUpperCase().trim();
                        
            if(model.isBlank(module_name)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validModuleName(module_name)) {
                view.displayErrorInvaildModuleName();
                continue;
            }
            if(model.moduleNameExist(module_name, moduleList)){
                view.displayModuleExistAldy();
                continue;
            }
            break;
        }

        while(true) {
            strModule_credit = view.takeModuleInput("Credit Unit");
            if(strModule_credit == null)
                return null;
            else
                strModule_credit = strModule_credit.trim(); 
                        
            module_credit = model.getIntValue(strModule_credit);
            if(model.isBlank(strModule_credit)) {
                view.displayIsBlank();
                continue;
            }
            if(module_credit == null) {
                view.displayIsNotInteger();
                continue;
            }
            if(!model.isInRange(module_credit, 1, 16)) {
                view.displayIsNotInRange(1, 16);
                continue;
            }
            break;
        }
        
        while(true) {
            strModule_mark = view.takeModuleInput("Module Marks");
            if(strModule_mark == null)
                return null;
            else
                strModule_mark = strModule_mark.trim();             
            module_mark = model.getIntValue(strModule_mark);
            if(model.isBlank(strModule_mark)) {
                view.displayIsBlank();
                continue;
            }
            if(module_mark == null) {
                view.displayIsNotInteger();
                continue;
            }
            if(!model.isInRange(module_mark, 0, 100)) {
                view.displayIsNotInRange(0, 100);
                continue;
            }
            break;
        }
        moduleList.add(new Module(module_code, module_name, module_credit, 0));
        return new Module(module_code, module_name, module_credit, module_mark);
    }
    
    //==========================================================================
    // Admin Option 2 [DELETE STUDENT]
    //==========================================================================
    public void deleteStudent() {
        //==========================================
        // Booting up
        //==========================================
        String strAdminNo = "";
        boolean found = false;
        
        //==========================================
        // Getting user input 
        //==========================================
        while(true) {
            strAdminNo = view.getAdminNo();
            if(strAdminNo == null)
              return;
            else
                strAdminNo = strAdminNo.toLowerCase().trim();
            
            if(model.isBlank(strAdminNo)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validateAdminNo(strAdminNo)) {
                view.displayErrorInvalidAdminNo();
                continue;
            }
          break;
        }
        
        //==========================================
        // Processing
        //==========================================
        for (Student stu : studentList) {
            if(stu.getAdmin_number().equals(strAdminNo)) {
            found = true;
          }
        }

        final String finalValue = strAdminNo.toLowerCase();
        studentList.removeIf(stu -> stu.getAdmin_number().equals(finalValue));
        
        //==========================================
        // Presentation
        //==========================================
        if(!found)
            view.displayStudentNotFound();
        else
            view.displayDeleteOK();
    }
    
    
    //==========================================================================
    // Admin Option 3 [ADD NEW MODULE FOR STUDENT]
    //==========================================================================
    public void addModuleForStudent() {   
        //==========================================
        // Booting up
        //==========================================
        String adminNo = "";
        Module newModule = new Module();
        
        //==========================================
        // Getting user input
        //==========================================
        while(true) {
            adminNo = view.takeInput("Admin");
            if(adminNo == null)
                return;
            else 
                adminNo = adminNo.toLowerCase().trim();
            
            if(model.isBlank(adminNo)) {
                view.displayIsBlank();
                continue;
            }
            if(!model.validateAdminNo(adminNo)) {
              view.displayErrorInvalidAdminNo();
              continue;
            }
            break;
        }
        
        //==========================================
        // Processing and presentation
        //==========================================
        if(model.searchStudentByAdminNumber(adminNo, studentList)) {
            adminNo = adminNo.toLowerCase();
            while(true) {
                for(Student stu : studentList) {
                    if(stu.getAdmin_number().equals(adminNo)){
                        newModule = getNewModule(adminNo);
                        if(newModule != null) {
                            stu.addModule(newModule);
                        }
                    }
                }       
                if(!model.wantToAddMore())
                    break;
            }
        } else {
            view.displayStudentNotFound();
        }
    }
    
    
    //==========================================================================
    // Admin Option 4 [DROP MODULE FOR STUDENT]
    //==========================================================================
    public void dropModuleForStudent() {   
        //==========================================
        // Booting up
        //==========================================
        String adminNo = "";
        ArrayList<String> output = new ArrayList<>();
        view.addMessageToModuleList(output);
        
        int index = 1;
        Integer input = 0;
        //==========================================
        // Getting user input
        //==========================================
        while(true) {
            adminNo = view.takeInput("Admin");
            if(adminNo == null)
                return;
            else 
                adminNo = adminNo.toLowerCase().trim();
            
            if(!model.validateAdminNo(adminNo)) {
              view.displayErrorInvalidAdminNo();
              continue;
            }
            break;
        }
        
        //==========================================
        // Processing and presentation
        //==========================================
        if(model.searchStudentByAdminNumber(adminNo, studentList)) {
            for(Student stu : studentList) {
                if(stu.getAdmin_number().equals(adminNo)){
                    for(Module m : stu.getModules_taken()) {
                        output.add(index + ". " + m.toString());
                        index++;
                    }
                    input = takeInputForMenu(output, "Drop Module");
                    if(input == null)
                          return;
                    else {
                        stu.dropModule(input-1);
                        view.displayModuleDropSuccess();
                    }
                    
                }
            }
        } else {
            view.displayStudentNotFound();
        }
    }
    public Student merge(Student a, Student b) {
        ArrayList<Module> combinedModules = new ArrayList<>();
        ArrayList<Module> last = new ArrayList<>();
        for(Module m: a.getModules_taken())
            combinedModules.add(m);
        for(Module m: b.getModules_taken())
            
            combinedModules.add(m);
        for(int i = 0; i < combinedModules.size(); i++) {
            for(int j = 1; j < combinedModules.size(); j++) {
                    if(combinedModules.get(i).equals(combinedModules.get(j))){
                        last.add(combinedModules.get(i));
                        combinedModules.remove(i);
                        
                        
                    }

            }
        }
        Student combinedStudent = new Student(a.getStdClass(), a.getName(), a.getAdmin_number(), combinedModules);
       
        return combinedStudent;
    }
    
}
