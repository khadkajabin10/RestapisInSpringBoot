package com.example.demo.Dot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

@Data

public class AddstudentDto {
    @NotBlank(message = "name cnnot be blank")
    @Size(min = 3,max = 15, message = "Enter name length in proper formate size")
    private String name;
    @Email
    @NotBlank(message = "email cannot be blank")
    private  String email;
}
