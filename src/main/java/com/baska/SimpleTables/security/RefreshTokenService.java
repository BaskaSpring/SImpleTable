//package com.baska.SimpleTables.security;
//
//
//import com.baska.SimpleTables.repository.UserRepository;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.mfn.TableBuilder.model.auth.RefreshToken;
//import ru.mfn.TableBuilder.repository.RefreshTokenRepository;
//import ru.mfn.TableBuilder.repository.UserRepository;
//import ru.mfn.TableBuilder.security.exception.RefreshTokenRandomError;
//
//import java.time.Instant;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class RefreshTokenService {
//
//    @Value("${tableb.app.jwtRefreshExpirationMs}")
//    private Long refreshTokenDurationMs;
//
////    @Autowired
////    private RefreshTokenRepository refreshTokenRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Optional<RefreshToken> findByToken(String token) {
//        return refreshTokenRepository.findByToken(token);
//    }
//
//    @SneakyThrows
//    public RefreshToken createRefreshToken(String userId) {
//        RefreshToken refreshToken = new RefreshToken();
//        int count=0;
//        String token = "";
//        while (count<20) {
//            token = UUID.randomUUID().toString();
//            Optional<RefreshToken> rt = findByToken(token);
//            if (rt.isEmpty()){
//                break;
//            }
//            count++;
//            if (count>10){
//                throw new RefreshTokenRandomError("Error: Refresh token is not random!");
//            }
//        }
//        refreshToken.setUser(userRepository.findById(userId).get());
//        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
//        refreshToken.setToken(token);
//
//        refreshToken = refreshTokenRepository.save(refreshToken);
//        return refreshToken;
//    }
//
//    public Boolean verifyExpiration(RefreshToken token) {
//        return token.getExpiryDate().compareTo(Instant.now()) >= 0;
//    }
//
//    @Transactional
//    public int deleteByUserId(String userId) {
//        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
//    }
//}
