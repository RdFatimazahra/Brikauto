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
import com.backend.Service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Fournisseur/pieces")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    @PostMapping("/add")
    public ResponseEntity<PieceDto> createPiece(@RequestBody PieceDto pieceDTO) {
        return new ResponseEntity<>(pieceService.createPiece(pieceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceDto> getPieceById(@PathVariable int id) {
        return new ResponseEntity<>(pieceService.getPieceById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PieceDto>> getAllPieces() {
        return new ResponseEntity<>(pieceService.getAllPieces(), HttpStatus.OK);
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
