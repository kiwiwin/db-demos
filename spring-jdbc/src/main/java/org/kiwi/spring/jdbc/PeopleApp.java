package org.kiwi.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeopleApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        PeopleDao dao = (PeopleDao) context.getBean("peopleDao");

        displayCurrentTime();
        for (int i = 0; i < 10000; i++) {
            dao.insertPeople(generatePeople());
        }
        displayCurrentTime();
        dao.insertPeoples(generatePeoples());
        displayCurrentTime();

        System.out.println(dao.getPeopleCount());
        dao.clear();
        System.out.println(dao.getPeopleCount());
    }

    private static void displayCurrentTime() {
        System.out.println(new Date());
    }

    private static List<People> generatePeoples() {
        ArrayList<People> peoples = new ArrayList<People>();
        for (int i = 0; i < 10000; i++) {
            peoples.add(generatePeople());
        }
        return peoples;
    }

    private static People generatePeople() {
        return new People("xx", "yy");
    }
}
