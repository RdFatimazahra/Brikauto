package com.backend.Controller;

import com.backend.DTO.PieceDto;
import com.backend.Service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Admin/pieces")

//@CrossOrigin(origins = "http://localhost:4200/")
public class PieceController {
    @Autowired
    private PieceService pieceService;

    @PostMapping
    public ResponseEntity<PieceDto> createPiece(@RequestBody PieceDto pieceDto) {
        PieceDto createdPiece = pieceService.createPiece(pieceDto);
        return ResponseEntity.ok(createdPiece);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceDto> getPieceById(@PathVariable int id) {
        PieceDto pieceDto = pieceService.getPieceById(id);
        return ResponseEntity.ok(pieceDto);
    }

    @GetMapping
    public ResponseEntity<List<PieceDto>> getAllPieces() {
        List<PieceDto> pieces = pieceService.getAllPieces();
        return ResponseEntity.ok(pieces);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PieceDto> updatePiece(@PathVariable int id, @RequestBody PieceDto pieceDto) {
        PieceDto updatedPiece = pieceService.updatePiece(id, pieceDto);
        return ResponseEntity.ok(updatedPiece);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiece(@PathVariable int id) {
        pieceService.deletePiece(id);
        return ResponseEntity.noContent().build();
    }
}

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
