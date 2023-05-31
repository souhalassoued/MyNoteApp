package com.MyNote.MyNote.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection="Employe")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class Employe implements UserDetails {
   @Id

    private String ID;
    @Indexed(unique = true)

    private String Username;
    @Indexed(unique = true)
    @NonNull
    private String email;
    @Indexed(unique = true)

    private String phoneNumber;
    @JsonIgnore
    @NonNull
    private String password;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return Username;
    }

  public String getEmail() {
      return email;
  }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
