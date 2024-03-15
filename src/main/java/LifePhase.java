package main.java;

public enum LifePhase {
    BABY,
    TODDLER,
    CHILD,
    TEEN,
    ADULT,
    ELDER;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}


