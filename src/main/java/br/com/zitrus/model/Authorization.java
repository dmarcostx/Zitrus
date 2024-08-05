package br.com.zitrus.model;

public final class Authorization {
    private int id;
    private short procedure;
    private byte age;
    private boolean male;
    private boolean allowed;
    private String reason;

    public int getId() {
        return id;
    }

    public Authorization setId(int id) {
        this.id = id;
        return this;
    }

    public short getProcedure() {
        return procedure;
    }

    public Authorization setProcedure(short procedure) {
        this.procedure = procedure;
        return this;
    }

    public byte getAge() {
        return age;
    }

    public Authorization setAge(byte age) {
        this.age = age;
        return this;
    }

    public boolean isMale() {
        return male;
    }

    public Authorization setMale(boolean male) {
        this.male = male;
        return this;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public Authorization setAllowed(boolean allowed) {
        this.allowed = allowed;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Authorization setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
