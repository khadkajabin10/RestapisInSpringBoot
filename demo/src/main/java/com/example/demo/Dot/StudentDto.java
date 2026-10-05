package com.example.demo.Dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable {
    private long id;

    private String name;
    private  String email;
}
