package business;

import java.io.Serial;
import java.io.Serializable;

public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 504221651053881975L;
    private String state;
    private String street;
    private String zipCode;
    private String city;

    public Address(String state, String street, String zipCode, String city)
    {
        this.state = state;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
}
