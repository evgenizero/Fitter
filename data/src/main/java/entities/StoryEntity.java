package entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
@Table(name = "Stories")
public class StoryEntity extends Model {
    @Column(name = "registration_user")
    public User registrationUser;

    @Column(name = "created_by_user")
    public User createdByUser;

    @Column(name = "createdDate")
    public String createdDate;

    @Column(name = "text")
    public String text;

    @Column(name = "attachment_urls")
    public List<String> attachmentUrls;

}
