package com.github.archturion64.CodingChallengeIceCream.boundary;

import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ice-cream")
public class IceCreamFlavorController {

    private final IceCreamFlavorService iceCreamFlavorService;

    public IceCreamFlavorController(IceCreamFlavorService iceCreamFlavorService) {
        this.iceCreamFlavorService = iceCreamFlavorService;
    }

    @GetMapping(value = "/flavors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<IceCreamFlavorDTO>> getAll() {
        return ResponseEntity.ok(iceCreamFlavorService.getAll()
                .stream()
                .map(IceCreamFlavorDTOMapper::map)
                .collect(Collectors.toList()));
    }

    @PutMapping(value = "/flavors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(final @RequestBody @Valid IceCreamFlavorDTO flavorDTO) {
        iceCreamFlavorService.add(IceCreamFlavorDTOMapper.map(flavorDTO));
        return ResponseEntity.noContent().build();
    }
}
