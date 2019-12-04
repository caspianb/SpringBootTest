package hello.order;

import java.util.Date;

public class ConvertedOrderObj {

    private int intField;
    private String stringField;
    private Date dateField;

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    @Override
    public String toString() {
        return String.format(
                "%s [intField=%s, stringField=%s, dateField=%s]",
                this.getClass().getSimpleName(), this.intField, this.stringField, this.dateField);
    }
}
