package com.nextgentele.dmrc.apis.apiModel;

import java.util.List;

public class MasterResponse {


        int status;
            String message;
            List<MasterResponsePayload> payload;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<MasterResponsePayload> getPayload() {
        return payload;
    }
}
