package io.daalchini.prometheuspoc.data;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@JsonIncludeProperties({"id", "title", "description", "published"})
public record BookDto(
        Long id,
        String title,
        String description,
        Boolean published
) implements Serializable {
}
