package com.hospital.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {

    private String friendlyName;
    private boolean isLoggedIn;
    private boolean isDoctor;
    private Integer medicalEmployeeId;
    private boolean hasJustBeenLoggedOut;

}
