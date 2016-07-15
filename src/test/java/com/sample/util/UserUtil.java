package com.sample.util;

import com.sample.domain.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    private static final String ID = "id";
    private static final String PASSWORD = "password";

    private UserUtil() {
    }

    public static UserProfile createUser() {
        return new UserProfile(ID, PASSWORD);
    }

    public static List<UserProfile> createUserList(int howMany) {
        List<UserProfile> userProfileList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            userProfileList.add(new UserProfile(ID + "#" + i, PASSWORD));
        }
        return userProfileList;
    }

}
