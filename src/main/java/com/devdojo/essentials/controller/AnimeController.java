package com.devdojo.essentials.controller;

import com.devdojo.essentials.domain.Anime;
import com.devdojo.essentials.service.AnimeService;
import com.devdojo.essentials.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/animes")
public class AnimeController {

    private final DateUtils dateUtils;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list(){
        log.info(dateUtils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())+" Retornando lista de animes");
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        log.info(dateUtils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())+" Retornando anime por ID");
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime){
        log.info("Cadastrando novo anime "+anime.getName());
        return new ResponseEntity<Anime>(animeService.save(anime),HttpStatus.CREATED);
    }
}
