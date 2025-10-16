package org.chomookun.arch4vue.config;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/config")
@AllArgsConstructor
public class ConfigController {

    private final Config config;

    @GetMapping
    public ResponseEntity<Config> getConfig() {
        return ResponseEntity.ok(config);
    }

}
