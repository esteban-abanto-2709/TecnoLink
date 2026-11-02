package com.tecnolink.tecnolink.quotes.interfaces.rest.documentation;

import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.QuoteRequestAnswerRequest;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.QuoteRequestCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Quote Requests", description = "Operaciones REST de solicitudes de cotizacion")
public interface QuoteRequestApi {
    @Operation(summary = "Solicitar cotizacion", description = "Registra una solicitud de cotizacion de un cliente sobre una publicacion.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<QuoteRequestDto> request(QuoteRequestCreateRequest r);

    @Operation(summary = "Consultar solicitud de cotizacion", description = "Busca una solicitud de cotizacion por identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    QuoteRequestDto get(@Parameter(description = "Identificador de la solicitud de cotizacion", required = true, example = "1") Long id);

    @Operation(summary = "Listar solicitudes por cliente", description = "Filtra solicitudes de cotizacion mediante clientId.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    List<QuoteRequestDto> byClient(@Parameter(description = "Identificador del cliente", required = true, example = "demo-client") String clientId);

    @Operation(summary = "Listar solicitudes por proveedor", description = "Filtra solicitudes de cotizacion mediante supplierId.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    List<QuoteRequestDto> bySupplier(@Parameter(description = "Identificador del proveedor", required = true, example = "techperu") String supplierId);

    @Operation(summary = "Responder cotizacion", description = "El proveedor responde la solicitud; el IGV (18%) y el total se calculan a partir del subtotal.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflicto con las reglas del negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    QuoteRequestDto answer(@Parameter(description = "Identificador de la solicitud de cotizacion", required = true, example = "1") Long id, QuoteRequestAnswerRequest r);

    @Operation(summary = "Vencer cotizacion", description = "Marca como vencida una solicitud respondida cuya cotizacion ya paso su vigencia.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflicto con las reglas del negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    QuoteRequestDto expire(@Parameter(description = "Identificador de la solicitud de cotizacion", required = true, example = "1") Long id);
}
