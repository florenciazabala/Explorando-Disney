package com.alkemy.explorandodisney.domain.service;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.web.controller.exeptions.ConflictException;
import com.alkemy.explorandodisney.web.controller.exeptions.FieldInvalidException;
import com.alkemy.explorandodisney.web.controller.exeptions.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImageService{

    @Autowired
    private MovieSevice movieSevice;

    @Autowired
    private CharacterService characterService;

    public final String CHARACTER_UPLOADED_FOLDER ="pictures/characters/";
    public final String MOVIE_UPLOADED_FOLDER ="pictures/movies/";

    //CREATE IMAGE
    @Transactional
    public byte[] createImageCharacter(Long id, MultipartFile multipartFile) {
        if (id == null) {
            throw new FieldInvalidException("idCharacter can't be null");
        }

        if (multipartFile.isEmpty()) {
            throw new FieldInvalidException("Is not selecting the image to upload");
        }

        Character character = characterService.getById(id);

        if (character.getPicture() != null && !character.getPicture().isEmpty()) {
            String fileName = character.getPicture();
            Path path = Paths.get(fileName);
            File f = path.toFile();
            if (f.exists()) {
                f.delete();
            }
        }

        String finalPath = createPath(multipartFile,CHARACTER_UPLOADED_FOLDER,character.getIdCharacter(),character.getName());


        character.setPicture(finalPath);

        try {
            characterService.updateCharacterImage(finalPath, id);
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);
            return bytes;
        }catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error during upload: " + multipartFile.getOriginalFilename());
        }
    }

    @Transactional
    public byte[] createImageMovie(Long id, MultipartFile multipartFile) {
        if (id == null) {
            throw new FieldInvalidException("idMovie can't be null");
        }

        if (multipartFile.isEmpty()) {
            throw new FieldInvalidException("Is not selecting the image to upload");
        }

        Movie movie = movieSevice.getById(id);

        if (movie.getPicture() != null && !movie.getPicture().isEmpty()) {
            String fileName = movie.getPicture();
            Path path = Paths.get(fileName);
            File f = path.toFile();
            if (f.exists()) {
                f.delete();
            }
        }
        String finalPath = createPath(multipartFile,MOVIE_UPLOADED_FOLDER,movie.getIdMovie(),movie.getTitle());
        movie.setPicture(finalPath);

        try {
            movieSevice.updateImage(finalPath,id);
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);
            return bytes;
        }catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error during upload: " + multipartFile.getOriginalFilename());
        }
    }

    public String createPath(MultipartFile multipartFile,String folder, Long id, String name){

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateName = dateFormat.format(date);
        System.out.println(dateName);
        String fileName = String.valueOf(id) +"-"+name+"-" + dateName + "." + multipartFile.getContentType().split("/")[1];
        String finalPath = folder + fileName;

        return finalPath;
    }

    //READ IMAGE
    public byte[] getImageCharacter(Long id){
        if (id == null) {
            throw new FieldInvalidException("idCharacter can't be null");
        }
        Character character = characterService.getById(id);
        if(character.getPicture()==null || character.getPicture().isEmpty()){
            throw new NoSuchElementException("The character hasn't image");
        }
        return getImage(character.getPicture());
    }

    public byte[] getImageMovie(Long id){
        if (id == null) {
            throw new FieldInvalidException("idMovie can't be null");
        }
        Movie movie = movieSevice.getById(id);
        if(movie.getPicture()==null || movie.getPicture().isEmpty()){
            throw new NoSuchElementException("The movie hasn't image");
        }
        return getImage(movie.getPicture());
    }

    public byte[] getImage(String namePath){
        try {

            String fileName =namePath;
            Path path = Paths.get(fileName);
            File f = path.toFile();
            if (!f.exists()) {
                throw new ConflictException("Image not found");
            }

            byte[] image = Files.readAllBytes(path);
            return image;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error to show image");
        }

    }
}
