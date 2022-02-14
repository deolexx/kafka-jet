package com.deo.jet.office.listener.processors;

import com.deo.jet.common.messages.OfficeStateMessage;
import com.deo.jet.common.processor.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {
    @Override
    public void process(String jsonMessage) {

    }
}
