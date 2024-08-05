package br.com.zitrus.model;

public final class Rule {
    private int id;
    private short procedure;
    private byte ageMin;
    private byte ageMax;
    private boolean male;
    private boolean active;

    public int getId() {
        return id;
    }

    public Rule setId(int id) {
        this.id = id;
        return this;
    }

    public short getProcedure() {
        return procedure;
    }

    public Rule setProcedure(short procedure) {
        this.procedure = procedure;
        return this;
    }

    public byte getAgeMin() {
        return ageMin;
    }

    public Rule setAgeMin(byte ageMin) {
        this.ageMin = ageMin;
        return this;
    }

    public byte getAgeMax() {
        return ageMax;
    }

    public Rule setAgeMax(byte ageMax) {
        this.ageMax = ageMax;
        return this;
    }

    public boolean isMale() {
        return male;
    }

    public Rule setMale(boolean male) {
        this.male = male;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Rule setActive(boolean active) {
        this.active = active;
        return this;
    }
}
