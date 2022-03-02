package business;

public class Address {

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
