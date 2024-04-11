package com.yummyfoods.spring.dao;

import com.yummyfoods.spring.form.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDAOImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private UserDAOImpl userDao;

    @Test
    public void testListUserReturnsAnEmptyListIfNoUsers() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Query query = mock(Query.class);
        when(session.createQuery("from User")).thenReturn(query);
        when(query.list()).thenReturn(new ArrayList<>());
        assertThat(userDao.listUser(), is(empty()));
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).createQuery("from User");
        verify(query, times(1)).list();
    }

    @Test
    public void testListUserReturnsAListWithUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        user1.setUserName("user1");
        user2.setUserName("user2");
        users.add(user1);
        users.add(user2);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Query query = mock(Query.class);
        when(session.createQuery("from User")).thenReturn(query);
        when(query.list()).thenReturn(users);
        assertThat(userDao.listUser(), is(not(empty())));
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).createQuery("from User");
        verify(query, times(1)).list();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userDao.addUser(user);
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).save(user);
    }

    @Test
    @Disabled
    public void testDeleteUser() {
        // The method is empty, so there is nothing to test
    }

    @Test
    public void testGetUserByIdReturnsTheCorrectUser() {
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        user.setUserId("user1");
        user.setUserName("user1");
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Query query = mock(Query.class);
        when(session.createQuery("from User where userId='user1'")).thenReturn(query);
        when(query.list()).thenReturn(users);
        assertThat(userDao.getUserById("user1"), is(user));
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).createQuery("from User where userId='user1'");
        verify(query, times(1)).list();
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userDao.update(user);
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).update(user);
    }

}