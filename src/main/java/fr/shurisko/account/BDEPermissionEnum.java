package fr.shurisko.account;

public enum BDEPermissionEnum {

    CREATE_ACCOUNT("Account Creation", false),
    MANAGING_ACCOUNT("Account Management", false),
    DELETE_ACCOUNT("Remove Account", false),
    CREATE_EVENT("Create Event", false),
    MANAGING_EVENT("Manage Event", false),
    DELETE_EVENT("Delete Event", false),
    EVENT_SELLER("Event Seller", true),
    EVENT_COOKER("Event Cooker", true),
    VIEW_BENEFIT("View Event Benefit", false),
    EDIT_OTHER_PASSWORD("Edit Other Passowrd", false);

    private String permissionName;
    private boolean perDefault;

    BDEPermissionEnum(String permissionName, boolean perDefault) {
        this.permissionName = permissionName;
        this.perDefault = perDefault;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public boolean isPerDefault() {
        return perDefault;
    }

    public BDEPermissionEnum getFromName(String permissionName)
    {
        for (BDEPermissionEnum permissionEnum : BDEPermissionEnum.values())
            if (permissionEnum.getPermissionName().equalsIgnoreCase(permissionName))
                return permissionEnum;
        return null;
    }
}
