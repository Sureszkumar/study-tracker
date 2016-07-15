package com.sample.service;

import com.sample.domain.UserProfile;
import com.sample.repository.UserRepository;
import com.sample.util.UserUtil;
import com.sample.service.exception.UserServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldSaveNewUser_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedUserShouldBeReturned() throws Exception {
        final UserProfile savedUserProfile = stubRepositoryToReturnUserOnSave();
        final UserProfile userProfile = UserUtil.createUser();
        final UserProfile returnedUserProfile = userService.save(userProfile);
        // verify repository was called with user
        verify(userRepository, times(1)).save(userProfile);
        assertEquals("Returned user should come from the repository", savedUserProfile, returnedUserProfile);
    }

    private UserProfile stubRepositoryToReturnUserOnSave() {
        UserProfile userProfile = UserUtil.createUser();
        when(userRepository.save(any(UserProfile.class))).thenReturn(userProfile);
        return userProfile;
    }

    //@Test
    public void shouldSaveNewUser_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingUser();
        try {
            userService.save(UserUtil.createUser());
            fail("Expected exception");
        } catch (UserServiceException ignored) {
        }
        verify(userRepository, never()).save(any(UserProfile.class));
    }

    private void stubRepositoryToReturnExistingUser() {
        final UserProfile userProfile = UserUtil.createUser();
        when(userRepository.findOne(userProfile.getId())).thenReturn(userProfile);
    }

    @Test
    public void shouldListAllUsers_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingUsers(10);
        Collection<UserProfile> list = userService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(userRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingUsers(int howMany) {
        when(userRepository.findAll()).thenReturn(UserUtil.createUserList(howMany));
    }

    @Test
    public void shouldListAllUsers_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingUsers(0);
        Collection<UserProfile> list = userService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

}
