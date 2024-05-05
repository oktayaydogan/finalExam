package org.MTSG.finalExam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionDto extends BaseResponseDto {
    private String message;
}
