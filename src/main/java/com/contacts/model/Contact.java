package com.contacts.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private List<Email> emails;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private List<Phone> phones;
    @ManyToOne
    private User user;
}
