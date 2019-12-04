package hello.order;

import hello.config.AbstractEntity;
import hello.config.JpaConfig;

import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "orders", indexes = {
        @Index(columnList = "tenant"),
        @Index(columnList = "tenant, itemNumber", unique = true)
})
public class Order extends AbstractEntity {

    private String itemNumber;
    private ConvertedOrderObj convertedOrderObj;

    @Converter(autoApply = true)
    private static class NestedOrderObjConverter extends JpaConfig.JpaJsonConverter<ConvertedOrderObj> {
        public NestedOrderObjConverter() {
            super(ConvertedOrderObj.class);
        }
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public ConvertedOrderObj getConvertedOrderObj() {
        return convertedOrderObj;
    }

    public void setConvertedOrderObj(ConvertedOrderObj convertedOrderObj) {
        this.convertedOrderObj = convertedOrderObj;
    }

    @Override
    public String toString() {
        return String.format(
                "%s [id=%s, tenant=%s, itemNumber=%s, nestedOrderObj=%s, createdDate=%s, lastModifiedDate=%s]",
                this.getClass().getSimpleName(), this.getId(), this.getTenant(), this.itemNumber, this.convertedOrderObj, this.getCreatedDate(), this.getLastModifiedDate());
    }
}
