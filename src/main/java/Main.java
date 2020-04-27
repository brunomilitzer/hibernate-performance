import br.com.brunomilitzer.hibernate.Address;
import br.com.brunomilitzer.hibernate.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger( Main.class );

    public static void main( String[] args ) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "my-persistence" );
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        InsertPerson( em );

        em.getTransaction().commit();
        em.close();
    }

    private static void InsertPerson( EntityManager em ) {

        Address a = Address.builder().number( 376L ).street( "Rua Fernando Ferreira de Mello" ).city( "Florianópolis" ).zipCode( "88080-360" ).build();

        Person p1 = Person.builder().firstName( "Vanessa" ).lastName( "de Garcez" ).dateOfBirth( getDOB( "1979-10-07" ) ).build();
        p1.setAddress( a );

        Person p2 = Person.builder().firstName( "Tales" ).lastName( "de Garcez de Garcez Coelho" ).dateOfBirth( getDOB( "2003-04-17" ) ).build();
        p2.setAddress( a );

        Person p3 = Person.builder().firstName( "Bruno" ).lastName( "Militzer" ).dateOfBirth( getDOB( "1978-10-08" ) ).build();
        p3.setAddress( a );

        List<Person> personList = new ArrayList<>();

        personList.add( p1 );
        personList.add( p2 );
        personList.add( p3 );

        personList.forEach( em::persist );
    }

    private static Date getDOB(String date) {

        return Date.from(LocalDate.parse( date , DateTimeFormatter.ISO_DATE ).atStartOfDay().atZone( ZoneId.systemDefault() ).toInstant());
    }
}
