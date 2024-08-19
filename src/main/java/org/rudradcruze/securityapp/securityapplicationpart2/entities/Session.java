package org.rudradcruze.securityapp.securityapplicationpart2.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String refreshToken;
    private String accessToken;

    @UpdateTimestamp
    private LocalDateTime lastAccessTokenUsedTime;

    @UpdateTimestamp
    private LocalDateTime lastUsedAt;
}
