package com.example.SCMProject.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message="User-name is a mandatory field")
    @Size(min = 3, message="Minimum 3 characters are required")
    private String name;
    @NotBlank(message="E-mail is a mandatory field")
    @Email(message="Invalid e-mail format")
    private String email;
    @NotBlank(message="Password is a mandatory field")
    @Size(min=8,message="Password should have 8 characters")
    private String password;
    @NotBlank(message="Contact details are mandatory")
    @Size(min=8, max=12,message="Invalid contact")
    private String phoneNumber;
    @NotBlank(message="About is a mandatory field")
    private String about;


}
