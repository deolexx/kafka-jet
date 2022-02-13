package com.deo.jet.common.messages;

import com.deo.jet.common.bean.Source;
import com.deo.jet.common.bean.Type;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {
    protected Type type;
    protected Source source;

    public String getCode() {
        return source.name() + "_" + type.name();
    }
}
