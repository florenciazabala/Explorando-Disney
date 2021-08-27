package com.alkemy.explorandodisney.web.controller;

import com.alkemy.explorandodisney.domain.Movie;
import com.alkemy.explorandodisney.domain.MovieList;
import com.alkemy.explorandodisney.domain.service.ImageService;
import com.alkemy.explorandodisney.domain.service.MovieSevice;
import com.alkemy.explorandodisney.web.controller.exeptions.FieldInvalidException;
import com.alkemy.explorandodisney.web.controller.exeptions.InvalidDataException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/movies")
@Api(tags = {"Movies"})
public class MovieController {

    @Autowired
    private MovieSevice movieSevice;

    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "Get a list of all movies", authorizations = { @Authorization(value="JWT") })
    @GetMapping
    public List<MovieList> getAll(){
        return movieSevice.getAll();
    }

    @ApiOperation(value = "Search a movie by a tile", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "title")
    public List<MovieList> getByTitle(@ApiParam(value = "The title of the movie",required = true,example = "Alicia en el pais de las maravillas") @RequestParam String title){
        return movieSevice.getByTitle(title);
    }

    @ApiOperation(value = "Get a list of all movies by category", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "category")
    public  List<MovieList> getByCategory(@ApiParam @RequestParam String category){
        return movieSevice.getByCategory(category);
    }

    @ApiOperation(value = "Sort movies by creation date", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "order")
    public List<MovieList> getByDate(@ApiParam @RequestParam String order){
        if(!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")){
            throw new FieldInvalidException("Values must be ASC or DESC");
        }
        return movieSevice.getByDate(order.toUpperCase());
    }

    @ApiOperation(value = "Search a movie whith an ID", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "idMovie")
    public Movie getDescriptionMovie(@ApiParam @RequestParam("idMovie") Long idPelicula){
        return movieSevice.getById(idPelicula);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody @Valid Movie movie, BindingResult result){
        String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        if (result.hasErrors()){
            throw new InvalidDataException(errorMsg,result);
        }
        movieSevice.createMovie(movie);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovie(@RequestParam("idMovie") Long idPelicula,@RequestBody @Valid Movie movie,BindingResult result){
        String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        if (result.hasErrors()){
            throw new InvalidDataException(errorMsg,result);
        }
        movieSevice.updateMovie(idPelicula,movie);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@RequestParam("title") String title){movieSevice.deleteByTitle(title);}

    //CREATE  IMAGE

    @PostMapping(value="/upload/image",headers=("content-type=multipart/form-data"),produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)

    public byte[] uploadImage(@RequestParam("idMovie") Long idMovie,
                                                     @RequestParam("file") MultipartFile multipartFile,
                                                     UriComponentsBuilder componentsBuilder){

        return imageService.createImageMovie(idMovie,multipartFile);
    }

    //GET IMAGE
    @ApiOperation(value = "Get an image", authorizations = { @Authorization(value="JWT") }, response = FileSystemResource.class, produces = "image/jpeg")
    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@RequestParam("idMovie")Long idMovie){
        return imageService.getImageMovie(idMovie);
    }

    //RELATIONS
    @PostMapping("/addCharacters")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCharacters(@RequestParam ("title")String title, @RequestBody Set<String> characters){
        movieSevice.addCharacters(title,characters);
    }

    @DeleteMapping("/removeCharacters")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCharacters(@RequestParam ("title")String title, @RequestBody Set<String> characters){
        movieSevice.removeCharacters(title,characters);
    }
}
