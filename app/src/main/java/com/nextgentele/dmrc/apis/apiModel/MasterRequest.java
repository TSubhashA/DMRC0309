package com.nextgentele.dmrc.apis.apiModel;

public class MasterRequest {

        String channelId;
            String tokenId;
            MasterRequestPayload payload;

    public MasterRequest(String channelId, String tokenId, MasterRequestPayload payload) {
        this.channelId = channelId;
        this.tokenId = tokenId;
        this.payload = payload;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public MasterRequestPayload getPayload() {
        return payload;
    }

    public void setPayload(MasterRequestPayload payload) {
        this.payload = payload;
    }
}
