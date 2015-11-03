package com.turreta.mockito.voidmethod;

public class PersonRecord {

    private String id;
    private String status;

    private Boolean hasDependents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getHasDependents() {
        return hasDependents;
    }

    public void setHasDependents(Boolean hasDependents) {
        this.hasDependents = hasDependents;
    }
}
