package hello;

import hello.composite.Composite;
import hello.composite.CompositeId;
import hello.composite.CompositeRepository;
import hello.mixed.EntityA;
import hello.mixed.EntityARepository;
import hello.mixed.EntityB;
import hello.mixed.EntityBRepository;
import hello.simple.Simple;
import hello.simple.SimpleRepository;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MongoTest {

    private static final String TENANT = "tenant-x";

    @Autowired
    private EntityARepository aRepository;

    @Autowired
    private EntityBRepository bRepository;

    @Autowired
    private SimpleRepository simpleRepository;

    @Autowired
    private CompositeRepository compositeRepository;

    @Test
    public void testMixedCollection() {
        EntityA a1 = new EntityA();
        a1.setTenant(TENANT);
        a1.setName("a1-Name");

        EntityB b1 = new EntityB();
        b1.setTenant(TENANT);
        b1.setColor("b1-RED");

        aRepository.save(a1);
        bRepository.save(b1);

        List<EntityA> results = aRepository.findAllByTenant(TENANT);
        Assert.assertEquals("Only entityA should be returned from entityA repository.", 1, results.size());
    }

    @Test
    public void testSimple() {
        Simple simple = new Simple();
        simple.setName("Simple name");

        simpleRepository.save(simple);
    }

    @Test
    public void testMixedFindByTenant() {

    }

    @Test
    public void testComposite() {
        Composite composite = new Composite();
        composite.setName("Composite name");

        CompositeId id = new CompositeId();
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
        CompositeId tId = new CompositeId();
        tId.setTenant("Tenant-X");
        example.setCompositeId(tId);
        List<Composite> list = compositeRepository.findAll(Example.of(example));
        System.out.println("list=" + list);
    }

}
