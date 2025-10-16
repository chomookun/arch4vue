package org.chomookun.arch4vue.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chomookun.arch4vue.common.PageableUtils;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.collection.Document;
import org.dizitart.no2.collection.NitriteCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/examples")
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final Nitrite nitrite;

//    private final MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper;

//    @Value("classpath:data/examples.json")
//    private Resource examplesJson;

    @PostConstruct
    public void init() throws IOException {
        NitriteCollection collection = nitrite.getCollection("examples");
        InputStream is = getClass().getResourceAsStream("/data/examples.json");
        List<Map<String, Object>> data = objectMapper.readValue(is, new TypeReference<>() {});
        for (Map<String, Object> item : data) {
            Document doc = Document.createDocument(item);
            collection.insert(doc);
        }
    }


//    @PostConstruct
//    public void loadData() throws IOException {
//        if (mongoTemplate.count(new Query(), Example.class) == 0) {
//            List<Example> examples = objectMapper.readValue(
//                    examplesJson.getInputStream(),
//                    new TypeReference<List<Example>>() {}
//            );
//            mongoTemplate.insert(examples , Example.class);
//        }
//    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getExamples(
            @RequestParam(value = "exampleId", required = false) String exampleId,
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault Pageable pageable
    ) {
        NitriteCollection collection = nitrite.getCollection("examples");
        List<Map<String,Object>> examples = collection.find()
                .toList()
                .stream()
                .map(doc ->objectMapper.convertValue(doc, new TypeReference<Map<String, Object>>() {}))
                .toList();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_RANGE, PageableUtils.toContentRange("example", pageable))
                .body(examples);

//        Query query = new Query();
//        if (exampleId != null) {
//            query.addCriteria(Criteria.where("exampleId").is(exampleId));
//        }
//        if (name != null) {
//            query.addCriteria(Criteria.where("name").regex(name, "i")); // 대소문자 무시
//        }
//        query.with(pageable);
//        List<Example> examples = mongoTemplate.find(query, Example.class);
//        long totalSize = mongoTemplate.count(query, Example.class);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_RANGE, PageableUtils.toContentRange("example", pageable, totalSize))
//                .body(examples);
    }

    @GetMapping("{exampleId}")
    public ResponseEntity<Example> getExample(@PathVariable String exampleId) {

//        Query query = new Query();
//        query.addCriteria(Criteria.where("exampleId").is(exampleId));
//        Example example = mongoTemplate.findOne(query, Example.class);
//        return ResponseEntity.ok(example);

        return ResponseEntity.ok().build();
    }

}
