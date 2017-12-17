package org.pocketx.knell.domain;

/**
 * @author wd
 */
public interface BirthdayManager {

    void set(Birthday birthday);

    Birthday get();

    boolean exist();

    /**
     * 删除生日数据
     */
    void deleteBirthday();
}
