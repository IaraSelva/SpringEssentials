package com.devdojo.essentials.controller;

import com.devdojo.essentials.domain.Anime;
import com.devdojo.essentials.service.AnimeService;
import com.devdojo.essentials.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        log.info(dateUtils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())+" Retornando anime por ID");
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime){
        log.info("Cadastrando novo anime: "+anime.getName());
        return new ResponseEntity<Anime>(animeService.save(anime),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Anime> replace (@RequestBody Anime anime){
        animeService.replace(anime);
        return new ResponseEntity<Anime>(anime, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
