package com.nextgentele.dmrc.lib;

public interface ResultInterface {
    enum CMD_LIST {NONE, FIND_CARD, CREDIT, DEBIT, GET_BAL};
    void onResult(CMD_LIST currCmd, Object result);
}
