package likelion.finalproject.market.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    // 작가명
    @Column(unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private Auth auth;

    @Builder
    public Member(long id, String username, String email, String password, String nickname, Auth auth) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.auth = auth;
    }
}