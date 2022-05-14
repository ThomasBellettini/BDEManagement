package fr.shurisko.event.command;

import fr.shurisko.event.BDEEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BDEClientCommand {

    private final long timestamp;

    private final String clientName;
    private final String clientEmail;

    private boolean isReady;
    private final List<BDEEvent.BDEProduct> productList;

    public BDEClientCommand(String clientName, String clientEmail) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.timestamp = new Date().getTime();
        this.isReady = false;
        productList = new ArrayList<>();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public boolean isReady() {
        return isReady;
    }

    public List<BDEEvent.BDEProduct> getProductList() {
        return productList;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
