package vn.bt10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.bt10.entity.User;
import vn.bt10.repository.ProductRepository;
import vn.bt10.repository.UserRepository;

/**
 * Controller for the user home page.  Displays a welcome message and
 * lists products belonging to the logged in user.  Only accessible to
 * users with the USER role.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
            model.addAttribute("user", user);
            if (user != null) {
                model.addAttribute("products", productRepository.findByUserId(user.getId()));
            }
        }
        return "user/home";
    }
}