package com.epam.dao;

import com.epam.dbconnect.ConnectionFactory;
import com.epam.models.Ad;
import com.epam.models.Category;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdDao implements AdDaoInterface {

    @Override
    public Ad getAd(String id) {
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM ads WHERE id=?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ad ad = new Ad();
                    ad.setPerson_id(rs.getInt("person_id"));
                    ad.setId(rs.getString("id"));
                    ad.setTitle(rs.getString("title"));
                    ad.setBody(rs.getString("body"));
                    ad.setCategory(Category.valueOf(rs.getString("category")));
                    ad.setPhone(rs.getString("phone"));
                    ad.setDateOfCreation(LocalDate.parse(rs.getString("dateofcreation")));
                    PersonDao personDao = new PersonDao();
                    ad.setPerson(personDao.getPerson(rs.getInt("person_id")));
                    return ad;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ad> getAllAds() {
        try (Connection cn = ConnectionFactory.getConnection();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ads")) {

            List<Ad> adList = new ArrayList<>();

            while (rs.next()) {
                Ad ad = new Ad();
                ad.setPerson_id(rs.getInt("person_id"));
                ad.setId(rs.getString("id"));
                ad.setTitle(rs.getString("title"));
                ad.setBody(rs.getString("body"));
                ad.setCategory(Category.valueOf(rs.getString("category")));
                ad.setPhone(rs.getString("phone"));
                ad.setDateOfCreation(LocalDate.parse(rs.getString("dateofcreation")));
                PersonDao personDao = new PersonDao();
                ad.setPerson(personDao.getPerson(rs.getInt("person_id")));
                adList.add(ad);
            }

            return adList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ad> getAllByPersonId(int id) {
        try (Connection cn = ConnectionFactory.getConnection();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ads WHERE person_id=" + id)) {

            List<Ad> adList = new ArrayList<>();

            while (rs.next()) {
                Ad ad = new Ad();
                ad.setPerson_id(rs.getInt("person_id"));
                ad.setId(rs.getString("id"));
                ad.setTitle(rs.getString("title"));
                ad.setBody(rs.getString("body"));
                ad.setCategory(Category.valueOf(rs.getString("category")));
                ad.setPhone(rs.getString("phone"));
                ad.setDateOfCreation(LocalDate.parse(rs.getString("dateofcreation")));
                PersonDao personDao = new PersonDao();
                ad.setPerson(personDao.getPerson(rs.getInt("person_id")));
                adList.add(ad);
            }

            return adList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertAd(Ad ad) {
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO ads (person_id, id, title, body, category, " +
                     "phone, dateofcreation) VALUES(?, ?, ?, ?, ?, ?, ?);")) {
            ps.setInt(1, ad.getPerson_id());
            ps.setString(2, ad.getId());
            ps.setString(3, ad.getTitle());
            ps.setString(4, ad.getBody());
            ps.setString(5, String.valueOf(ad.getCategory()));
            ps.setString(6, ad.getPhone());
            ps.setDate(7, Date.valueOf(ad.getDateOfCreation()));

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
    public boolean deleteAd(String id) {

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM ads WHERE id=?")) {

            ps.setString(1, id);

            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
