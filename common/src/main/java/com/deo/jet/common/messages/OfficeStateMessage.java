package com.deo.jet.common.messages;

import com.deo.jet.common.bean.Route;
import com.deo.jet.common.bean.Source;
import com.deo.jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeStateMessage extends Message {
    private Route route;

    public OfficeStateMessage() {
        this.source = Source.OFFICE;
        this.type = Type.STATE;
    }

    public OfficeStateMessage(Route val) {
        this();
        this.route = val;
    }
}
