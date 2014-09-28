import au.com.privitar.core.Transaction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.joda.time.DateTime;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Andrew Todd on 26/09/2014.
 */
public class TransactionTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serialiseToJSON() throws Exception {

        assertThat(MAPPER.readValue(fixture("fixtures/transaction.json"), JsonNode.class));

        final Transaction transaction = new Transaction();
        transaction.setInvestment("IOOF MultiMix Growth Trust");
        transaction.setTransactionDate(new DateTime().withDate(2014, 9, 21));
        transaction.setTransactionType("Buy - Switch");
        transaction.setValue(24740.87);

        System.out.println(fixture("fixtures/transaction.json"));
        //assertThat(MAPPER.writeValueAsString(transaction)).isEqualTo(fixture("fixtures/transaction.json"));
    }
}
