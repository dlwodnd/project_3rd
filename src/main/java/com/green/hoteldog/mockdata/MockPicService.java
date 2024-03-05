package com.green.hoteldog.mockdata;

import com.green.hoteldog.common.entity.HotelEntity;
import com.green.hoteldog.common.entity.HotelPicEntity;
import com.green.hoteldog.common.entity.HotelRoomInfoEntity;
import com.green.hoteldog.common.repository.HotelPicRepository;
import com.green.hoteldog.common.repository.HotelRepository;
import com.green.hoteldog.common.repository.HotelRoomRepository;
import com.green.hoteldog.common.utils.MyFileUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.mockdata.models.MockPicDto;
import com.green.hoteldog.mockdata.models.MockPicsDto;
import com.green.hoteldog.mockdata.models.MockTestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MockPicService {
    private final HotelRepository hotelRepository;
    private final HotelPicRepository hotelPicRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final MyFileUtils fileUtils;

    @Transactional
    public ResVo updateHotelPics(List<MultipartFile> pics,long hotelPk){
        HotelEntity hotel = hotelRepository.findById(hotelPk).orElseThrow(()->new RuntimeException("존재하지 않는 호텔입니다."));
        List<String> picUrls = new ArrayList<>();
        hotelPicRepository.deleteAllByHotelEntity(hotel);
        String targetPath = "hotel/"+hotelPk;
        fileUtils.delFolderTrigger(targetPath);
        List<HotelPicEntity> picEntities = new ArrayList<>();
        for (MultipartFile pic : pics) {
            String picUrl = fileUtils.transferTo(pic, targetPath);
            picUrls.add(picUrl);
            HotelPicEntity picEntity = new HotelPicEntity();
            picEntity.setHotelEntity(hotel);
            picEntity.setPic(picUrl);
            picEntities.add(picEntity);
            hotelPicRepository.save(picEntity);
        }


        return new ResVo(1);
    }

    public ResVo updateHotelRoomPic(List<MultipartFile> pics,long hotelPk){
        HotelEntity hotel = hotelRepository.findById(hotelPk).orElseThrow(()->new RuntimeException("존재하지 않는 호텔입니다."));
        int x = 0;
        List<HotelRoomInfoEntity> roomInfoEntityList = hotelRoomRepository.findHotelRoomInfoEntitiesByHotelEntity(hotel);

        for (HotelRoomInfoEntity roomInfoEntity : roomInfoEntityList) {
            String targetPath = "hotel/"+hotelPk+"/room/"+roomInfoEntity.getHotelRoomPk();
            fileUtils.delFolderTrigger(targetPath);
            String fileNm = fileUtils.transferTo(pics.get(x), targetPath);
            roomInfoEntity.setRoomPic(fileNm);
            x++;
            hotelRoomRepository.save(roomInfoEntity);
        }
        return new ResVo(1);
    }
}
