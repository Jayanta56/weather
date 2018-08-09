package com.example.weather.dao;

import com.example.weather.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDAO extends CrudRepository<Location, Long> {

    @Query(value = "Select x.CityName  as  City from ( " +
            " Select upper(cityName) as CityName, count(*)  as total from  CityDetails " +
            " group by upper(cityName)  order by 2 desc ) x  limit 5 ", nativeQuery = true)
    public List<String> findTopFiveCities() ;

    @Query(value = "Select x.CityName  as  City from ( " +
            " Select upper(cityName) as CityName, count(*)  as total from  CityDetails " +
            " group by upper(cityName)  order by 2 asc  ) x  limit 5 ", nativeQuery = true)
    public List<String> findBottomFiveCities() ;

    @Query(value = "Select  upper(cityName) as City from CityDetails order by id desc  limit 5", nativeQuery = true)
    public List<String> findRecentFiveCities() ;

}
