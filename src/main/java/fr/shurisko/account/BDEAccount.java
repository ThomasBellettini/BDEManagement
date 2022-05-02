package fr.shurisko.account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BDEAccount {

    private String accountUUID;
    private String accountName;
    private String accountPassword;

    private Map<BDEPermissionEnum, Boolean> permissions;

    public BDEAccount(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;

        permissions = new HashMap<>();
        for (BDEPermissionEnum bdePermissionEnum : BDEPermissionEnum.values())
            permissions.put(bdePermissionEnum, bdePermissionEnum.isPerDefault());
        this.accountUUID = UUID.randomUUID().toString();
    }

    public String getAccountUUID() {
        return accountUUID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public Map<BDEPermissionEnum, Boolean> getPermissions() {
        return permissions;
    }
}
