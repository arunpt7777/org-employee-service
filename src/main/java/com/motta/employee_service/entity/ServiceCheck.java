package com.motta.employee_service.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Service_check")
@SequenceGenerator(name = "Custom_Sequence", sequenceName = "custom_sequence", initialValue = 2,allocationSize = 1)
@Data
@NoArgsConstructor
public class ServiceCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Custom_Sequence")
    private int id;
    private String serviceName;
    private int status;

    public ServiceCheck(int id, String serviceName, int status) {
        this.id = id;
        this.serviceName = serviceName;
        this.status = status;
    }
}
