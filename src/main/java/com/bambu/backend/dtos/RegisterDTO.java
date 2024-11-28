package com.bambu.backend.dto;

package com.bambu.backend.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role){
}