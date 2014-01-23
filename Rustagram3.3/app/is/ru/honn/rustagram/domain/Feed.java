/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.domain;


import java.util.Date;
import java.util.List;




public class Feed extends Image {
    protected String creatorDisplayName;
    protected List<String> likers;
    protected List<Comment> comments;


    public Feed() {
    }

    public Feed(int id, String creator, String url, String description) {
        super(id, creator, url, description);
    }

    public Feed(String creator, String url, String description) {
        super(creator, url, description);

    }

    public Feed(int id, String creator, Date created, String url, String description) {
        super(id, creator, created, url, description);
    }

    public Feed(Image i) {
        super(i.getId(), i.getCreator(), i.getUrl(), i.getDescription());
    }


    public String getCreatorDisplayName() {
        return creatorDisplayName;
    }

    public void setCreatorDisplayName(String creatorDisplayName) {
        this.creatorDisplayName = creatorDisplayName;
    }

    public List<String> getLikers() {
        return likers;
    }

    public void setLikers(List<String> likers) {
        this.likers = likers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}
