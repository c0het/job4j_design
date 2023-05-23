package ru.job4j.serialization;

import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {


    @XmlAttribute
    private  int numberOfOrder;
    private Contact contact;

    @XmlElement(name = "manName")
    private String managerName;

    @XmlTransient
    private boolean vipStatus;

    @XmlElementWrapper
    @XmlElement(name = "oldOrder")
    private int[] oldOrders;

    public Customer() { }

    public Customer(int numberOfOrder, Contact contact, String managerName, boolean vipStatus, int[] oldOrders) {
        this.numberOfOrder = numberOfOrder;
        this.contact = contact;
        this.managerName = managerName;
        this.vipStatus = vipStatus;
        this.oldOrders = oldOrders;
    }

    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public Contact getContact() {
        return contact;
    }

    public String getManagerName() {
        return managerName;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public int[] getOldOrders() {
        return oldOrders;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "contact=" + contact
                + ", numberOfOrder=" + numberOfOrder
                + ", managerName='" + managerName + '\''
                + ", vipStatus=" + vipStatus
                + ", oldOrders=" + Arrays.toString(oldOrders)
                + '}';
    }


    private static String convertObjToXml(Customer customer) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(customer, writer);
            xml = writer.getBuffer().toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    private static Customer readFromXml(Customer customer) {
        String xml = convertObjToXml(customer);
        Customer rsl = null;
        try (StringReader reader = new StringReader(xml)) {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            Unmarshaller un = context.createUnmarshaller();
            rsl = (Customer) un.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rsl;
    }


    public static void main(String[] args) throws JSONException {
        Customer customer = new Customer(5454, new Contact(111,
                "444-444-444"), "Stanislav", true, new int[]{5452, 5453});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numberOfOrder", customer.getNumberOfOrder());
        jsonObject.put("contact", customer.getContact());
        jsonObject.put("managerName", customer.getManagerName());
        jsonObject.put("vipStatus", customer.isVipStatus());
        jsonObject.put("oldOrders", customer.getOldOrders());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(customer).toString());
    }
}
