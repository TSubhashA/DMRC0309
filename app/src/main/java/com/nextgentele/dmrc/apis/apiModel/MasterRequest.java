package com.nextgentele.dmrc.apis.apiModel;

public class MasterRequest {

        int channelId;
            String tokenId;
            MasterRequestPayload payload;

    public MasterRequest(int channelId, String tokenId, MasterRequestPayload payload) {
        this.channelId = channelId;
        this.tokenId = tokenId;
        this.payload = payload;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
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
