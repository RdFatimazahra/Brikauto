package com.backend.auth;

import com.backend.Model.Admin;
import com.backend.Model.Panier;
import com.backend.Model.Role;
import com.backend.Model.Utilisateur;
//import com.backend.Repository.FournisseurRepository;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.UserRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PanierRepository panierRepository;

    //RegisterUser :
    public AuthenticationResponse register(RegisterRequest request) {
        var user = new Utilisateur();

        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        //register user for cart
       Utilisateur savedUser = userRepository.save(user);


       //create new panier for new client ::
        Panier panier = new Panier();
        panier.setUtilisateur(savedUser);
        panierRepository.save(panier);


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .id(user.getId())
                .build();


    }


    //Register Admin ;
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var admin = new Admin();
        admin.setNom(request.getNom());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Role.ADMIN);

        Admin savedAdmin = userRepository.save(admin);
        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(admin.getRole().name())
                .id(savedAdmin.getId())
                .build();


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .id(user.getId())
                .build();


    }





}
