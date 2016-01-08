package entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
@Table(name = "Attachments")
public class AttachmentEntity extends Model {
    @Column(name = "server_url")
    String serverUrl;

    @Column(name = "story")
    Story story;
}
