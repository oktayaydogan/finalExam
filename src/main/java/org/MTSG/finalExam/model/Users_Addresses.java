package org.MTSG.finalExam.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users_addresses")
public class Users_Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "HOMETOWN")
    private String hometown;

    @OneToOne(mappedBy = "address")
    private Users user;
}
