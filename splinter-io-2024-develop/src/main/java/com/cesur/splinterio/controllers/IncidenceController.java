package com.cesur.splinterio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesur.splinterio.models.Incidence;
import com.cesur.splinterio.models.dtos.IncidenceDto;
import com.cesur.splinterio.services.IncidenceService;

@RestController
@RequestMapping("/incidence")
public class IncidenceController {

    @Autowired
    IncidenceService incidenceService;

    @GetMapping("/listByUser")
    public ResponseEntity<List<Incidence>> getIncidencesByUsername(@RequestParam(name = "username") String username) {
        try {
            return ResponseEntity.ok(incidenceService.getIncidencesByUserName(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> storeIncidence(@RequestBody IncidenceDto entity) {
        try {
            incidenceService.storeIncidence(entity);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteIncidence(@RequestParam(name = "incidence_id") Long id) {
        try {
            incidenceService.deleteIncidence(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PatchMapping("/partial/{id}")
    public ResponseEntity<Void> partialUpdateIncidence(@PathVariable(name = "id") Long id, @RequestBody IncidenceDto incidenceDto) {
        try {
            incidenceService.updatePartialIncidence(id, incidenceDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/full")
    public ResponseEntity<Void> fullUpdateIncidence(@RequestBody Incidence incidenceDto) {
        try {
            incidenceService.updateAllIncidence(incidenceDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
