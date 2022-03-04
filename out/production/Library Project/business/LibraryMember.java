package business;

public class LibraryMember extends Person {

    private String memberNumber;
    private CheckOutRecord checkoutRecord = new CheckOutRecord();

    public LibraryMember(String id, String firstName, String lastName, String phoneNumber, Address address) {
        super(firstName, lastName, phoneNumber, address);
        this.memberNumber = id;
    }


    public String getMemberId() {
        return memberNumber;
    }

    public CheckOutRecord getCheckOutRecord() {
        return checkoutRecord;
    }

    public void addCheckOutRecordEntry(CheckOutRecordEntry entry) {
        checkoutRecord.addCheckOutRecordEntry(entry);
        entry.setCheckOutRecord(checkoutRecord);
    }
}
