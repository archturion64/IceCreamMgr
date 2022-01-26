package com.github.archturion64.CodingChallengeIceCream.boundary;

import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ice-cream")
@RequiredArgsConstructor
public class IceCreamFlavorController {

    private final IceCreamFlavorService iceCreamFlavorService;

    @GetMapping(value = "/flavors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all stored flavors", response = IceCreamFlavorDTO.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All stored flavors were returned."),
            @ApiResponse(code = 500, message = "Internal server error.")
    })
    public ResponseEntity<Collection<IceCreamFlavorDTO>> getAll() {
        return ResponseEntity.ok(iceCreamFlavorService.getAll()
                .stream()
                .map(IceCreamFlavorDTOMapper::map)
                .collect(Collectors.toList()));
    }

    @PutMapping(value = "/flavors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new ice cream flavor to the application", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "The flavor was successfully added."),
            @ApiResponse(code = 400, message = "The data provided did not pass validation."),
            @ApiResponse(code = 500, message = "Internal server error.")
    })
    public ResponseEntity<Void> add(final @RequestBody @Valid IceCreamFlavorDTO flavorDTO) {
        iceCreamFlavorService.add(IceCreamFlavorDTOMapper.map(flavorDTO));
        return ResponseEntity.noContent().build();
    }
}
