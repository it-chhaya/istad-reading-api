package co.istad.bmsapi.api.file.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.istad.bmsapi.api.file.FileServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {
    
    private final FileServiceImpl fileServiceImpl;

    @PostMapping
    ResponseEntity<?> uploadOne(@RequestPart("file") MultipartFile file) {
        var fileDto = fileServiceImpl.uploadOne(file);

        var rest = new Rest<FileDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been uploaded.");
        rest.setData(fileDto);

        return ResponseEntity.ok(rest);
    }
    

    @PostMapping("upload-all")
    ResponseEntity<?> uploadAll(@RequestPart("files") List<MultipartFile> files) {
        var fileDtoList = fileServiceImpl.uploadAll(files);

        var rest = new Rest<List<FileDto>>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been uploaded.");
        rest.setData(fileDtoList);

        return ResponseEntity.ok(rest);
    }

}
