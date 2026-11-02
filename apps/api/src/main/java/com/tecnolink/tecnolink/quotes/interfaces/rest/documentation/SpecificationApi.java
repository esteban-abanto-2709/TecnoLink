package com.tecnolink.tecnolink.quotes.interfaces.rest.documentation;

import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.SpecificationCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Specifications", description = "Operaciones REST de especificaciones tecnicas de productos")
public interface SpecificationApi {
    @Operation(summary = "Agregar especificacion", description = "Registra una especificacion tecnica (nombre, valor y unidad) de un producto.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "409", description = "Conflicto con las reglas del negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<SpecificationDto> add(SpecificationCreateRequest r);

    @Operation(summary = "Consultar especificacion", description = "Busca una especificacion por identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    SpecificationDto get(@Parameter(description = "Identificador de la especificacion", required = true, example = "1") Long id);

    @Operation(summary = "Listar especificaciones por producto", description = "Filtra especificaciones mediante productId.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    List<SpecificationDto> byProduct(@Parameter(description = "Identificador del producto", required = true, example = "laptop-lenovo-ideapad") String productId);
}
