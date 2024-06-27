package JPRG_CA1_v2;

/*
 * @author Kaung Htet Thu 
 *         DIT/FT/2B/23  
 *         P2340768      
 */

public class Module {
  //==============================================================
  // Attributes / Properties
  //==============================================================
  private String moduleCode;
  private String moduleName;
  private int moduleCredit;
  private int mark;

  
  //==============================================================
  // Constructors
  //==============================================================
  public Module() { 
    moduleCode = "";
    moduleName = "";
    moduleCredit = -1;
    mark = 0;
  }

  public Module(String moduleCode, String moduleName, int moduleCredit, int mark) {
    this.moduleCode = moduleCode;
    this.moduleName = moduleName.toUpperCase();
    this.moduleCredit = moduleCredit;
    this.mark = mark;
  }

  
  //==============================================================
  // Getters / Setters
  //==============================================================
  public String getModuleCode() {
    return moduleCode;
  }
  public String getModuleName() {
    return moduleName;
  }
  public int getModuleCredit() {
    return moduleCredit;
  }
  public int getMark() {
    return mark;
  }
  public char getGrade() {
    return modelConvertToGrade(this.mark);
  }
  public void setMark(int mark) {
    this.mark = mark;
  }
  
  
  //==============================================================
  // Methods
  //==============================================================
  public char modelConvertToGrade (double marks) {
    if (marks >= 80){
        return 'A';
    } else if (marks >= 70 ) {
        return 'B';
    } else if (marks >= 60) {
        return 'C';
    } else if (marks >= 50) {
        return 'D';
    } else return 'F';
  }

  
  @Override
  public String toString () {
      return moduleCode + "/" + moduleName + "/" + moduleCredit + " : " + modelConvertToGrade(mark);
  }
  
}
