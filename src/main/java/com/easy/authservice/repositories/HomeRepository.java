package com.easy.authservice.repositories;

import com.easy.authservice.models.Home;
import com.easy.authservice.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    @Query(nativeQuery = true, value = "SELECT h.* FROM home h")
    public Collection<Home> find();

    @Query(nativeQuery = true, value = "SELECT pg_sleep(3)")
    public void execSleep();
}
