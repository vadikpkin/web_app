package com.epam.dao;

import com.epam.models.Ad;
import java.util.List;

public interface AdDaoInterface {

    Ad getAd(String id);

    List<Ad> getAllAds();

    List<Ad> getAllByPersonId(int id);

    boolean insertAd(Ad ad);

    boolean deleteAd(String id);

}
