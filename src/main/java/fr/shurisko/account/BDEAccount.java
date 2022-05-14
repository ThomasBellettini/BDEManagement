package fr.shurisko.account;

import fr.shurisko.BDEServer;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BDEAccount {

    private final String accountUUID;
    private final String accountName;
    private String accountPassword;

    private BDERankEnum accountRank;
    private Map<BDEPermissionEnum, Boolean> permissions;

    public BDEAccount(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = DigestUtils.md5Hex(
                BDEServer.getInstance.systemEnv.get("SALT_PREFIX") + accountPassword + BDEServer.getInstance.systemEnv.get("SALT_SUFFIX")
        );

        permissions = new HashMap<>();
        accountRank = BDERankEnum.MEMBER;
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

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public void setAccountRank(BDERankEnum accountRank) {
        this.accountRank = accountRank;
    }
}
