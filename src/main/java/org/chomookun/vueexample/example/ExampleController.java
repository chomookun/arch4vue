package org.chomookun.vueexample.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chomookun.vueexample.common.PageableUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("examples")
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper;

    @Value("classpath:data/examples.json")
    private Resource examplesJson;

    @PostConstruct
    public void loadData() throws IOException {
        if (mongoTemplate.count(new Query(), Example.class) == 0) {
            List<Example> examples = objectMapper.readValue(
                    examplesJson.getInputStream(),
                    new TypeReference<List<Example>>() {}
            );
            mongoTemplate.insert(examples , Example.class);
        }
    }

    @GetMapping
    public ResponseEntity<List<Example>> getExamples(
            @RequestParam(value = "exampleId", required = false) String exampleId,
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault Pageable pageable
    ) {
        Query query = new Query();
        if (exampleId != null) {
            query.addCriteria(Criteria.where("exampleId").is(exampleId));
        }
        if (name != null) {
            query.addCriteria(Criteria.where("name").regex(name, "i")); // 대소문자 무시
        }
        query.with(pageable);
        List<Example> examples = mongoTemplate.find(query, Example.class);
        long totalSize = mongoTemplate.count(query, Example.class);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_RANGE, PageableUtils.toContentRange("example", pageable, totalSize))
                .body(examples);
    }

    @GetMapping("{exampleId}")
    public ResponseEntity<Example> getExample(@PathVariable String exampleId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("exampleId").is(exampleId));
        Example example = mongoTemplate.findOne(query, Example.class);
        return ResponseEntity.ok(example);
    }

}
