package br.com.brunomilitzer.hibernate;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "address")
@Entity(name = "Address")
@ToString(of = {"number", "street", "city"}, includeFieldNames = false)
@EqualsAndHashCode(of = {"number", "street", "city", "zipCode"}, callSuper = false)
public class Address implements Serializable {

    private static final long serialVersionUID = 8594029996912607136L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "number", nullable = false)
    private Long number;

    @NotBlank
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
}
