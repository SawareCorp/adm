package com.company.adm.service;


public interface UniqueNumbersExtService {
    String NAME = "adm_UniqueNumbersExtService";

    /**
     * Returns the next sequence value.
     *
     * @param domain    sequence identifier
     * @return          next value
     */
    long getNextNumber(String domain);

    /**
     * Returns the current value of the sequence. For some implementations
     * {@link #getNextNumber(String)} must be called at least once beforehand.
     *
     * @param domain    sequence identifier
     * @return          current value
     */
    long getCurrentNumber(String domain);

    /**
     * Set current value for the sequence.
     * Next {@link #getCurrentNumber(String)} invocation will return {@code value}
     * Next {@link #getNextNumber(String)} invocation will return {@code value + increment}
     *
     * @param domain    sequence identifier
     * @param value     value
     */
    void setCurrentNumber(String domain, long value);
}