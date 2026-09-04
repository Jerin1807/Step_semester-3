class Employee2 {

    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee2(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName + " Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {

        Employee2 e1 = new Employee2("Arjun", 30000);
        Employee2 e2 = new Employee2("Priya", 35000);
        Employee2 e3 = new Employee2("Rahul", 40000);

        Employee2.printCompanyInfo();
    }
}