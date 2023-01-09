package org.nikafit.NikaftHub.enumeration;

public enum Position {
    JUNIOR_COACH("JUNIOR_COACH"),
    SENIOR_COACH("SENIOR_COACH"),
    HEAD_COACH("HEAD_COACH"),
    SITE_MANAGER("SITE_MANAGER"),
    OWNER("OWNER");

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }
}
