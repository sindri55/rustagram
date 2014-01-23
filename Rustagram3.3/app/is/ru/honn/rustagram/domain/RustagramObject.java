/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.domain;

import java.util.Date;

/**
 * Provides common functionality and properties for all Rustagram objects.
 */
public class RustagramObject {
    protected String creatorUsername;
    protected Date created;

    public RustagramObject() {
    }

    public RustagramObject(String creatorUsername) {
        this.creatorUsername = creatorUsername;
        this.created = new Date();
    }

    public RustagramObject(String creatorUsername, Date created) {
        this.creatorUsername = creatorUsername;
        this.created = created;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
