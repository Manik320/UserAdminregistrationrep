package admin_user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import admin_user.model.MenuOption;
import admin_user.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menu-options")
public class MenuOptionController {

    @Autowired
    private MenuService menuOptionService;

    @GetMapping
    public ResponseEntity<List<MenuOption>> getAllMenuOptions() {
        List<MenuOption> menuOptions = menuOptionService.getAllMenuOptions();
        return ResponseEntity.ok(menuOptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuOption> getMenuOptionById(@PathVariable Long id) {
        MenuOption menuOption = menuOptionService.getMenuOptionById(id);
        if (menuOption != null) {
            return ResponseEntity.ok(menuOption);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MenuOption> createMenuOption(@RequestBody MenuOption menuOption) {
        MenuOption createdMenuOption = menuOptionService.createMenuOption(menuOption);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuOption> updateMenuOption(@PathVariable Long id, @RequestBody MenuOption menuOption) {
        MenuOption updatedMenuOption = menuOptionService.updateMenuOption(id, menuOption);
        if (updatedMenuOption != null) {
            return ResponseEntity.ok(updatedMenuOption);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuOption(@PathVariable Long id) {
        boolean deleted = menuOptionService.deleteMenuOption(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
