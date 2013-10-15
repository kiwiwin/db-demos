package org.kiwi.spring.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.collect.ImmutableList.of;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-test.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PeopleIntegrationTest {
    @Autowired
    private PeopleDao peopleDao;

    @Before
    public void setUp() throws Exception {
        peopleDao.clear();
    }

    @Test
    public void should_success_add_a_people() {
        peopleDao.insertPeople(new People("firstName", "lastName"));
        assertThat(peopleDao.getPeopleCount(), is(1));
    }

    @Test
    public void should_success_add_two_people() {
        peopleDao.insertPeoples(
                of(new People("firstName1", "lastName1"),
                   new People("firstName2", "lastName2")));
        assertThat(peopleDao.getPeopleCount(), is(2));
    }
}
