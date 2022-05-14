package fr.shurisko.security;

import fr.shurisko.account.BDEAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BDEToken {

    private final Map<String, BDEAccount> accountLogged;

    public BDEToken () {
        this.accountLogged = new HashMap<>();
    }

    public Map<String, BDEAccount> getAccountLogged() {
        return accountLogged;
    }

    public BDEAccount isLogin(String token) {
        if (accountLogged.containsKey(token))
            return accountLogged.get(token);
        return null;
    }

    public String addToken(BDEAccount account) {
        UUID token = UUID.randomUUID();
        for (String key : accountLogged.keySet()) {
            if (accountLogged.get(key).getAccountUUID().equalsIgnoreCase(account.getAccountUUID())) {
                accountLogged.remove(key);
                break;
            }
        }
        accountLogged.put(token.toString(), account);
        return token.toString();
    }

    public void destroyToken(String token) {
        accountLogged.remove(token);
    }

}
