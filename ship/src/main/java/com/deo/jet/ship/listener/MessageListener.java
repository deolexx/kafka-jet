package com.deo.jet.ship.listener;

import com.deo.jet.common.messages.Message;
import com.deo.jet.common.processor.MessageConverter;
import com.deo.jet.common.processor.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {

    private final MessageConverter messageConverter;

    @Autowired
    private Map<String, MessageProcessor<? extends Message>> processors = new HashMap<>();

    @KafkaListener(id = "BoardId", topics = "officeRoutes")
    public void radarListener(String data) {
        String code = messageConverter.extractCode(data);
        try {
            processors.get(code).process(data);
        } catch (Exception e) {
           log.info("Code:" + code+ "." +e.getLocalizedMessage());
        }
    }

}
