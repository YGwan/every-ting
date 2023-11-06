package com.everyTing.notification.config;

import com.everyTing.core.exception.TingServerException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.InputStream;

import static com.everyTing.notification.errorCode.NotificationServerErrorCode.NSER_001;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${firebase.private.key.path}")
    private String firebasePrivateKeyPath;

    @Value("${firebase.private.key.scope}")
    private String firebasePrivateKeyScope;

    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = new ClassPathResource(firebasePrivateKeyPath).getInputStream();
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.
                                    fromStream(serviceAccount)
                                    .createScoped(firebasePrivateKeyScope)
                    ).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (Exception e) {
            throw new TingServerException(NSER_001);
        }
    }
}
