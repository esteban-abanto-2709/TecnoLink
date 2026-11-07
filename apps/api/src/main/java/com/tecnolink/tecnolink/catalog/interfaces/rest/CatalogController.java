package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.CatalogItem;
import com.tecnolink.tecnolink.suppliers.application.SupplierService;
import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "Catalog",
        description = "Operaciones de consulta del catálogo de TecnoLink"
)
public class CatalogController {

    private final CatalogService catalogService;
    private final SupplierService supplierService;

    public CatalogController(
            CatalogService catalogService,
            SupplierService supplierService
    ) {
        this.catalogService = catalogService;
        this.supplierService = supplierService;
    }

    @GetMapping("/categories")
    @Operation(
            summary = "Listar categorías",
            description = "Obtiene todas las categorías disponibles en el catálogo."
    )
    public List<Category> categories() {
        return catalogService.getCategories();
    }

    @GetMapping("/services/{id}")
    @Operation(
            summary = "Consultar servicio",
            description = "Obtiene el detalle de un servicio tecnológico mediante su identificador."
    )
    public TechService service(
            @Parameter(description = "Identificador del servicio")
            @PathVariable String id
    ) {
        return catalogService.getService(id);
    }

    @GetMapping("/catalog")
    @Operation(
            summary = "Buscar y filtrar catálogo",
            description = "Permite consultar el catálogo utilizando texto, categoría, tipo y rango de precios."
    )
    public List<CatalogItem> search(
            @Parameter(description = "Texto de búsqueda")
            @RequestParam(required = false) String query,

            @Parameter(description = "Identificador de categoría")
            @RequestParam(required = false) String categoryId,

            @Parameter(description = "Tipo de categoría")
            @RequestParam(required = false) CategoryKind kind,

            @Parameter(description = "Precio mínimo")
            @RequestParam(required = false) Double minPrice,

            @Parameter(description = "Precio máximo")
            @RequestParam(required = false) Double maxPrice
    ) {
        Map<String, String> supplierNames = new HashMap<>();

        for (Supplier supplier : supplierService.getSuppliers()) {
            supplierNames.put(
                    supplier.getId(),
                    supplier.getName()
            );
        }

        return catalogService.search(
                query,
                categoryId,
                kind,
                minPrice,
                maxPrice,
                supplierNames
        );
    }
}
