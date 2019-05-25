package com.example.sarnobatvipul.mydocapp.database;

/**
 * Created by mr. A on 28-02-2019.
 */

public class Patient
{
    public String id,contact,emailid,gender,dob,bloodgroup,address,name;

    public Patient()
    {}

    public Patient(String id, String contact, String emailid, String gender, String dob, String bloodgroup, String address, String name) {
        this.id = id;
        this.contact = contact;
        this.emailid = emailid;
        this.gender = gender;
        this.dob = dob;
        this.bloodgroup = bloodgroup;
        this.address = address;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
