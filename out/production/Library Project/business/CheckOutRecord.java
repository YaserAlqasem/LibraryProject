package business;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord  implements Serializable {
    @Serial
    private static final long serialVersionUID = -64028886303660737L;
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
