package co.istad.bmsapi.api.file.web;

import co.istad.bmsapi.api.file.FileServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    

    /**
     * Upload a file
     * @param file is an object from request part
     * @return ResponseEntity
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadOne(@RequestPart("file") MultipartFile file) {

        Rest<FileDto> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been uploaded.");
        rest.setData(fileService.uploadOne(file));

        return ResponseEntity.ok(rest);

    }



    /**
     * Upload all files in the same time
     * @param files is the list of objects from request part
     * @return ResponseEntity
     * @author Chan Chhaya
     */
    @PostMapping(path = "/upload-all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadAll(@RequestPart("files") List<MultipartFile> files) {

        Rest<List<FileDto>> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Files have been uploaded.");
        rest.setData(fileService.uploadAll(files));

        return ResponseEntity.ok(rest);

    }


    @GetMapping
    ResponseEntity<?> getAllFiles() {
        return null;
    }


    @GetMapping("/{uuid}")
    ResponseEntity<?> getFileByUUID() {
        return null;
    }


    @DeleteMapping("/{uuid}")
    ResponseEntity<?> deleteFileByUUID() {
        return null;
    }

}
