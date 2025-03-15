package com.SchoolNews.jefiro.br.domain;

import com.SchoolNews.jefiro.br.models.NewsModel;
import com.SchoolNews.jefiro.br.models.dto.UpMembersDTO;
import com.SchoolNews.jefiro.br.models.enumm.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "members")
@Entity
public class MembersModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private String image;
    private String dateCreated;
    private String dateUpdated;
    private Boolean status;
    private Boolean publishedPermission;
    private Boolean accountNotLocked;

    @OneToMany(mappedBy = "members",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsModel> news;

    public MembersModel(UpMembersDTO data, String passWordEncry){
        this.name = data.name();
        this.email = data.email();
        this.password = passWordEncry;
        this.role = MemberRole.STUDENT;
        this.image = data.image();
        this.dateCreated = LocalDateTime.now().toString();
        this.status = true;
        this.publishedPermission = true;
        this.accountNotLocked = true;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == MemberRole.ADMIN)return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_EDITOR"), new SimpleGrantedAuthority("ROLE_STUDENT"));
        if (this.role == MemberRole.EDITOR)return List.of(new SimpleGrantedAuthority("ROLE_EDITOR"),
                new SimpleGrantedAuthority("ROLE_STUDENT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status;
    }
}
