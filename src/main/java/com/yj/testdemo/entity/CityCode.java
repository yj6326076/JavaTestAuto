package com.yj.testdemo.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city_code")
@EntityListeners(AuditingEntityListener.class)
public class CityCode {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String value;
    @Column
    private String parent;

    public CityCode(){super();}

    public CityCode(String name, String value, String parent){
        this.name = name;
        this.value = value;
        this.parent = parent;
    }
}
