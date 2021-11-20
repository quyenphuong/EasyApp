package com.example.easyapp.ui.driver;

public class ApplicationUser {
    private String Id_user;
    private String Email;
    private String PhoneStamp;
    private String UserName;

    public ApplicationUser(String Id_user, String Email, String PhoneStamp, String UserName){
        this.Id_user = Id_user;
        this.Email = Email;
        this.PhoneStamp = PhoneStamp;
        this.UserName = UserName;
    }
    public String getId_user() {
        return Id_user;
    }

    public void setId_user(String id_user) {
        Id_user = id_user;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneStamp() {
        return PhoneStamp;
    }

    public void setPhoneStamp(String phoneStamp) {
        PhoneStamp = phoneStamp;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


}
