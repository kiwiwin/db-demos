package org.kiwi.spring.jdbc;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;

public class PeopleDao {
    private static final String INSERT_PEOPLE_QUERY = "INSERT INTO PEOPLE VALUES(?,?)";
    private static final String COUNT_PEOPLE_QUERY = "SELECT COUNT(1) FROM PEOPLE";
    private static final String DELETE_ALL_PEOPLE_QUERY = "DELETE FROM PEOPLE";
    private JdbcTemplate jdbcTemplate;

    @Required
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPeople(People people) {
        jdbcTemplate.update(INSERT_PEOPLE_QUERY, people.getFirstName(), people.getLastName());
    }

    public void insertPeoples(List<People> peoples) {
        jdbcTemplate.batchUpdate(INSERT_PEOPLE_QUERY, transformToObjects(peoples));
    }

    private List<Object[]> transformToObjects(List<People> peoples) {
        return from(peoples).transform(new Function<People, Object[]>() {
            @Override
            public Object[] apply(People people) {
                return new Object[]{people.getFirstName(), people.getLastName()};
            }
        }).toList();
    }

    public int getPeopleCount() {
        return (Integer) jdbcTemplate.queryForObject(COUNT_PEOPLE_QUERY, Integer.class);
    }

    public void clear() {
        jdbcTemplate.update(DELETE_ALL_PEOPLE_QUERY);
    }
}
