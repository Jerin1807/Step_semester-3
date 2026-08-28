public class Employee {
    String empId;
    String empName;
    double salary;
    boolean isIntern;
    Employee(String empId,String empName,double salary){
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }
    Employee(String empId,String empName){
        this(empId,empName,0);
        this.isIntern = true;
    }
    public void printProfile(){
        System.out.println("E- " + empId + " / " + empName + " / " + " Rs " + salary + " / " + " Intern: " + isIntern);
    }
    public static void main(String[] args){
        Employee e1 = new Employee("101","Divya",65000);
        Employee e2 = new Employee("102","Arjun");
        e1.printProfile();
        e2.printProfile();
    }

    
}
