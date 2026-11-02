package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.application.handlers.commands.TechServiceCommandHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.TechServiceQueryHandler;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServiceQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServicesQuery;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateTechServiceRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.UpdateTechServiceRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.mappers.TechServiceRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tech-services")
@Tag(
        name = "Tech Services",
        description = "Operaciones de gestión y consulta de servicios tecnológicos"
)
public class TechServiceController {

    private final TechServiceCommandHandler techServiceCommandHandler;
    private final TechServiceQueryHandler techServiceQueryHandler;

    public TechServiceController(
            TechServiceCommandHandler techServiceCommandHandler,
            TechServiceQueryHandler techServiceQueryHandler
    ) {
        this.techServiceCommandHandler = techServiceCommandHandler;
        this.techServiceQueryHandler = techServiceQueryHandler;
    }

    @GetMapping
    @Operation(
            summary = "Listar servicios tecnológicos",
            description = "Obtiene todos los servicios tecnológicos registrados en el catálogo."
    )
    public List<TechServiceDto> getTechServices() {
        return techServiceQueryHandler.handle(
                new GetTechServicesQuery()
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consultar servicio tecnológico",
            description = "Obtiene el detalle de un servicio tecnológico mediante su identificador."
    )
    public TechServiceDto getTechService(
            @Parameter(description = "Identificador del servicio tecnológico")
            @PathVariable String id
    ) {
        return techServiceQueryHandler.handle(
                new GetTechServiceQuery(id)
        );
    }

    @PostMapping
    @Operation(
            summary = "Registrar servicio tecnológico",
            description = "Registra un nuevo servicio tecnológico en el catálogo."
    )
    public TechServiceDto createTechService(
            @Valid @RequestBody CreateTechServiceRequest request
    ) {
        return techServiceCommandHandler.handle(
                TechServiceRestMapper.toCommand(request)
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar servicio tecnológico",
            description = "Actualiza la información de un servicio tecnológico existente."
    )
    public TechServiceDto updateTechService(
            @Parameter(description = "Identificador del servicio tecnológico")
            @PathVariable String id,

            @Valid @RequestBody UpdateTechServiceRequest request
    ) {
        return techServiceCommandHandler.handle(
                TechServiceRestMapper.toCommand(id, request)
        );
    }
}