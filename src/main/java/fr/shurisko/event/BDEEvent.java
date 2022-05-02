package fr.shurisko.event;

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

    private final List<BDEProduct> productList;
    private final List<BDETransactions> transactionsList;

    public BDEEvent(String eventName, String eventCode) {
        this.eventName = eventName;
        this.eventCode = eventCode;

        this.eventUUID = UUID.randomUUID().toString();
        this.productList = new ArrayList<>();
        this.transactionsList = new ArrayList<>();
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
}
