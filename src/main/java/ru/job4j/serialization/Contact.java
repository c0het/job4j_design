package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.*;
import java.nio.file.Files;


@XmlRootElement(name = "contact")
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @XmlTransient
    private int zipCode;

    @XmlAttribute
    private String phone;

    public Contact() { }

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }



    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException {
        final Contact contact = new Contact(11111, "999-999-99-99");

        File file = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
