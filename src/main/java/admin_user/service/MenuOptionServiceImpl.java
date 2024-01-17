package admin_user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin_user.model.MenuOption;
import admin_user.repositories.MenuOptionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MenuOptionServiceImpl implements MenuService {

    @Autowired
    private MenuOptionRepository menuOptionRepository;

    @Override
    public List<MenuOption> getAllMenuOptions() {
        return menuOptionRepository.findAll();
    }

    @Override
    public MenuOption getMenuOptionById(Long id) {
        Optional<MenuOption> optionalMenuOption = menuOptionRepository.findById(id);
        return optionalMenuOption.orElse(null);
    }

    @Override
    public MenuOption createMenuOption(MenuOption menuOption) {
        return menuOptionRepository.save(menuOption);
    }

    @Override
    public MenuOption updateMenuOption(Long id, MenuOption menuOption) {
        Optional<MenuOption> optionalMenuOption = menuOptionRepository.findById(id);
        if (optionalMenuOption.isPresent()) {
            MenuOption existingMenuOption = optionalMenuOption.get();
            existingMenuOption.setName(menuOption.getName());
            existingMenuOption.setUrl(menuOption.getUrl());
            existingMenuOption.setRole(menuOption.getRole());
            return menuOptionRepository.save(existingMenuOption);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteMenuOption(Long id) {
        Optional<MenuOption> optionalMenuOption = menuOptionRepository.findById(id);
        if (optionalMenuOption.isPresent()) {
            menuOptionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

	
}
