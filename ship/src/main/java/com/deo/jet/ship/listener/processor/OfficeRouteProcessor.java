package com.deo.jet.ship.listener.processor;

import com.deo.jet.common.bean.Route;
import com.deo.jet.common.messages.OfficeRouteMessage;
import com.deo.jet.common.processor.MessageConverter;
import com.deo.jet.common.processor.MessageProcessor;
import com.deo.jet.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("OFFICE_ROUTE")
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {

    private final BoardProvider boardProvider;
    private final MessageConverter messageConverter;

    @Override
    public void process(String jsonMessage) {
        OfficeRouteMessage msg = messageConverter.extractMessage(jsonMessage, OfficeRouteMessage.class);
        Route route = msg.getRoute();
        boardProvider.getBoards().stream().filter(board -> board.noBusy() && route.getBoardName().equals(board.getName()))
                .findFirst()
                .ifPresent(board -> {
                    board.setRoute(route);
                    board.setBusy(true);
                    board.setLocation(null);

                });

    }
}
