package com.alkemy.explorandodisney.web.controller;

import com.alkemy.explorandodisney.domain.Character;
import com.alkemy.explorandodisney.domain.CharacterList;
import com.alkemy.explorandodisney.domain.service.CharacterService;
import com.alkemy.explorandodisney.domain.service.ImageService;
import com.alkemy.explorandodisney.persistence.mapper.CharacterMapper;
import com.alkemy.explorandodisney.web.controller.exeptions.InvalidDataException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<CharacterList> getAll(){
        return characterService.getAll();
    }

    @GetMapping(params = "idCharacter")
    public Character getDescriptionCharacter(@RequestParam Long idCharacter){
        return characterService.getById(idCharacter);
    }

    @GetMapping(params = "movies")
    public List<CharacterList>getByMovies(@RequestParam String movies){
        return characterService.getByMovies(movies);
    }

    @GetMapping(params = "name")
    public List<CharacterList>getByName(@RequestParam String name){
        return characterService.getByName(name);
    }

    @GetMapping(params = {"youngerAge","olderAge"})
    public List<CharacterList>getByAge(@RequestParam Integer youngerAge,@RequestParam Integer olderAge){
        return characterService.getByAge(youngerAge,olderAge);
    }

    @GetMapping(params = {"lowerWeight","higherWeight"})
    public List<CharacterList>getByWeight(@RequestParam Double lowerWeight, @RequestParam Double higherWeight){
        return characterService.getByWeight(lowerWeight,higherWeight);
    }


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


    @PostMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCharacter(@RequestParam("idCharacter")Long idCharacter,@Valid @RequestBody Character character, BindingResult result){
        String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        if (result.hasErrors()){
            throw new InvalidDataException(errorMsg,result);
        }
        characterService.updateCharacter(idCharacter,character);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@RequestParam("name") String name){characterService.deleteByName(name);}

    //CREATE IMAGE

    @PostMapping(value = "/upload/image",headers=("content-type=multipart/form-data"),produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] uploadImage(@RequestParam("idCharacter") Long idCharacter,
                                                     @RequestParam("file") MultipartFile multipartFile,
                                                     UriComponentsBuilder componentsBuilder){

            return imageService.createImageCharacter(idCharacter,multipartFile);

    }

    //GET IMAGE
    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@RequestParam("idCharacter")Long idCharacter){
        return imageService.getImageCharacter(idCharacter);
    }
}
