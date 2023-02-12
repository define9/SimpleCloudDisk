package cn.tomisme.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@Configuration
@NoArgsConstructor
public class EMailConfig {
    @Value("${spring.mail.from}")
    private String from;
}
