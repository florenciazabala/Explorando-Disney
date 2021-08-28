package com.alkemy.explorandodisney.web.controller;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.CharacterList;
import com.alkemy.explorandodisney.domain.service.CharacterService;
import com.alkemy.explorandodisney.domain.service.ImageService;
import com.alkemy.explorandodisney.persistence.mapper.CharacterMapper;
import com.alkemy.explorandodisney.web.controller.exeptions.InvalidDataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/characters")
@Api(tags = {"Characters"})
public class CharacterController {
    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "Get a list of all characters", authorizations = { @Authorization(value="JWT") })
    @GetMapping
    public List<CharacterList> getAll(){
        return characterService.getAll();
    }

    @ApiOperation(value = "Search a movie whith an ID", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "idCharacter")
    public Character getDescriptionCharacter(@ApiParam(value = "The id of the character",required = true,example ="20")@RequestParam Long idCharacter){
        return characterService.getById(idCharacter);
    }

    @ApiOperation(value = "Search for the characters of a movie", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "movies")
    public List<CharacterList>getByMovies(@ApiParam(value = "The title of the movie",required = true,example ="Star Wars I: La amenaza fantasma")@RequestParam String movies){
        return characterService.getByMovies(movies);
    }

    @ApiOperation(value = "Search characters by name", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = "name")
    public List<CharacterList>getByName(@ApiParam(value = "The name of character",required = true,example ="Chew")@RequestParam String name){
        return characterService.getByName(name);
    }

    @ApiOperation(value = "Search characters by age range", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = {"youngerAge","olderAge"})
    public List<CharacterList>getByAge(@ApiParam(value = "The younger age",required = true,example ="100")@RequestParam Integer youngerAge,@ApiParam(value = "The older age",required = true,example ="900")@RequestParam Integer olderAge){
        return characterService.getByAge(youngerAge,olderAge);
    }

    @ApiOperation(value = "Search characters by weight range", authorizations = { @Authorization(value="JWT") })
    @GetMapping(params = {"lowerWeight","higherWeight"})
    public List<CharacterList>getByWeight(@ApiParam(value = "The smallest weight",required = true,example ="10")@RequestParam Double lowerWeight,@ApiParam(value = "The greatest weight",required = true,example ="50") @RequestParam Double higherWeight){
        return characterService.getByWeight(lowerWeight,higherWeight);
    }

    @ApiOperation(value = "Create a character", authorizations = { @Authorization(value="JWT") })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCharacter(@Valid @RequestBody Character character, BindingResult result){
        String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        if (result.hasErrors()){
            throw new InvalidDataException(errorMsg,result);
        }
        characterService.createCharacter(character);
    }

    @ApiOperation(value = "Update a character", authorizations = { @Authorization(value="JWT") })
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCharacter(@ApiParam(value = "The id of the character",required = true,example ="20")@RequestParam("idCharacter")Long idCharacter,@Valid @RequestBody Character character, BindingResult result){
        String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        if (result.hasErrors()){
            throw new InvalidDataException(errorMsg,result);
        }
        characterService.updateCharacter(idCharacter,character);
    }

    @ApiOperation(value = "Delete a character", authorizations = { @Authorization(value="JWT") })
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@ApiParam(value = "The name of the character",required = true, example = "Woody")@RequestParam("name") String name){characterService.deleteByName(name);}

    //CREATE IMAGE
    @ApiOperation(value = "Upload the image", authorizations = { @Authorization(value="JWT")}, response = FileSystemResource.class, produces = "image/jpeg")
    @PostMapping(value = "/upload/image",headers=("content-type=multipart/form-data"),produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] uploadImage(@RequestParam("idCharacter") Long idCharacter,
                                                     @RequestParam("file") MultipartFile multipartFile,
                                                     UriComponentsBuilder componentsBuilder){

            return imageService.createImageCharacter(idCharacter,multipartFile);

    }

    //GET IMAGE
    @ApiOperation(value = "See the picture", authorizations = { @Authorization(value="JWT") }, response = FileSystemResource.class, produces = "image/jpeg")
    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@RequestParam("idCharacter")Long idCharacter){
        return imageService.getImageCharacter(idCharacter);
    }
}
