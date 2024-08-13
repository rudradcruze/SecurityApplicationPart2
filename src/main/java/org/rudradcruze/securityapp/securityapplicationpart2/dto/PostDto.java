package org.rudradcruze.securityapp.securityapplicationpart2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    private Long id;

    @NotBlank(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @NotEmpty(message = "Content is required")
    @Size(min = 3, max = 1000, message = "Content must be between 3 and 1000 characters")
    private String content;
}
