package co.istad.bmsapi.api.file;

import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileServiceImpl fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadOne(@RequestPart("file") MultipartFile file) {

        Rest<FileDto> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been uploaded");
        rest.setData(fileService.uploadOne(file));

        return ResponseEntity.ok(rest);

    }

    @PostMapping
    ResponseEntity<?> uploadAll() {
        return null;
    }

}
