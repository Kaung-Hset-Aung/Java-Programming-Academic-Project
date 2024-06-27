package JPRG_CA1_v2;

/*
 * @author Kaung Htet Thu | Kaung Hset Aung
 *         DIT/FT/2B/23   | DIT/FT/2B/23
 *         P2340768       | P2340698
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class StudentManagementView {
        //======================================================================
        // Constants
        //======================================================================
        private final String TITLE = "Student Admin System";
        private final String TITLE2 = "All student Report";
        private final String TITLE3 = "Class Summary";
        private final String TITLE4 = "Student Info";
        private final String TITLE5 = "Director's Honor Roll (Top 10%)";
        private final String TITLE6 = "Search";
        private final String TITLE7 = "Info";
        
        
        //======================================================================
        // Menu views
        //======================================================================
        public  ArrayList<String> getRoleRequestMenu () {
            ArrayList<String> roleMenu = new ArrayList<>();
            roleMenu.add("1. User");
            roleMenu.add("2. Admin");
            return roleMenu;
        }
        public ArrayList<String> getUserMenu () {
            ArrayList<String> userMenu = new ArrayList<>();
            userMenu.add("1. Display all student");
            userMenu.add("2. Search student by class");
            userMenu.add("3. Search student by name");
            userMenu.add("4. Print director's honor roll");
            userMenu.add("5. Go back to main menu");
            userMenu.add("6. Quit");

            return userMenu;
        }
        public  ArrayList<String> getAdminMenu () {
            ArrayList<String> adminMenu = new ArrayList<>();
            adminMenu.add("1. Add new student");
            adminMenu.add("2. Delete student");
            adminMenu.add("3. Add new module for student");
            adminMenu.add("4. Drop module for student");
            adminMenu.add("5. Go back to main menu");
            adminMenu.add("6. Quit");
            return adminMenu;
        }
        public void addMessageToModuleList(ArrayList<String> output) {
            output.add("Please choose a module that you want to drop.");
        }
    
        
        //======================================================================
        // Input views
        //====================================================================== 
        public String getMenuInput (String options, String title) {
            return JOptionPane.showInputDialog(null, options, title, JOptionPane.QUESTION_MESSAGE);
        }
        public String getInputClassNameToSearchStudentByClass() {
            return JOptionPane.showInputDialog(null, "Enter the class name to search", TITLE6, JOptionPane.QUESTION_MESSAGE);
        }
        public String getInputStudentNameToSearchStudentByName() {
            return JOptionPane.showInputDialog(null, "Enter the name to search", TITLE6, JOptionPane.QUESTION_MESSAGE);
        }
        public String getInputDiplomaToSearchDirectorHonorRoll () {
            return JOptionPane.showInputDialog(null, "Enter the Diploma name to print DHR eg.DIT", TITLE6, JOptionPane.QUESTION_MESSAGE);
        }
        public String getAdminNo() {
            return JOptionPane.showInputDialog(null, "Enter admin number of student", TITLE, JOptionPane.QUESTION_MESSAGE);
        }
        public String takeInput(String value) {
            return JOptionPane.showInputDialog(null, "Enter " + value, TITLE, JOptionPane.QUESTION_MESSAGE);
        }
        public String takeModuleInput(String value, int no) {
            String input = JOptionPane.showInputDialog(null, "Enter " + value + " for module " + no, TITLE, JOptionPane.QUESTION_MESSAGE);
            return input;
        }
        public String takeModuleInput(String value) {
            String input = JOptionPane.showInputDialog(null, "Enter " + value + " for module ", TITLE, JOptionPane.QUESTION_MESSAGE);
            return input;
        }
        
        
        //======================================================================
        // Output views
        //======================================================================
        public void displayAllStudents (String output) {
            JOptionPane.showConfirmDialog(null, output, TITLE2, JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
        }
        public void displaySearchByClass (String output) {
            JOptionPane.showMessageDialog(null, output, TITLE3, JOptionPane.INFORMATION_MESSAGE);
        }
        public void displaySearchbyName (String output) {
            JOptionPane.showConfirmDialog(null, output, TITLE4, JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
        }
        public void displayDirectorHonorRoll (String output) {
            JOptionPane.showMessageDialog(null, output, TITLE5, JOptionPane.INFORMATION_MESSAGE);
        }  
    
        
        //======================================================================
        // Show success views
        //======================================================================
        public void displayNewStudentAdded() {
            JOptionPane.showMessageDialog(null, "New student added successfully!", TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
        public void displayDeleteOK() {
            JOptionPane.showMessageDialog(null, "Student deleted!", TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
        public void displayModuleDropSuccess() {
            JOptionPane.showMessageDialog(null, "Module deleted!", TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        //======================================================================
        // Inform view
        //======================================================================    
        public void displayModuleExistInList() {
            JOptionPane.showMessageDialog(null, "Existing module code.\nModule name, code and credit will be retrieved from the system for you.", TITLE, JOptionPane.INFORMATION_MESSAGE); 
        }
        public void displayNoStudentFoundFromClass () {
            JOptionPane.showMessageDialog(null, "No student found from class!", TITLE3, JOptionPane.INFORMATION_MESSAGE);
        }
        public void displayStudentNotFound() {
            JOptionPane.showMessageDialog(null, "Student not found!", TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
        public void displayDiplomaNotFound () {
            JOptionPane.showMessageDialog(null, "Diploma not found" , TITLE7 ,JOptionPane.INFORMATION_MESSAGE);
        }
        public void displayProgramEnd() {
            JOptionPane.showMessageDialog(null, "Program terminated.\nThank you!", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        //======================================================================
        // Show error views
        //======================================================================    
        public void displayIsBlank () {
            JOptionPane.showMessageDialog (null, "The input cannot be blank", "Error: Input blank", JOptionPane.ERROR_MESSAGE);
        }
        public void promptUserForCorrectInput () {
            JOptionPane.showMessageDialog(null, "Please enter a valid option", "Error: Invalid input", JOptionPane.ERROR_MESSAGE);
        }
        public void displayIsNotInteger () {
            JOptionPane.showMessageDialog(null, "Input must be an integer", "Error: Bad input", JOptionPane.ERROR_MESSAGE);
        }
        public void displayIsNotInRange (int a, int b) {
            JOptionPane.showMessageDialog (null, "The input must be an integer between " + a + " and " + b, "Error: Input not in range", JOptionPane.ERROR_MESSAGE);
        }
             
        //Admin
        public void displayErrorInvalidName() {
            JOptionPane.showMessageDialog(null, "Name must be all alphabetic!", "Error: Invalid name", JOptionPane.ERROR_MESSAGE);
        }
        public void displayErrorInvalidClass() {
            JOptionPane.showMessageDialog(null, "Invalid class format! eg. DIT/FT/2B/23", "Error: Invalid class format", JOptionPane.ERROR_MESSAGE);
        }
        public void displayErrorInvalidAdminNo() {
            JOptionPane.showMessageDialog(null, "Please enter a valid admin number!\n\neg. p2340768", "ERROR: Invalid admin number", JOptionPane.ERROR_MESSAGE);
        }
        public void displayErrorAdminNoConflict() {
            JOptionPane.showMessageDialog(null, "Admin number already exists for another student.\nPlease try again.", "ERROR: Admin number conflict", JOptionPane.ERROR_MESSAGE);
        }
        public void displayModuleExistAldy() {
            JOptionPane.showMessageDialog(null, "Module name is already associated with another module code.\nPlease enter a unique name.", "ERROR: Module name conflict", JOptionPane.ERROR_MESSAGE);
        }
        public void displayModuleTakenAldy() {
            JOptionPane.showMessageDialog(null, "Module is already taken by the student.", "ERROR: Module conflict", JOptionPane.ERROR_MESSAGE);
        }
        public void displayErrorInvalidModuleCode() {
            JOptionPane.showMessageDialog(null, "Invalid module code format.\nPlease try again.\neg. ST0949", "ERROR: Invalid module code", JOptionPane.ERROR_MESSAGE);
        }
        public void displayErrorInvaildModuleName() {
            JOptionPane.showMessageDialog(null, "Module name should be alphabets.\nPlease try again.", "ERROR: Invalid module name", JOptionPane.ERROR_MESSAGE);
        }
        
        // User
        public void displayNoStudentFoundWithName (String name) {
            JOptionPane.showMessageDialog(null,String.format("Cannot find the student \"%s\"!!", name) , TITLE7 ,JOptionPane.ERROR_MESSAGE);
        }
        public void displayInvalidClassFormat (String validation) {
            JOptionPane.showMessageDialog(null,validation ,"ERROR: Invalid class format eg. DIT/FT/2B/23",JOptionPane.ERROR_MESSAGE);
        }
        public void displayInvalidName () {
            JOptionPane.showMessageDialog(null, "Please enter a valid name. eg. John Tan","ERROR: Invalid Name", JOptionPane.ERROR_MESSAGE);
        }
        public void displayInvalidDiplomaName () {
            JOptionPane.showMessageDialog(null,"Please enter a valid diploma name. eg. DIT","ERROR: Invalid Diploma Name",JOptionPane.ERROR_MESSAGE);
        }
        
        
        //======================================================================
        // Confirmation views
        //======================================================================
        public int displayAreYouSure(int count) {
            return JOptionPane.showConfirmDialog(null, "Are you sure you want to add " + count + " modules?", TITLE, JOptionPane.YES_NO_OPTION);
        }
        public static int displayConfirmClassNumber (String output, String caption) {
            return JOptionPane.showConfirmDialog(null, output, caption, JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
        }
}
