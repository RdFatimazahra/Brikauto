package com.backend.auth;

import com.backend.Model.Admin;
import com.backend.Model.Fournisseur;
import com.backend.Model.Role;
import com.backend.Model.Utilisateur;
import com.backend.Repository.FournisseurRepository;
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

    private final UtilisateurRepository utilisateurRepository;

    private final FournisseurRepository fournisseurRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    //RegisterUser :
    public AuthenticationResponse register(RegisterRequest request) {
        var user = new Utilisateur();
        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .build();


    }

    //registerFournisseur :
    public AuthenticationResponse registerFournisseur(RegisterRequest request) {
        var fournisseur = new Fournisseur();
        fournisseur.setNom(request.getNom());
        fournisseur.setEmail(request.getEmail());
        fournisseur.setPassword(passwordEncoder.encode(request.getPassword()));
    fournisseur.setRole(Role.FOURNISSEUR);

        userRepository.save(fournisseur);
        var jwtToken = jwtService.generateToken(fournisseur);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(fournisseur.getRole().name())
                .build();


    }

    //Register Admin ;
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var admin = new Admin();
        admin.setNom(request.getNom());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);
        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(admin.getRole().name())
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
                .build();


    }

//    public List<Utilisateur> getAllUsers() {
//        return  utilisateurRepository.findAll();
//    }
//
//    public List<Fournisseur> getAllTechniciens() {
//        return technicienRepository.findAll();
//    }



}
