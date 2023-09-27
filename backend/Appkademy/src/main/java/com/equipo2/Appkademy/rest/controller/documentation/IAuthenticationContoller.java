package com.equipo2.Appkademy.rest.controller.documentation;

import com.equipo2.Appkademy.rest.dto.request.AuthenticationRequestDto;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RegisterRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RoleRequestDto;
import com.equipo2.Appkademy.rest.dto.response.AuthenticationResponseDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication")
public interface IAuthenticationContoller {

    @Operation(summary = "Register a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponseDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request);

    @Operation(summary = "Update a Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Role",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacteristicRequestDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<AuthenticationResponseDto> updateRoles(Long userId, @RequestBody RoleRequestDto request);

}
