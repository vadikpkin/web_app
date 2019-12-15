package com.epam.dao;

import com.epam.dbconnect.ConnectionFactory;
import com.epam.models.Person;
import com.epam.models.Type;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonDao implements PersonDaoInterface {
    @Override
    public Person getPerson(int id) {

        Connection cn = ConnectionFactory.getConnection();

        try {
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM persons WHERE id=" + id);

            while (rs.next()) {
                return new Person(rs.getString("name"), rs.getString("surname"), rs.getString("email"),
                        Type.valueOf(rs.getString("type")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Person> getAllPersons() {

        Connection cn = ConnectionFactory.getConnection();

        try {
            Statement stmt = cn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM persons");

            Set<Person> personSet = new HashSet<>();

            while (rs.next()) {
                Person person = new Person(rs.getString("name"), rs.getString("surname"), rs.getString("email"),
                        Type.valueOf(rs.getString("type")));
                personSet.add(person);
            }

            return personSet;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertPerson(Person person) {

        Connection cn = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO persons (name, surname, email, type) " +
                    "VALUES (?, ?, ?, ?)");
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getEmail());
            ps.setString(4, String.valueOf(person.getTypeOfCategory()));

            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updatePerson(Person person, int id) {

        Connection cn = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE persons SET name=?, surname=?, email=?, type=? WHERE id=?");
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getEmail());
            ps.setString(4, String.valueOf(person.getTypeOfCategory()));
            ps.setInt(5, id);

            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePerson(int id) {

        Connection cn = ConnectionFactory.getConnection();

        try {
            Statement stmt = cn.createStatement();

            int i = stmt.executeUpdate("DELETE FROM persons WHERE id=" + id);

            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public int getId(Person person) {
        Connection cn = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = cn.prepareStatement("SELECT id FROM persons WHERE name=? AND surname=? AND email=?" +
                    " AND type=?");
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getEmail());
            ps.setString(4, String.valueOf(person.getTypeOfCategory()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int a = rs.getInt("id");
                return a;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
