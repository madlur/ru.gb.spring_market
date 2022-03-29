import document.Order;
import strategies.PayByCreditCard;
import strategies.PayByPayPal;
import strategies.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    public static void main(String[] args) throws IOException {
        if (strategy == null) {
            System.out.println("Please, select a payment method:" + "\n" +
                    "1 - PayPal" + "\n" +
                    "2 - Credit Card");
            String paymentMethod = reader.readLine();

            if (paymentMethod.equals("1")) {
                strategy = new PayByPayPal();
            } else {
                strategy = new PayByCreditCard();
            }
        }

        order.processOrder(strategy);

        if (strategy.pay(order.getTotalCost())) {
            System.out.println("Payment has been successful.");
        } else {
            System.out.println("FAIL!");
        }
        order.setClosed();
    }
}
