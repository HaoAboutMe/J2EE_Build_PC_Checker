package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.FormFactorRequest;
import com.j2ee.buildpcchecker.dto.response.FormFactorResponse;
import com.j2ee.buildpcchecker.entity.FormFactor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormFactorMapper {

    FormFactor toFormFactor(FormFactorRequest request);

    FormFactorResponse toFormFactorResponse(FormFactor formFactor);

    List<FormFactorResponse> toListFormFactorResponse(List<FormFactor> formFactors);

    void updateFormFactor(@MappingTarget FormFactor formFactor, FormFactorRequest request);
}

