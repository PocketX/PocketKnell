package org.pocketx.knell.domain;

public interface BirthdayManager {

    void set(Birthday birthday);

    Birthday get();

    boolean exist();
}
