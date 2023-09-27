package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.service.TeachingProficiencyService;
import com.equipo2.Appkademy.rest.controller.documentation.ITeachingSubjectController;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.TeachingSubjectDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectSearchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.equipo2.Appkademy.core.security.model.PermissionConstants.TEACHING_SUBJECT_CREATE;
import static com.equipo2.Appkademy.core.security.model.PermissionConstants.TEACHING_SUBJECT_DELETE;
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/providers/teaching_subject")
public class TeachingSubjectController implements ITeachingSubjectController {

    @Autowired
    private TeachingProficiencyService teachingProficiencyService;


    @PostMapping
    @PreAuthorize("hasAuthority('" + TEACHING_SUBJECT_CREATE + "')")
    public ResponseEntity<TeachingSubjectResponseDto> create(@RequestBody TeachingSubjectDto createDto){
        TeachingSubjectResponseDto responseDto = teachingProficiencyService.create(createDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @PostMapping("/search")
    //@PreAuthorize("hasAuthority('" + TEACHING_SUBJECT_READ + "')")
    public ResponseEntity<TeachingSubjectSearchResponseDto> search(@RequestBody PageableFilter filter){
        TeachingSubjectSearchResponseDto searchResponseDto = teachingProficiencyService.search(filter);
        return ResponseEntity.ok(searchResponseDto);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + TEACHING_SUBJECT_DELETE + "')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teachingProficiencyService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
