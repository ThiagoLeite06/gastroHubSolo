package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
import com.thiagoleite.GastroHubSolo.domain.usecases.CreateRestaurantUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteRestaurantUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.GetRestaurantUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateRestaurantUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final GetRestaurantUseCase getRestaurantUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;

    public RestaurantController(
            CreateRestaurantUseCase createRestaurantUseCase,
            GetRestaurantUseCase getRestaurantUseCase,
            UpdateRestaurantUseCase updateRestaurantUseCase,
            DeleteRestaurantUseCase deleteRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.getRestaurantUseCase = getRestaurantUseCase;
        this.updateRestaurantUseCase = updateRestaurantUseCase;
        this.deleteRestaurantUseCase = deleteRestaurantUseCase;
    }

    @PostMapping
    public ResponseEntity<RestaurantOutput> createRestaurant(@Valid @RequestBody CreateRestaurantInput input) {
        RestaurantOutput output = createRestaurantUseCase.execute(input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantOutput> getRestaurantById(@PathVariable Long id) {
        RestaurantOutput output = getRestaurantUseCase.getById(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantOutput>> getAllRestaurants() {
        List<RestaurantOutput> restaurants = getRestaurantUseCase.getAll();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RestaurantOutput>> getRestaurantsByOwnerId(@PathVariable Long ownerId) {
        List<RestaurantOutput> restaurants = getRestaurantUseCase.getByOwnerId(ownerId);
        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantOutput> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRestaurantInput input) {
        RestaurantOutput output = updateRestaurantUseCase.execute(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        deleteRestaurantUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}