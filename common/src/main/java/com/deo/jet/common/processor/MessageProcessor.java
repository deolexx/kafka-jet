package com.deo.jet.common.processor;

import com.deo.jet.common.messages.Message;

public interface MessageProcessor <T extends Message> {
    void process(String jsonMessage);
}
