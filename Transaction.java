import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private static int nextId = 1001;

    private int transactionId;
    private String type;
    private double amount;
    private String description;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, String description) {

        this.transactionId = nextId++;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("------------------------------");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Type           : " + type);
        System.out.println("Amount         : Rs." + amount);
        System.out.println("Description    : " + description);
        System.out.println("Date & Time    : "
                + dateTime.format(formatter));
    }
}