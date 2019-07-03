package com.sap.fiori.copilot.agentmodel.context;

public class SourceSystemUser {

    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String language;
    private boolean dsxKeyUser = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public boolean getDsxKeyUser() {
        return dsxKeyUser;
    }

    public void setDsxKeyUser(boolean dsxKeyUser) {
        this.dsxKeyUser = dsxKeyUser;
    }

    @Override
    public String toString() {
        return "SourceSystemUser [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
                + email + ", language=" + language + ", dsxKeyUser=" + this.dsxKeyUser + "]";
    }

}
