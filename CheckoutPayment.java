abstract class PaymentMethod{
    static int count=1001;
    final String transactionId;
    PaymentMethod(){
        transactionId="TXN-"+count++;
    }
    abstract String processPayment(double amount);
    String processPayment(double amount,String note){
        return processPayment(amount)+" ("+note+")";
    }
    String getTransactionId(){
        return transactionId;
    }
}
class CreditCardPayment extends PaymentMethod{
    String cardNumberLastFour;
    CreditCardPayment(String cardNumberLastFour){
        this.cardNumberLastFour=cardNumberLastFour;
    }
    String processPayment(double amount){
        return "Charged $"+amount+" to card ending "+cardNumberLastFour+" - Txn "+transactionId;
    }
}
class CashPayment extends PaymentMethod{
    CashPayment(){}
    String processPayment(double amount){
        return "Received $"+amount+" in cash - Txn "+transactionId;
    }
}
public class CheckoutPayment{
    static void printConfirmation(PaymentMethod payment,double amount){
        System.out.println(payment.processPayment(amount));
    }
    public static void main(String[] args){
        CreditCardPayment cc=new CreditCardPayment("4471");
        CashPayment cash=new CashPayment();
        System.out.println(cc.processPayment(250.0));
        System.out.println(cc.processPayment(250.0,"Birthday gift"));
        System.out.println(cash.processPayment(40.0));
        PaymentMethod ref=cc;
        printConfirmation(ref,250.0);
    }
}