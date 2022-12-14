package com.ivan.labdb4.controller;

import com.ivan.labdb4.service.HighLightService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
public class HighlightController {

    private final HighLightService highLightService;

    public HighlightController(HighLightService highLightService) {
        this.highLightService = highLightService;
    }

    @PostMapping()
    public ResponseEntity<String> saveHighLight(@RequestParam("file") MultipartFile file,
                                                @RequestParam("video-name") String videoName,
                                                @RequestParam("movie-name") String movieName) {
        try {
            highLightService.saveHighlightMetainfo(file, videoName, movieName);
            return ResponseEntity.ok("Video saved");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Some error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getHighLightById(@PathVariable Integer id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(highLightService.getHighlightMetainfo(id).getData()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Integer>> getAllHighLightsIds() {
        return ResponseEntity.ok(highLightService.getAllHighLightsIds());
    }
}
