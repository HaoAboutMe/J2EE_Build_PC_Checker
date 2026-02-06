package com.j2ee.buildpcchecker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode
{
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at {min} charactors", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} charactors", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1005, "Email is not valid", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006, "User not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You don't have permission", HttpStatus.FORBIDDEN),
    INVALID_DATE_OF_BIRTH(1009, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    // CPU validation errors (2001-2099)
    CPU_NAME_REQUIRED(2001, "CPU name is required", HttpStatus.BAD_REQUEST),
    CPU_SOCKET_ID_REQUIRED(2002, "Socket ID is required", HttpStatus.BAD_REQUEST),
    CPU_IGPU_REQUIRED(2003, "iGPU field is required", HttpStatus.BAD_REQUEST),
    CPU_TDP_REQUIRED(2004, "TDP is required", HttpStatus.BAD_REQUEST),
    CPU_PCIE_VERSION_ID_REQUIRED(2005, "PCIe Version ID is required", HttpStatus.BAD_REQUEST),
    CPU_SCORE_REQUIRED(2006, "Score is required", HttpStatus.BAD_REQUEST),
    CPU_NOT_FOUND(2007, "CPU not found", HttpStatus.NOT_FOUND),

    // Mainboard validation errors (2101-2199)
    MAINBOARD_NAME_REQUIRED(2101, "Mainboard name is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_SOCKET_ID_REQUIRED(2102, "Socket ID is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_VRM_PHASE_REQUIRED(2103, "VRM Phase is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_CPU_TDP_SUPPORT_REQUIRED(2104, "CPU TDP Support is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_RAM_TYPE_ID_REQUIRED(2105, "RAM Type ID is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_RAM_BUS_MAX_REQUIRED(2106, "RAM Bus Max is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_RAM_SLOT_REQUIRED(2107, "RAM Slot is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_RAM_MAX_CAPACITY_REQUIRED(2108, "RAM Max Capacity is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_SIZE_REQUIRED(2109, "Size is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_PCIE_VGA_VERSION_ID_REQUIRED(2110, "PCIe VGA Version ID is required", HttpStatus.BAD_REQUEST),
    MAINBOARD_NOT_FOUND(2111, "Mainboard not found", HttpStatus.NOT_FOUND),

    // RAM validation errors (2201-2299)
    RAM_NAME_REQUIRED(2201, "RAM name is required", HttpStatus.BAD_REQUEST),
    RAM_TYPE_ID_REQUIRED(2202, "RAM Type ID is required", HttpStatus.BAD_REQUEST),
    RAM_BUS_REQUIRED(2203, "RAM Bus is required", HttpStatus.BAD_REQUEST),
    RAM_CAS_REQUIRED(2204, "RAM CAS is required", HttpStatus.BAD_REQUEST),
    RAM_CAPACITY_PER_STICK_REQUIRED(2205, "Capacity per stick is required", HttpStatus.BAD_REQUEST),
    RAM_QUANTITY_REQUIRED(2206, "Quantity is required", HttpStatus.BAD_REQUEST),
    RAM_TDP_REQUIRED(2207, "TDP is required", HttpStatus.BAD_REQUEST),
    RAM_NOT_FOUND(2208, "RAM not found", HttpStatus.NOT_FOUND),

    // VGA validation errors (2301-2399)
    VGA_NAME_REQUIRED(2301, "VGA name is required", HttpStatus.BAD_REQUEST),
    VGA_LENGTH_REQUIRED(2302, "Length is required", HttpStatus.BAD_REQUEST),
    VGA_TDP_REQUIRED(2303, "TDP is required", HttpStatus.BAD_REQUEST),
    VGA_PCIE_VERSION_ID_REQUIRED(2304, "PCIe Version ID is required", HttpStatus.BAD_REQUEST),
    VGA_SCORE_REQUIRED(2305, "Score is required", HttpStatus.BAD_REQUEST),
    VGA_NOT_FOUND(2306, "VGA not found", HttpStatus.NOT_FOUND),

    // Socket validation errors (2401-2499)
    SOCKET_ID_REQUIRED(2401, "Socket ID is required", HttpStatus.BAD_REQUEST),
    SOCKET_NAME_REQUIRED(2402, "Socket name is required", HttpStatus.BAD_REQUEST),
    SOCKET_NOT_FOUND(2403, "Socket not found", HttpStatus.NOT_FOUND),

    // RamType validation errors (2501-2599)
    RAM_TYPE_ID_VALUE_REQUIRED(2501, "RAM Type ID is required", HttpStatus.BAD_REQUEST),
    RAM_TYPE_NAME_REQUIRED(2502, "RAM Type name is required", HttpStatus.BAD_REQUEST),
    RAM_TYPE_NOT_FOUND(2503, "RAM Type not found", HttpStatus.NOT_FOUND),

    // PcieVersion validation errors (2601-2699)
    PCIE_VERSION_ID_REQUIRED(2601, "PCIe Version ID is required", HttpStatus.BAD_REQUEST),
    PCIE_VERSION_NAME_REQUIRED(2602, "PCIe Version name is required", HttpStatus.BAD_REQUEST),
    PCIE_VERSION_NOT_FOUND(2603, "PCIe Version not found", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;


}
