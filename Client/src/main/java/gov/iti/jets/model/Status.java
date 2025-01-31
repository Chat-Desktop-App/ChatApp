package gov.iti.jets.model;

/*public enum Status {
    Online,
    Idle,
    Offline
}*/

public class Status {
    private final String name;
    private final String iconPath;

    public Status(String name, String iconPath) {
        this.name = name;
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public String getIconPath() {
        return iconPath;
    }

    @Override
    public String toString() {
        return name; 
    }
}
