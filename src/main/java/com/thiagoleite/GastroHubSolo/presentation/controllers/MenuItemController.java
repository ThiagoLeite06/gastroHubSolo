//package com.thiagoleite.GastroHubSolo.presentation.controllers;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.CreateMenuItemInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.UpdateMenuItemInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
//import com.thiagoleite.GastroHubSolo.domain.usecases.CreateMenuItemUseCase;
//import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteMenuItemUseCase;
//import com.thiagoleite.GastroHubSolo.domain.usecases.GetMenuItemUseCase;
//import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateMenuItemUseCase;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;
//
//@RestController
//@RequestMapping("/api/menu-items")
//@Tag(name = "Menu-Items",description = "Menu-Items Controller")
//public class MenuItemController {
//    private final CreateMenuItemUseCase createMenuItemUseCase;
//    private final GetMenuItemUseCase getMenuItemUseCase;
//    private final UpdateMenuItemUseCase updateMenuItemUseCase;
//    private final DeleteMenuItemUseCase deleteMenuItemUseCase;
//
//    public MenuItemController(
//            CreateMenuItemUseCase createMenuItemUseCase,
//            GetMenuItemUseCase getMenuItemUseCase,
//            UpdateMenuItemUseCase updateMenuItemUseCase,
//            DeleteMenuItemUseCase deleteMenuItemUseCase) {
//        this.createMenuItemUseCase = createMenuItemUseCase;
//        this.getMenuItemUseCase = getMenuItemUseCase;
//        this.updateMenuItemUseCase = updateMenuItemUseCase;
//        this.deleteMenuItemUseCase = deleteMenuItemUseCase;
//    }
//
//    @PostMapping
//    public ResponseEntity<MenuItemOutput> createMenuItem(@Valid @RequestBody CreateMenuItemInput input) {
//        MenuItemOutput output = createMenuItemUseCase.execute(input);
//        return new ResponseEntity<>(output, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(description = "Endpoint para listar o Menu Item pelo Id", summary = "Este é um resumo para listar o Menu Item pelo Id")
//    public ResponseEntity<MenuItemOutput> getMenuItemById(@PathVariable Long id) {
//        MenuItemOutput output = getMenuItemUseCase.getById(id);
//        return ResponseEntity.ok(output);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<MenuItemOutput>> getAllMenuItems() {
//        List<MenuItemOutput> menuItems = getMenuItemUseCase.getAll();
//        return ResponseEntity.ok(menuItems);
//    }
//
//    @GetMapping("/restaurant/{restaurantId}")
//    @Operation(description = "Endpoint para listar o Menu Item pelo Id do restaurante", summary = "Este é um resumo para listar o Menu Item pelo Id do restaurante")
//    public ResponseEntity<List<MenuItemOutput>> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
//        List<MenuItemOutput> menuItems = getMenuItemUseCase.getByRestaurantId(restaurantId);
//        return ResponseEntity.ok(menuItems);
//    }
//
//    @PutMapping("/{id}")
//    @Operation(description = "Endpoint para atualizar o Menu Item", summary = "Este é um resumo para atualizar o Menu Item")
//    public ResponseEntity<MenuItemOutput> updateMenuItem(
//            @PathVariable Long id,
//            @Valid @RequestBody UpdateMenuItemInput input) {
//        MenuItemOutput output = updateMenuItemUseCase.execute(id, input);
//        return ResponseEntity.ok(output);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(description = "Endpoint para deletar o Menu Item", summary = "Este é um resumo para deletar o Menu Item")
//    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
//        deleteMenuItemUseCase.execute(id);
//        return ResponseEntity.noContent().build();
//    }
//}