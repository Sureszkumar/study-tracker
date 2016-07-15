package com.sample.controller;

import com.sample.util.UserUtil;
import com.sample.domain.UserProfile;
import com.sample.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService);
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final UserProfile savedUserProfile = stubServiceToReturnStoredUser();
        final UserProfile userProfile = UserUtil.createUser();
        UserProfile returnedUserProfile = userController.createUser(userProfile);
        // verify user was passed to UserService
        verify(userService, times(1)).save(userProfile);
        assertEquals("Returned user should come from the service", savedUserProfile, returnedUserProfile);
    }

    private UserProfile stubServiceToReturnStoredUser() {
        final UserProfile userProfile = UserUtil.createUser();
        when(userService.save(any(UserProfile.class))).thenReturn(userProfile);
        return userProfile;
    }


    @Test
    public void shouldListAllUsers() throws Exception {
        stubServiceToReturnExistingUsers(10);
        Collection<UserProfile> userProfiles = userController.listUsers();
        assertNotNull(userProfiles);
        assertEquals(10, userProfiles.size());
        // verify user was passed to UserService
        verify(userService, times(1)).getList();
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userService.getList()).thenReturn(UserUtil.createUserList(howMany));
    }

}
