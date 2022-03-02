package business;

public class Author extends Person{

    private String shortBio;

    public Author(String firstName, String lastName, String phoneNumber, Address address, String shortBio)
    {
        super(firstName, lastName, phoneNumber, address);
        this.shortBio = shortBio;
    }
}
