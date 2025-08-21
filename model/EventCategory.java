package model;

public enum EventCategory {
    CONFERENCE("Conferência"),
    WORKSHOP("Workshop"),
    SEMINAR("Seminário"),
    MEETING("Reunião"),
    SOCIAL("Social"),
    SPORTS("Esportes"),
    CULTURAL("Cultural"),
    EDUCATIONAL("Educacional");
    
    private final String displayName;
    
    EventCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
