package com.ecommerce.order.response;

import com.ecommerce.order.dto.AddressDTO;
import com.ecommerce.order.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO addressDTO;

}
