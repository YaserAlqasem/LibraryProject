package business;

import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord {
    private List<CheckOutRecordEntry> checkOutEntries;

    public CheckOutRecord() {
        checkOutEntries = new ArrayList<>();
    }

    public void addCheckOutRecordEntry(CheckOutRecordEntry checkOutRecordEntry) {
        checkOutEntries.add(checkOutRecordEntry);
        checkOutRecordEntry.setCheckOutRecord(this);
    }

    public List<CheckOutRecordEntry> getCheckOutRecordEntries(){
        return checkOutEntries;
    }
}
