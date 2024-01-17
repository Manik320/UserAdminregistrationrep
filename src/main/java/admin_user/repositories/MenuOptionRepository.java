package admin_user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.model.MenuOption;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
    List<MenuOption> findByRole(String role);
}
