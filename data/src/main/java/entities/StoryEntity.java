package entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
@Table(name = "Stories")
public class StoryEntity extends Model {
    @Column(name = "registration_user")
    public Long registrationUser;

    @Column(name = "created_by_user")
    public Long createdByUser;

    @Column(name = "createdDate")
    public Date createdDate;

    @Column(name = "text")
    public String text;

    public List<AttachmentEntity> attachmentEntities() {
        return getMany(AttachmentEntity.class, "story");
    }
}
