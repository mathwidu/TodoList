package br.com.mathwidu.todoList.filter;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.mathwidu.todoList.user.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            var servletRequest= request.getServletPath();
            if(servletRequest.startsWith("/task/")){
                    //pegar a autenticação (usuario e senha)
                var authorization = request.getHeader("Authorization");

                var authEncoded = authorization.substring("Basic ".length()).trim();

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String (authDecode);

                String[] credentials = authString.split(":");
                var user = credentials[0];
                var password = credentials[1];

                System.out.println("Authorization");
                System.out.println("User: " + user);
                System.out.println("Password: " + password);


                //validar usuario 
                var userFound = this.userRepository.findByUsername(user);
                if(userFound == null){
                    response.sendError(401);
                    return;
                }
                else{
                    System.out.println("Usuário encontrado");
                    //validar senha 
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), userFound.getPassword().toCharArray());
                    
                    if(passwordVerify.verified){
                        request.setAttribute("idUser", userFound.getId());
                        filterChain.doFilter(request, response);

                    }
                    else{
                        response.sendError(401);
                        return;
                    }
                    //Segue viagem
                }
            }else{
                filterChain.doFilter(request, response);
            }
            

    }

    
}









