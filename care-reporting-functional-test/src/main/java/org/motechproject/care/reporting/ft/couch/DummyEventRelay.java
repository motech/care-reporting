package org.motechproject.care.reporting.ft.couch;


import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.EventRelay;
import org.springframework.stereotype.Component;

@Component
public class DummyEventRelay implements EventRelay {
    @Override
    public void sendEventMessage(MotechEvent motechEvent) {
        //Do nothing.
    }
}
