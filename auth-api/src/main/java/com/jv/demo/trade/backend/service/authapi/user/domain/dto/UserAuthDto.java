package com.jv.demo.trade.backend.service.authapi.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthDto implements UserDetails {
    private String userId;
    private String password;

    private String emailId;
    private String createdBy;

    private Date createdOn;

    private String modifiedBy;

    private String modifiedOn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // @TODO Must read from db
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(this.getUserId().startsWith("A")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
            authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
        }else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
        }
        return authorities;
        //return List.of();
    }

    @Override
    public String getUsername() {
        return this.getUserId();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

}
