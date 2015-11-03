package com.turreta.mockito.voidmethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VoidMethodClassTest {

    @Mock
    private PersonRecord record;

    @Mock
    private PersonService service;

    private VoidMethodClass voidMethodClass = new VoidMethodClass();

    private InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        voidMethodClass.setService(service);
        inOrder = Mockito.inOrder(record, service);
    }

    @Test
    public void testProcess_record_with_null_id() {
        Mockito.when(record.getId()).thenReturn(null);
        voidMethodClass.process(record);

        // We check if getId() was invoked.
        Mockito.verify(record, Mockito.times(1)).getId();

        // We check if only getId() was invoked, and not getStatus()
        Mockito.verify(record, Mockito.only()).getId();

        // No methods invoked in service object
        Mockito.verifyZeroInteractions(service);
    }

    @Test
    public void testProcess_record_with_null_status() {
        Mockito.when(record.getId()).thenReturn("1000");
        Mockito.when(record.getStatus()).thenReturn(null);

        voidMethodClass.process(record);

        // We check if getStatus was invoked
        inOrder.verify(record, Mockito.times(1)).getId();
        inOrder.verify(record, Mockito.times(1)).getStatus();

        // No methods invoked in service object
        Mockito.verifyZeroInteractions(service);
    }

    @Test
    public void testProcess_record_record_legal_aged() {
        Mockito.when(record.getId()).thenReturn("1000");
        Mockito.when(record.getStatus()).thenReturn("LEGALAGED");

        voidMethodClass.process(record);

        // We check if getStatus was invoked
        inOrder.verify(record, Mockito.times(1)).getId();
        inOrder.verify(record, Mockito.times(2)).getStatus();

        Mockito.verify(service, Mockito.only()).processLegalAged(Matchers.any(PersonRecord.class));

    }

    @Test
    public void testProcess_record_record_under_aged() {
        Mockito.when(record.getId()).thenReturn("1000");
        Mockito.when(record.getStatus()).thenReturn("UNDERAGED");

        voidMethodClass.process(record);

        // We check if getStatus was invoked
        inOrder.verify(record, Mockito.times(1)).getId();
        inOrder.verify(record, Mockito.times(2)).getStatus();

        Mockito.verify(service, Mockito.only()).processUnderAged(Matchers.any(PersonRecord.class));

    }

    @Test
    public void testProcess_record_record_not_undefined_status() {
        Mockito.when(record.getId()).thenReturn("1000");
        Mockito.when(record.getStatus()).thenReturn("UNDEFINED STATUS");

        voidMethodClass.process(record);

        // We check if getStatus was invoked
        inOrder.verify(record, Mockito.times(1)).getId();
        inOrder.verify(record, Mockito.times(2)).getStatus();

        Mockito.verify(service, Mockito.only()).processLegalAged(Matchers.any(PersonRecord.class));

    }
}
