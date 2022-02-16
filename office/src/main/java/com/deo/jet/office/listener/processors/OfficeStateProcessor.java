package com.deo.jet.office.listener.processors;

import com.deo.jet.common.messages.AirPortStateMessage;
import com.deo.jet.common.messages.OfficeStateMessage;
import com.deo.jet.common.processor.MessageConverter;
import com.deo.jet.common.processor.MessageProcessor;
import com.deo.jet.office.provider.AirportsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {
    private final MessageConverter messageConverter;
    private final AirportsProvider airportsProvider;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        airportsProvider.getPorts().forEach(airPort -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirPortStateMessage(airPort)));
        });
    }
}
