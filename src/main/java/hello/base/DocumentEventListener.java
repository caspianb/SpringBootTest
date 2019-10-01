package hello.base;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class DocumentEventListener<T extends BaseDocument> extends AbstractMongoEventListener<T> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<T> event) {
        super.onBeforeConvert(event);
    }

    @Override
    public void onAfterConvert(AfterConvertEvent<T> event) {
        super.onAfterConvert(event);
    }
}
