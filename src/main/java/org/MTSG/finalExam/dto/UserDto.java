package org.MTSG.finalExam.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseResponseDto {
    private String name;
    private String surname;
    private String email;
    private String city;
    private String district;
    private String hometown;
}
