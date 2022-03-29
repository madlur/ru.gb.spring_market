package document;

import strategies.PayStrategy;

public class Order implements Document {
    private int totalCost = 0;
    private boolean isClosed = false;

    @Override
    public Document copy() {
        return new Order();
    }

    @Override
    public void edit() {
    }

    @Override
    public void save() {
    }

    public void processOrder(PayStrategy strategy) {
        strategy.collectPaymentDetails();
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}
