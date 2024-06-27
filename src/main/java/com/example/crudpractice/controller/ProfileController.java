package com.example.crudpractice.controller;

import com.example.crudpractice.entity.Profile;
import com.example.crudpractice.request.ProfileDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profileApi/v2")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/save")
    public ResponseDto add(@RequestBody ProfileDto profileDto) {
        ResponseDto response = profileService.addProfile(profileDto);
        return response;
    }

    @GetMapping("/fetch")
    public ResponseData getAll() {
        ResponseData responseData = profileService.getList();
        return  responseData;
    }

    @GetMapping("/getById")
    public ResponseData getProfileById(@RequestParam("id") Long id) {
        ResponseData responseData = profileService.getProfileById(id);
        return  responseData;
    }

    @DeleteMapping("/delete")
    public ResponseDto deleteProfileById(@RequestParam("profileId") Long id) {
        ResponseDto responseDto = profileService.deleteProfileById(id);
        return responseDto;
    }

    @GetMapping("/pagination")
    public Page<Profile> getProfilePages(Pageable pageable) {
        Page<Profile> pages = profileService.getPages(pageable);
        return pages;
    }

    @GetMapping("/filter")
    public Page<Profile> getProflePagesBySize(@RequestParam int num, @RequestParam int size) {
        Page<Profile> response = profileService.getPagesBySize(num,size);
        return response;
    }
}
