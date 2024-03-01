package com.github.kiarttantasi.lolapi.controllers.v1;

import com.github.kiarttantasi.lolapi.models.Response.MatchDetailV1;
import com.github.kiarttantasi.lolapi.models.Response.MatchesResponseV1;
import com.github.kiarttantasi.lolapi.services.MatchService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@Slf4j
public class MatchHistoryController {

    private final MatchService matchService;

    public MatchHistoryController(MatchService matchHistoryService) {
        this.matchService = matchHistoryService;
    }

    @GetMapping
    public ResponseEntity<MatchesResponseV1> getMatches(@RequestParam String gameName, @RequestParam String tagLine) {
        try {
            final List<MatchDetailV1> matches = matchService.getMatches(gameName, tagLine);
            if (matches == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(new MatchesResponseV1(matches));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
