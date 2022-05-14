package np.com.surajphueudin.bloodbankingapp.beans;

import java.io.Serializable;

public class DonorsBean implements Serializable {
    String name;
    String bloodGroup;
    String date;
    String time;
    String location;
    String lastDonation;
    String contact;

    public DonorsBean(){

    }

    public DonorsBean(String name,
                      String bloodGroup, String date,
                      String time, String location, String lastDonation, String contact) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.date = date;
        this.time = time;
        this.location = location;
        this.lastDonation = lastDonation;
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLastDonation(String lastDonation) {
        this.lastDonation = lastDonation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getContact() {
        return contact;
    }

    public String getDate() {
        return date;
    }

    public String getLastDonation() {
        return lastDonation;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
}

