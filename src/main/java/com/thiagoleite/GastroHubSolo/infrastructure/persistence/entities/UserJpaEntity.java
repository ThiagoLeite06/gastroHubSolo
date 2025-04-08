package com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities;

import com.thiagoleite.GastroHubSolo.domain.entities.UserTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

    @Column(nullable = false, length = 200)
    private String address;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_type_id")
//    private UserTypeEntity userType;
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;

}
