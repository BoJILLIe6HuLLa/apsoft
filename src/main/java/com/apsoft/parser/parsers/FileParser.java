package com.apsoft.parser.parsers;

import com.apsoft.parser.entities.ParagraphNode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileParser {
    private static final String WRONG_FILE_MESSAGE = "Invalid file";
    public List<ParagraphNode> parseParagraphNodes(MultipartFile file) throws IOException {
        List<ParagraphNode> paragraphNodes = new ArrayList<>();
        Scanner scanner = new Scanner(file.getInputStream());
        ParagraphNode previousNode=new ParagraphNode();
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            if(!line.startsWith("#")) {
                throw new IOException(WRONG_FILE_MESSAGE);
            }
            int nestingLevel = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#'){
                    nestingLevel++;
                    if(previousNode.getLevel()!=1 && nestingLevel-previousNode.getLevel()>1){
                        throw new IOException(WRONG_FILE_MESSAGE);
                    }
                }else{
                    break;
                }
            }
            ParagraphNode paragraph = new ParagraphNode();
            paragraph.setName(line.substring(nestingLevel).trim());
            if (nestingLevel == 1) {
                paragraphNodes.add(paragraph);
            }else {
                if (nestingLevel == previousNode.getLevel()){
                    paragraph.setParent(previousNode.getParent());
                    previousNode.getParent().getSubNodes().add(paragraph);
                }else{
                    if(nestingLevel>previousNode.getLevel()) {
                        paragraph.setParent(previousNode);
                        previousNode.getSubNodes().add(paragraph);
                    }else {
                        ParagraphNode temporaryNode=previousNode.getParent();
                        while (temporaryNode.getLevel()>=nestingLevel){
                            temporaryNode = temporaryNode.getParent();
                        }
                        paragraph.setParent(temporaryNode);
                        temporaryNode.getSubNodes().add(paragraph);
                    }
                }
            }
            paragraph.setLevel(nestingLevel);
            previousNode = paragraph;
        }
        return paragraphNodes;
    }
}
