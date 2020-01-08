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

        try (Connection cn = ConnectionFactory.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM persons WHERE id=" + id)) {

            while (rs.next()) {
                Person person = new Person();
                person.setId(id);
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setEmail(rs.getString("email"));
                person.setTypeOfCategory(Type.valueOf(rs.getString("type")));
                return person;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Person> getAllPersons() {

        try (Connection cn = ConnectionFactory.getConnection();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM persons")) {

            Set<Person> personSet = new HashSet<>();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setEmail(rs.getString("email"));
                person.setTypeOfCategory(Type.valueOf(rs.getString("type")));
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

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO persons (name, surname, email, type) " +
                     "VALUES (?, ?, ?, ?)")) {
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
    public boolean deletePerson(int id) {

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM persons WHERE id=?")) {

            ps.setInt(1, id);

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
    public int getId(Person person) {

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT id FROM persons WHERE name=? AND surname=? AND email=?" +
                     " AND type=?")) {

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getEmail());
            ps.setString(4, String.valueOf(person.getTypeOfCategory()));

            try(ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int a = rs.getInt("id");
                    return a;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
