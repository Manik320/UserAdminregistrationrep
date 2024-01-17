package admin_user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import admin_user.dto.MenuOptionDTO;
import admin_user.model.MenuOption;

@Service
public interface MenuService {
    

	List<MenuOption> getAllMenuOptions();

	MenuOption getMenuOptionById(Long id);

	MenuOption createMenuOption(MenuOption menuOption);

	boolean deleteMenuOption(Long id);

	MenuOption updateMenuOption(Long id, MenuOption menuOption);
}
