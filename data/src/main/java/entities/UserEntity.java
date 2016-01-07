package entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
@Table(name = "Users")
public class UserEntity extends Model {
    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;
}
