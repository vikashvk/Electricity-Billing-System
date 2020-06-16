package com.ebs.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

/**
 * Abstraction for AuthenticationPrincipal which returns object of
 * UserDetails(Spring Security)
 * 
 * @author Poonamchand Sahu
 * 
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}