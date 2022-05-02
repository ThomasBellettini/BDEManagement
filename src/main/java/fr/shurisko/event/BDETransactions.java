package fr.shurisko.event;

import java.util.List;

public class BDETransactions {

    public enum TransactionType {
        CARD,
        CASH
    }

    private final String transactionUUID;
    private final String transactionSeller;
    private final String transactionBuyer;

    private final List<BDEEvent.BDEProduct> productList;
    private final TransactionType transactionType;

    public BDETransactions(String transactionUUID, String transactionSeller, String transactionBuyer, List<BDEEvent.BDEProduct> productList, TransactionType transactionType) {
        this.transactionUUID = transactionUUID;
        this.transactionSeller = transactionSeller;
        this.transactionBuyer = transactionBuyer;
        this.productList = productList;
        this.transactionType = transactionType;
    }

    public String getTransactionUUID() {
        return transactionUUID;
    }

    public String getTransactionSeller() {
        return transactionSeller;
    }

    public String getTransactionBuyer() {
        return transactionBuyer;
    }

    public List<BDEEvent.BDEProduct> getProductList() {
        return productList;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
