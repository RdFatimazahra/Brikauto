//
//    @PutMapping("/{id}")
//    public ResponseEntity<PieceDto> updatePiece(@PathVariable int id, @RequestBody PieceDto pieceDto) {
//        PieceDto updatedPiece = pieceService.updatePiece(id, pieceDto);
//        return ResponseEntity.ok(updatedPiece);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePiece(@PathVariable int id) {
//        pieceService.deletePiece(id);
//        return ResponseEntity.noContent().build();
//    }
//}

//Methode Oussama :
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(authorize -> authorize
//                    .requestMatchers( "/api/v1/auth/registerAdmin","/api/v1/auth/authenticate").permitAll()
//                    .requestMatchers("/api/v1/auth/User/**").hasAuthority("USER")
//                    .requestMatchers("/api/v1/auth/Fournisseur/**").hasAuthority("FOURNISSEUR")
//                    .requestMatchers( "/api/v1/auth/Admin/**").hasAuthority("ADMIN")
//                    .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//            .authenticationProvider(authenticationProvider)
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//            .cors(withDefaults()); // Enable CORS
//    return http.build();
//}
//
//@Bean
//public WebMvcConfigurer webMvcConfigurer() {
//    return new WebMvcConfigurer() {
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/")
//                    .allowedOrigins("http://localhost:4200")
//                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                    .allowedHeaders("*")
//                    .allowCredentials(true);
//        }
//    };
//}
package com.backend.Controller;

import com.backend.DTO.PieceDto;
import com.backend.Model.Fournisseur;
import com.backend.Repository.FournisseurRepository;
import com.backend.Service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Fournisseur/pieces")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @PostMapping("/add")
    public ResponseEntity<PieceDto> createPiece(@RequestBody PieceDto pieceDTO, @AuthenticationPrincipal Fournisseur fournisseur) {
        Fournisseur fournisseur1 = fournisseurRepository.findById(fournisseur.getId()).get();
        PieceDto pieceDto = pieceService.createPiece(pieceDTO, fournisseur1);
      return  ResponseEntity.ok(pieceDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceDto> getPieceById(@PathVariable int id) {
        return new ResponseEntity<>(pieceService.getPieceById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PieceDto>> getAllPieces(@AuthenticationPrincipal Fournisseur fournisseur) {
        return new ResponseEntity<>(pieceService.getAllPieces(fournisseur), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PieceDto> updatePiece(@PathVariable int id, @RequestBody PieceDto pieceDTO) {
        return new ResponseEntity<>(pieceService.updatePiece(id, pieceDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiece(@PathVariable int id) {
        pieceService.deletePiece(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
