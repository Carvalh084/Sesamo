package com.stappco.password.sesamo;

class ExampleItemSocial {
    private String mTitle;
    private String mPassword;
    private String mNotes;

    public ExampleItemSocial(String Title, String Password, String Notes) {
        mTitle = Title;
        mPassword = Password;
        mNotes = Notes;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getPassword() {
        return mPassword;
    }
    public  String getNotes(){
        return mNotes;
    }
}

