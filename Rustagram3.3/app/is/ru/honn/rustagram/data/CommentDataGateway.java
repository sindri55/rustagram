/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Comment;
import is.ruframework.data.RuDataAccess;

import java.util.List;

public interface CommentDataGateway extends RuDataAccess {
    public int addComment(Comment comment);

    public List<Comment> getCommentsOnImage(int imageId);
}
