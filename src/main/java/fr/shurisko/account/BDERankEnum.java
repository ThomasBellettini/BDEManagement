package fr.shurisko.account;

public enum BDERankEnum {

    PRESIDENT("Président", 0, ""),
    VICE_PRESIDENT("Vice-Président", 1, ""),
    TRESORY("Trésorier", 2, ""),
    SECURITY_MANAGER("Responsable Sécurité", 3, ""),
    COOK_MANAGER("Responsable Cuisine", 4, ""),
    SECRETARY("Secrétaire", 5, ""),
    MEMBER("Membre", 6, "");

    public String rankName;
    public int rankOrder;
    public String rankHexColor;

    BDERankEnum(String rankName, int rankOrder, String rankHexColor) {
        this.rankName = rankName;
        this.rankOrder = rankOrder;
        this.rankHexColor = rankHexColor;
    }
}
