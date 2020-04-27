import br.com.brunomilitzer.hibernate.Person;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TestStatistics {

    private static final Logger logger = LoggerFactory.getLogger( TestStatistics.class );

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory( "my-persistence" );

    @Test
    public void logStatistic() {
        logger.info( "...logStatistics..." );

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Person> persons = em.createQuery( "SELECT p FROM Person p", Person.class ).getResultList();

        for ( Person p: persons) {
            logger.info( p.getFirstName() + " " + p.getLastName());
        }

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void statisticsAPI() {
        logger.info( "...statisticsAPI..." );

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Person> persons = em.createQuery( "SELECT p FROM Person p", Person.class ).getResultList();

        for ( Person p: persons) {
            logger.info( p.getFirstName() + " " + p.getLastName());
        }

        SessionFactory sessionFactory = emf.unwrap( SessionFactory.class );
        Statistics stats = sessionFactory.getStatistics();

        long queryCount = stats.getQueryExecutionCount();
        long collectionsFetchCount = stats.getCollectionFetchCount();

        logger.info("QueryCount: " + queryCount);
        logger.info("CollectionsFetchCount: " + collectionsFetchCount);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void selectPersonNames() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query q = em.createQuery( "SELECT p.firstName, p.lastName FROM Person p" );

        List<Object[]> persons = q.getResultList();

        for ( Object[] p: persons) {
            logger.info("Person " + p[0] + " " + p[1]);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void selectFromWithoutJoinFetch() {

    }

    @AfterEach
    public void close() {
        emf.close();
    }
}
