package com.est.cranejob.recruit.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="recruit_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String title;
    @Column(name = "company_name", nullable = false)
    private String companyName;

}
