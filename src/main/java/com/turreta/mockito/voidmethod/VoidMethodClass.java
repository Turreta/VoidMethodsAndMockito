package com.turreta.mockito.voidmethod;

public class VoidMethodClass {

    private PersonService service;

    /**
     * @return the service
     */
    public PersonService getService() {
        return service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(PersonService service) {
        this.service = service;
    }

    /**
     * The main logic or behavior to test using Mockito
     * 
     * @param record
     */
    public void process(PersonRecord record) {
        if (record.getId() == null || record.getStatus() == null) {
            return;
        }

        if (record.getStatus().equalsIgnoreCase("UNDERAGED")) {
            service.processUnderAged(record);
        } else {
            service.processLegalAged(record);
        }
    }
}
