package fr.shurisko.event;

import fr.shurisko.event.command.BDEClientCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BDEEvent {

    public static class BDEProduct {
        private final String productName;
        private final double productPrice;

        public BDEProduct(String productName, double productPrice) {
            this.productName = productName;
            this.productPrice = productPrice;
        }

        public String getProductName() {
            return productName;
        }

        public double getProductPrice() {
            return productPrice;
        }
    }

    private final String eventUUID;
    private final String eventName;
    private final String eventCode;
    private final String managerCode;

    private boolean isFinish;

    private final List<BDEProduct> productList;
    private final List<BDETransactions> transactionsList;
    private final List<BDEClientCommand> commandList;

    public BDEEvent(String eventName, String eventCode) {
        this.eventName = eventName;
        this.eventCode = eventCode;

        this.managerCode = UUID.randomUUID().toString().substring(0, 6);
        this.eventUUID = UUID.randomUUID().toString();
        this.productList = new ArrayList<>();
        this.transactionsList = new ArrayList<>();
        this.commandList = new ArrayList<>();
        this.isFinish = false;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public List<BDEProduct> getProductList() {
        return productList;
    }

    public List<BDETransactions> getTransactionsList() {
        return transactionsList;
    }

    public List<BDEClientCommand> getCommandList() {
        return commandList;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
