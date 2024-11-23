import java.util.ArrayList;
import java.util.List;

class Locations {
    private String NameLocation;
    private String NameBoost;
    private int CfBoosts;
    private String NameDebaf;
    private int CfDebaf;

    public Locations(String NameLocation, String NameBoost, int CfBoosts, String NameDebaf, int CfDebaf) {
        this.NameLocation = NameLocation;
        this.NameBoost = NameBoost;
        this.CfBoosts = CfBoosts;
        this.NameDebaf = NameDebaf;
        this.CfDebaf = CfDebaf;
    }

    public String getNameLocation() {
        return NameLocation;
    }

    public String getNameBoost() {
        return NameBoost;
    }

    public int getCfBoosts() {
        return CfBoosts;
    }

    public String getNameDebaf() {
        return NameDebaf;
    }

    public int getCfDebaf() {
        return CfDebaf;
    }
}

public class Location {
    public static List<Locations> getLocationsList() {
        List<Locations> locations = new ArrayList<>();
        locations.add(new Locations("Ліс", "Оборона", 20, "Наступ", 10));
        locations.add(new Locations("Рівнини", "Наступ", 15, "Оборона", 10));
        locations.add(new Locations("Болот", "Оборона", 25, "Наступ", 20));
        locations.add(new Locations("Місто", "Оборона", 30, "Наступ", 20));
        return locations;
    }
}
