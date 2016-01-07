package com.fitter.mapper;

import com.fitter.model.UserModel;

import entities.User;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class UserModelToUser {
    public static User transform(UserModel userModel) {
        User user = null;

        if (userModel != null) {
            user = new User();
            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            user.setEmail(userModel.getEmail());
        }

        return user;
    }
}
