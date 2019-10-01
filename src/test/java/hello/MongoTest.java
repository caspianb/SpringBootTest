package hello;

import hello.composite.Composite;
import hello.composite.CompositeRepository;
import hello.simple.Simple;
import hello.simple.SimpleRepository;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MongoTest {

    @Autowired
    private SimpleRepository simpleRepository;

    @Autowired
    private CompositeRepository compositeRepository;

    @Test
    public void testSimple() {
        Simple simple = new Simple();
        simple.setName("Simple name");

        simpleRepository.save(simple);
    }

    @Test
    public void testComposite() {
        Composite composite = new Composite();
        composite.setName("Composite name");

        Composite.CompositeId id = new Composite.CompositeId();
        id.setId(UUID.randomUUID().toString());
        id.setTenant("Tenant-X");
        composite.setCompositeId(id);

        System.out.println("Saving:");
        Composite sav = compositeRepository.save(composite);
        System.out.println("sav=" + sav);

        System.out.println("Finding by id:");
        Composite ret = compositeRepository.findById(id).orElse(null);
        System.out.println("ret=" + ret);

        System.out.println("Finding all by tenant:");
        Composite example = new Composite();
        Composite.CompositeId tId = new Composite.CompositeId();
        tId.setTenant("Tenant-X");
        example.setCompositeId(tId);
        List<Composite> list = compositeRepository.findAll(Example.of(example));
        System.out.println("list=" + list);
    }
}
