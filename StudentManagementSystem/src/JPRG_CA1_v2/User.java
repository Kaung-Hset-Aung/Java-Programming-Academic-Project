package JPRG_CA1_v2;

/*
 * @author Kaung Htet Thu | Kaung Hset Aung
 *         DIT/FT/2B/23   | DIT/FT/2B/23
 *         P2340768       | P2340698
 */

public class User {
    public static void main(String[] args) {
        StudentManagementModel model = new StudentManagementModel();
        StudentManagementView view = new StudentManagementView ();
        StudentManagementController controller = new StudentManagementController (model, view);
        
        
        //======================================================================
        // Run 
        //======================================================================
        controller.run();
    }
    
}
