package br.com.zitrus.model;

public final class Response {
    private int id;
    private boolean allowed;
    private String reason;

    public int getId() {
        return id;
    }

    public Response setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public Response setAllowed(boolean allowed) {
        this.allowed = allowed;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Response setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
