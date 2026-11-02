package com.tecnolink.tecnolink.quotes.interfaces.rest.documentation;

import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonTableDto;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.ComparisonCreateRequest;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.ComparisonProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Comparisons", description = "Operaciones REST del comparador de productos")
public interface ComparisonApi {
    @Operation(summary = "Crear comparacion", description = "Crea una comparacion de un cliente con hasta 4 productos.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "409", description = "Conflicto con las reglas del negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<ComparisonDto> create(ComparisonCreateRequest r);

    @Operation(summary = "Consultar comparacion", description = "Busca una comparacion por identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ComparisonDto get(@Parameter(description = "Identificador de la comparacion", required = true, example = "1") Long id);

    @Operation(summary = "Listar comparaciones por cliente", description = "Filtra comparaciones mediante clientId.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    List<ComparisonDto> byClient(@Parameter(description = "Identificador del cliente", required = true, example = "demo-client") String clientId);

    @Operation(summary = "Agregar producto", description = "Agrega un producto a la comparacion (maximo 4, sin repetir).")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflicto con las reglas del negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ComparisonDto addProduct(@Parameter(description = "Identificador de la comparacion", required = true, example = "1") Long id, ComparisonProductRequest r);

    @Operation(summary = "Quitar producto", description = "Quita un producto de la comparacion.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ComparisonDto removeProduct(@Parameter(description = "Identificador de la comparacion", required = true, example = "1") Long id,
                                @Parameter(description = "Identificador del producto", required = true, example = "laptop-lenovo-ideapad") String productId);

    @Operation(summary = "Generar cuadro comparativo", description = "Arma el cuadro de especificaciones de los productos (minimo 2). Una celda sin dato sale como \"-\".")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflicto con las reglas del negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ComparisonTableDto table(@Parameter(description = "Identificador de la comparacion", required = true, example = "1") Long id);
}
