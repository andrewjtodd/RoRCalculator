import org.joda.time.*;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * Created by Andrew Todd on 3/10/2014.
 */
public class WilliamTest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(WilliamTest.class);

    @Test
    public void getWilliamsDays() {
        DateTime born = new DateTime(1953, 1, 26, 0, 0, 0);

        Days boy = Days.daysBetween(born, new DateTime());
        Hours hours = boy.toStandardHours();
        Minutes minutes = boy.toStandardMinutes();
        Seconds seconds = boy.toStandardSeconds();

        logger.debug("William is a naughty boy");
        logger.debug("");

        logger.debug("William was born " + boy.getDays() + " days ago!!!");
        logger.debug("William was born " + hours.getHours() + " hours ago.");
        logger.debug("William was born " + minutes.getMinutes() + " minutes ago.");
        logger.debug("William was born " + seconds.getSeconds() + " seconds ago.");
    }
}
