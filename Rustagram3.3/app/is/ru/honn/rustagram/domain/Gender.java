/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.domain;

/**
 * Gender enumeration.
 */
public enum Gender {
    MALE, FEMALE, UNSPECIFIED;

    public static Gender fromString(String name) {
        Gender gender;
        try {
            gender = Gender.valueOf(name);
        } catch (Throwable t) {
            gender = UNSPECIFIED;
        }
        return gender;
    }
}
