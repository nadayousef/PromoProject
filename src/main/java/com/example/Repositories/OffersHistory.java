package com.example.Repositories;

import com.example.Models.History;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OffersHistory extends CrudRepository<History,String> {
    @Modifying
    @Query("update History set  = false where u.lastLoginDate < :date")
    void updateHistoryByCapping(@Param("capping") int capping);

}
