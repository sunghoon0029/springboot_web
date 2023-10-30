package com.project.web.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;
import java.util.concurrent.TimeUnit;

@Getter
@RedisHash("refreshToken")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    private Long id;

    private String refreshToken;

    @TimeToLive(unit = TimeUnit.DAYS)
    private Integer exp;

    public void setExp(Integer expiration) {
        this.exp = expiration;
    }
}
