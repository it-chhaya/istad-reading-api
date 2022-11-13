package co.istad.bmsapi.shared.constraint.fileid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import co.istad.bmsapi.api.file.FileServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstraintFileIdValidator implements ConstraintValidator<ConstraintFileId, Long> {

    private final FileServiceImpl fileServiceImpl;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return fileServiceImpl.existsFileID(id);
    }
    
}
