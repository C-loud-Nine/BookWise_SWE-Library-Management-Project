package com.example.bookwisesweproject.patterns.observer;

import com.example.bookwisesweproject.User_Dash;
import com.example.bookwisesweproject.User_Notification;
import com.google.firebase.database.DatabaseReference;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UserTest {
    @Mock
    User_Notification userActivity;
    @Mock
    User_Dash userDash;
    @Mock
    DatabaseReference mref;
    @InjectMocks
    User user;

    /*@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdate() throws Exception {
        user.update();
    }*/
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme