package com.apsoft.parser.controllers;

import com.apsoft.parser.entities.ParagraphNode;
import com.apsoft.parser.parsers.FileParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
public class ParagraphController {
    private FileParser fileParser;

    public ParagraphController(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    @PostMapping("/api/paragraphs")
    private List<ParagraphNode> getParagraphsFromFile(@RequestParam("file")MultipartFile file){
        try {
            return fileParser.parseParagraphNodes(file);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Invalid file input",e);
        }
    }
}
