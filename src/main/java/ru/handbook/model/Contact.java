package ru.handbook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 11.07.2017.
 */
public class Contact implements Serializable {

    private String contactName;
    private String telephone;
    private String skype;
    private String mail;
    private List<String> contactGroups;

    public List<String> getContactGroups() {
        return contactGroups;
    }

    public void setContactGroups(String contactGroup) {
        if (contactGroups == null) {
            contactGroups = new ArrayList();
        }
        contactGroups.add(contactGroup);
    }

    public Contact(String name) {
        this.contactName = name;
    }

    public void setContactName(String name) {
        this.contactName = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void getContactInfo() {
        System.out.println("Name: " + getContactName() +
                "\ntelehpone: " + getTelephone() +
                "\nskype: " + getSkype() +
                "\nmail: " + getMail() + "\n");
        System.out.println("Groups");
        if (contactGroups != null) {
            for (String groupName : contactGroups) {
                System.out.println(groupName);
            }
            System.out.println("----------");
        } else System.out.println("Group list is empty");
    }

    public String getContactName() {
        return contactName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getSkype() {
        return skype;
    }

    public String getMail() {
        return mail;
    }
}
