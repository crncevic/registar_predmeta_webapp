package integration.test;

import domain.Katedra;
import java.io.File;
import javax.inject.Inject;
import logic.KatedraLogic;
import org.apache.maven.Maven;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
@RunWith(Arquillian.class)
public class KatedraTest {

//    @PersistenceContext(unitName = "SRP_PU")
//    public EntityManager em;
//     @EJB(beanName = "KatedraLogic")
//     public KatedraLogic katedraLogic;
//    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(Katedra.class, KatedraLogic.class,GenericRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
//    @Inject
//    public KatedraLogic katedraLogic;

    @Test
    public void IsThereAnyKatedra() {

//        Assert.assertNotNull(katedraLogic);
    }
}
