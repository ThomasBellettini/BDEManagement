package fr.shurisko.manager;

import fr.shurisko.account.BDEAccount;
import fr.shurisko.event.BDEEvent;

import java.util.ArrayList;
import java.util.List;

public class BDEManager {

    private final List<BDEAccount> bdeAccounts;
    private final List<BDEEvent> bdeEvents;

    public BDEManager () {
        this.bdeAccounts = new ArrayList<>();
        this.bdeEvents = new ArrayList<>();
    }

    public List<BDEAccount> getListAccount() {
        return bdeAccounts;
    }

    public List<BDEEvent> getListEvent() {
        return bdeEvents;
    }

    public BDEAccount getAccountFromEmail(String email) {
        for (BDEAccount account : bdeAccounts)
            if (account.getAccountName().equalsIgnoreCase(email))
                return account;
        return null;
    }

    public BDEEvent getEventFromCode(String code) {
        for (BDEEvent event : bdeEvents)
            if (event.getEventCode().equalsIgnoreCase(code))
                return event;
        return null;
    }

}
