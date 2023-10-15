package br.com.gabriel.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gabriel.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var currentPath = request.getServletPath();

        if (currentPath.startsWith("/tasks")) {
            var authorization = request.getHeader("Authorization");

            if (authorization == null) {
                response.sendError(403);
                return;
            }

            var code = authorization.substring("Basic".length()).trim();
            var decoded = Base64.getDecoder().decode(code);
            String userAndPassword = new String(decoded);
            String[] credentials = userAndPassword.split(":");

            String username = credentials[0];
            String password = credentials[1];

            //1° - Verifica existência de usuário
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401);
            } else {
                // 2° - Verifica se senha é válida
                var passwordVerification = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (passwordVerification.verified) {
                    // Caso for continuar fluxo
                    request.setAttribute("userId", user.getId());
                    filterChain.doFilter(request, response); // Passou nas verificações!
                } else {
                    response.sendError(401);
                }
            }
        } else {
            // Caso não seja a endpoint /tasks
            filterChain.doFilter(request, response);
        }
    }
}
