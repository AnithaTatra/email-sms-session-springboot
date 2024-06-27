package com.example.crudpractice.serviceimpl;

import com.example.crudpractice.constants.ConstantStatus;
import com.example.crudpractice.entity.Profile;
import com.example.crudpractice.repository.ProfileRepository;
import com.example.crudpractice.request.ProfileDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileInterface;
    @Override
    public ResponseDto addProfile(ProfileDto profileDto) {
        ResponseDto responseDto = new ResponseDto();
        try {


            if(ConstantStatus.FLAG.equals(profileDto.getFlag())) {
                Profile profile = new Profile();
                profile.setProfileName(profileDto.getName());
                profileInterface.save(profile);
                responseDto.setMessage(ConstantStatus.SUCCESS);
            } else {
                Profile profiles = profileInterface.findById(profileDto.getId()).get();
                    profiles.setProfileName(profileDto.getName());
                    profileInterface.save(profiles);
                    responseDto.setMessage(ConstantStatus.SUCCESS);
            }
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseData getList() {
        try {
            ProfileDto profileDto = new ProfileDto();

            List<Profile> profileList = new ArrayList<>();
            List<Profile> profiles = profileInterface.findAll();
            ResponseData data = new ResponseData();

            for(Profile profile : profiles) {
                profileDto.setName(profile.getProfileName());
                profileList.add(profile);

            }
            data.setData(profileList);
            data.setStatus("Ok");

            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseData getProfileById(Long id) {
        try {
            ResponseData response = new ResponseData();

            Optional<Profile> optionalProfile = profileInterface.findById(id);
            response.setData(optionalProfile);
            response.setStatus(ConstantStatus.SUCCESS);

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDto deleteProfileById(Long id) {
        try {
            ResponseDto responseDto = new ResponseDto();
            profileInterface.deleteById(id);
            responseDto.setMessage(ConstantStatus.SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Profile> getPages(Pageable pageable) {
        try {
            Page<Profile> pages = profileInterface.findAll(pageable);
            return pages;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Profile> getPagesBySize(int pageNumber, int pageSize) {
        Pageable profilePages = PageRequest.of(pageNumber,pageSize);
        return profileInterface.findAll(profilePages);
    }

}
