package com.green.hoteldog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor

public enum HotelErrorCode implements ErrorCode{
    WAITING_SUSPEND_APPROVAL
            (HttpStatus.BAD_REQUEST,"호텔 중지 승인 대기중 입니다"),
    WAITING_HOTEL_APPROVAL
            (HttpStatus.BAD_REQUEST,"호텔 승인 대기중 입니다"),
    NOT_EXIST_HOTEL
            (HttpStatus.NOT_FOUND,"존재하지 않는 호텔 입니다"),
    REQUIRED_VALUE_IS_NULL
            (HttpStatus.BAD_REQUEST,"필수 값이 없습니다."),
    NON_EXIST_HOTEL_PK
            (HttpStatus.NOT_FOUND,"존재하지 않는 호텔 PK"),
    NON_EXIST_ROOM_DATE
            (HttpStatus.NOT_FOUND,"호텔 방 날짜 데이터 존재하지 않음"),
    NON_EXIST_PAGE_DATA
            (HttpStatus.NOT_FOUND,"존재하지 않는 페이지 번호"),
    UNKNOWN_DATE_FORM
            (HttpStatus.BAD_REQUEST,"날짜를 형식에 맞게 입력 해주세요."),
    NOT_EXIST_HOTEL_ROOM
            (HttpStatus.NOT_FOUND,"존재하지 않는 호텔 방 입니다"),
    NOT_ENOUGH_ROOM
            (HttpStatus.BAD_REQUEST,"방이 충분하지 않습니다"),
    NOT_EXIST_OPTION
            (HttpStatus.NOT_FOUND,"존재하지 않는 옵션 PK");
    private final HttpStatus httpStatus;
    private final String message;
}
