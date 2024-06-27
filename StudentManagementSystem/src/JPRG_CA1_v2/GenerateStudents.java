package JPRG_CA1_v2;

/*
 * @author Kaung Htet Thu | Kaung Hset Aung
 *         DIT/FT/2B/23   | DIT/FT/2B/23
 *         P2340768       | P2340698
 */

import java.util.ArrayList;
import java.util.Random;

public class GenerateStudents {
    //==============================================================
    // Constants
    //==============================================================
    private final String[] firstNames = {
        "John", "Harry", "James", "William", "Oliver", "Benjamin", "Elijah", "Lucas", "Mason", "Logan",
        "Alexander", "Ethan", "Jacob", "Michael", "Daniel", "Henry", "Jackson", "Sebastian", "Aiden", "Matthew",
        "Samuel", "David", "Joseph", "Carter", "Owen", "Wyatt", "Jack", "Luke", "Jayden", "Dylan",
        "Grayson", "Levi", "Isaac", "Gabriel", "Julian", "Mateo", "Anthony", "Jaxon", "Lincoln", "Joshua",
        "Christopher", "Andrew", "Theodore", "Caleb", "Ryan", "Asher", "Nathan", "Thomas", "Leo", "Isaiah",
        "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Mia", "Amelia", "Harper", "Evelyn", "Abigail",
        "Emily", "Ella", "Elizabeth", "Camila", "Luna", "Sofia", "Avery", "Mila", "Aria", "Scarlett",
        "Penelope", "Layla", "Chloe", "Victoria", "Madison", "Eleanor", "Grace", "Nora", "Riley", "Zoey",
        "Hannah", "Hazel", "Lily", "Ellie", "Violet", "Lillian", "Zoe", "Stella", "Aurora", "Natalie",
        "Emilia", "Everly", "Leah", "Aubrey", "Willow", "Addison", "Lucy", "Audrey", "Bella", "Nova"
    };

    private final String[] lastNames = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
        "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
        "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
        "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
        "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts",
        "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker", "Cruz", "Edwards", "Collins", "Reyes",
        "Stewart", "Morris", "Morales", "Murphy", "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper",
        "Peterson", "Bailey", "Reed", "Kelly", "Howard", "Ramos", "Kim", "Cox", "Ward", "Richardson",
        "Watson", "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza", "Ruiz", "Hughes",
        "Price", "Alvarez", "Castillo", "Sanders", "Patel", "Myers", "Long", "Ross", "Foster", "Jimenez"
    };

    private final String[] moduleInfo = {
            "ST0509/JPRG/5", "ST0001/BED/6", "ST0002/FOP2/5", 
            "ST0003/FOP/5", "MS0105/MATH/3", "ST2413/FOC/5", 
            "ST0525/DBS/6", "ST0501/FED/5"};

    private final String[] allDiplomaNames = {
        "DIT",   // Diploma in Information Technology
        "DISM",  // Diploma in Infocomm Security Management
        "DAAA",  // Diploma in Applied AI and Anatytics
        "DBA",   // Diploma in Business Administration
        "DAC",   // Diploma in Accountancy
        "DME",   // Diploma in Mechanical Engineering
        "DBS",   // Diploma in Biomedical Science
        "DARCH", // Diploma in Architecture
        "DMB",   // Diploma in Maritime Business
        "DEWT"   // Diploma in Environmental Management & Water Technology 
    };

    private final String[] allClassTypes = {
        "1A", "1B", "2A", "2B"
    };
    
    private final String[] allDiplomaTypes = {
        "FT", "PT"};

    
    //==============================================================
    // Attributes / Properties
    //==============================================================
    private ArrayList<Module> moduleList;

    
    //==============================================================
    // Constructor
    //==============================================================
    public GenerateStudents() {
        this.moduleList  = createModuleList();
    }

    
    //==============================================================
    // Getters
    //==============================================================
    public ArrayList<Module> getModuleList() {
        return this.moduleList;
    }

    
    //==============================================================
    // Methods
    //==============================================================
    private ArrayList<Module> createModuleList() {
        ArrayList<Module> list = new ArrayList<>();
        for (String info : moduleInfo) {
            try {
                String[] data = info.split("/");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Invalid module info format: " + info);
                }
                String module_code = data[0];
                String module_name = data[1];
                int module_credit = Integer.parseInt(data[2]);
                list.add(new Module(module_code, module_name, module_credit, 0));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing module credit: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
        
        return list;
    }
    
    public int getRandomInteger(int lowerLimit, int upperLimit) {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Lower limit must be less than or equal to upper limit");
        }
        Random random = new Random();
        
        return lowerLimit + random.nextInt(upperLimit - lowerLimit + 1);
    }

    public String getRandomName () {

        int firstLength = this.firstNames.length - 1;
        int secLength = this.lastNames.length - 1;
        String firstName = firstNames[getRandomInteger(0, firstLength)];
        String lastName = lastNames[getRandomInteger(0, secLength)];

        return firstName + " " + lastName;
    }

    public ArrayList<Module> getRandom5ModuleTaken () {
        ArrayList<Module> moduleListCopy = new ArrayList<>();
        ArrayList<Module> moduleTaken = new ArrayList<>();

        moduleListCopy.addAll(moduleList);

        for (int i = 0 ; i < 5 && !moduleListCopy.isEmpty(); i ++) {
            int index = getRandomInteger(0, moduleListCopy.size() - 1);
            int mark = getRandomInteger(60,100);
            Module module = new Module();
            module = moduleListCopy.get(index);
            moduleListCopy.remove(index);
            module.setMark(mark);
            moduleTaken.add(module);
        }
        
        return moduleTaken;
    }

    public String getRandomClass (String[] allDiplomaName, String[] allDiplomaType, String[] allClassType) {
        String diplomaName = allDiplomaName[getRandomInteger(0, allDiplomaName.length-1)];
        String diplomaType = allDiplomaType[getRandomInteger(0, allDiplomaType.length-1)];
        String classType = allClassTypes[getRandomInteger(0, allClassTypes.length-1)];
        String classNumber = String.format("%02d", getRandomInteger(1, 20));

        return String.format("%s/%s/%s/%s",diplomaName,diplomaType,classType,classNumber);
    }

    public ArrayList<Student> generateStudentList(int numberOfRandomStudents) {
        ArrayList<Student> list = new ArrayList<>();
        for(int x = 1 ; x <= numberOfRandomStudents; x ++) {                 
            list.add( new Student(getRandomClass(allDiplomaNames,allDiplomaTypes,allClassTypes), ("p" + (2400000 + x)), getRandomName(), getRandom5ModuleTaken()) ); 
        }
        return list;
    }
}
