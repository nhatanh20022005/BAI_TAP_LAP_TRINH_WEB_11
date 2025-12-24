package vn.bt10.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for handling authentication related actions such as
 * displaying the login form and redirecting users after a successful
 * login.  The actual authentication is handled by Spring Security;
 * this controller simply returns the appropriate views.
 */
@Controller
@RequestMapping
public class AuthController {

    /**
     * Serves the custom login page.  The form on this page submits to
     * {@code /login}, which is handled by Spring Security.
     *
     * @return the name of the login template
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Default landing endpoint after login.  Examines the authenticated
     * user's roles and redirects to the appropriate home page.  Admins
     * are sent to {@code /admin/home}, while ordinary users go to
     * {@code /user/home}.  If somehow not authenticated, the user is
     * redirected back to the login page.
     *
     * @param authentication the Spring Security authentication object
     * @return a redirect URL to the appropriate page
     */
    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        if (authentication != null) {
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                    return "redirect:/admin/home";
                }
            }
            // If no admin role then treat as user
            return "redirect:/user/home";
        }
        return "redirect:/login";
    }
}