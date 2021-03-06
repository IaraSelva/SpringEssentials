package com.devdojo.essentials.repository;


import com.devdojo.essentials.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
