package com.example.crudpractice.service;

import com.example.crudpractice.entity.Profile;
import com.example.crudpractice.request.ProfileDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.response.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {

    ResponseDto addProfile(ProfileDto profileDto);

    ResponseData getList();

    ResponseData getProfileById(Long id);

    ResponseDto deleteProfileById(Long id);

    Page<Profile> getPages(Pageable pageable);

    Page<Profile> getPagesBySize(int pageNumber, int pageSize);


}
