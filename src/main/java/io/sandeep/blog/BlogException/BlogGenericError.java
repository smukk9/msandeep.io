package io.sandeep.blog.BlogException;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogGenericError {

    private String errorMessage;
}
