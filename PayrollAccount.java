class PayrollAccount{
    private double basicSalary;
    private double bonus;
    public PayrollAccount(double basicSalary){
        if(basicSalary<0){
            System.out.println("warning\n");
        }
        else{
            this.basicSalary = basicSalary;
        }
    }
    public void creditBonus(double amount){
        if(amount<=0){
            System.out.println("rejected\n");
        }
        else{
            bonus = bonus + amount;
        }
    }
    public void deductTax(double percent){
        if(percent>0 && percent<100){
        basicSalary = basicSalary - (basicSalary*(percent/100));
        }
        else{
            System.out.println("percent rejected\n");
        }
    }
    public double getNetSalary(){
        return basicSalary + bonus;
    }
    public static void main(String[] args){
        PayrollAccount p = new PayrollAccount(50000);
        p.creditBonus(5000);
        p.deductTax(10);
        System.out.println("Bonus credited: Rs 5000.0");
        System.out.println("Tax deducted: 10%" );
        System.out.println("Net Salary: Rs " + p.getNetSalary());
        
    }
}