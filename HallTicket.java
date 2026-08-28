public class HallTicket {
    String studentName;
    int seatNumber;
    HallTicket(String studentName,int seatNumber){
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
    public static void main(String[] args){
        HallTicket priya = new HallTicket("priya",0);
        HallTicket copy = priya;
        copy.seatNumber = 45;
        System.out.println("priya's seatnumber (vis first variable):" + priya.seatNumber);
        System.out.println("copy == priya : " + (copy == priya));
        HallTicket separate = new HallTicket("Priya",45);
        System.out.println("separate == priya: " + (separate == priya));
    }
}
