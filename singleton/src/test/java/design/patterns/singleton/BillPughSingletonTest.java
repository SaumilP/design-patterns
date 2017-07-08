package design.patterns.singleton;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BillPughSingletonTest {

    @Test
    public void shouldSuccessfullyCreateBillPuggedSingleton() throws Exception {
        BillPughSingleton instance = BillPughSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }
}