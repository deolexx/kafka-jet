package com.deo.jet.ship.listener.processor;

import com.deo.jet.common.messages.BoardStateMessage;
import com.deo.jet.common.messages.OfficeStateMessage;
import com.deo.jet.common.processor.MessageConverter;
import com.deo.jet.common.processor.MessageProcessor;
import com.deo.jet.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("OFFICE_STATE")
@Slf4j
@RequiredArgsConstructor
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageConverter messageConverter;
    private final BoardProvider boardProvider;

    @Override
    public void process(String jsonMessage) {
        boardProvider.getBoards().forEach(board -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new BoardStateMessage(board)));
        });
    }
}
