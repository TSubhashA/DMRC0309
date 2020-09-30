package com.nextgentele.dmrc.lib;

public interface CardLibInterface {

    /**
     * This function will run till it gets DESfire card with our app installed
     *
     * @return UID of the card
     */
    void findCard();

    /**
     * Stop the unending loop of fin the card, which was initiated with findCard() function.
     */
    void terminateCardFinder();

    /**Will get the current card balance. Will Return -1 in case of any error*/
    void getCardBal();

    /**Will credit the amount as passed in the paramter and return the final amount. Will Return -1 in case of any error*/
    void creditCardBal(int amount);

    /**Will debit the amount as passed in the paramter and return the final amount. Will Return -1 in case of any error*/
    void debitCardBal(int amount);

    /**
     * Must be called in on_destroy() function of the calling Activity
     */
    void deregister();
}
