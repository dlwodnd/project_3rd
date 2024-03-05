package com.green.hoteldog.mockdata;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.mockdata.models.MockTestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class MockPicController {
    private final MockPicService service;


    @PutMapping
    public ResVo updateHotelPic(@RequestPart List<MultipartFile> pics
            ,@RequestPart long hotelPk){
        return service.updateHotelPics(pics, hotelPk);
    }
    @PatchMapping
    public ResVo patchHotelRoomPic(@RequestPart List<MultipartFile> pics
            ,@RequestPart long hotelPk){
        return service.updateHotelRoomPic(pics, hotelPk);
    }
}
