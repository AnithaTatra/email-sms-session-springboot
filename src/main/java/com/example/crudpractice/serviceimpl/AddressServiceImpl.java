package com.example.crudpractice.serviceimpl;

import com.example.crudpractice.constants.ConstantStatus;
import com.example.crudpractice.entity.Address;
import com.example.crudpractice.repository.AddressRepository;
import com.example.crudpractice.request.AddressDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressInterface;
    @Override
    public ResponseDto addAddress(AddressDto addressDto) {
        ResponseDto response = new ResponseDto();

        try {
            if(ConstantStatus.FLAG.equals(addressDto.getFlag())) {
                Address address = new Address();
                address.setCity(addressDto.getCity());
                address.setDistrict(addressDto.getDistrict());
                address.setState(addressDto.getState());
                addressInterface.save(address);
                response.setMessage(ConstantStatus.SUCCESS);

            } else {

                Address address = addressInterface.findById(addressDto.getId()).get();
                address.setCity(addressDto.getCity());
                address.setDistrict(addressDto.getDistrict());
                address.setState(addressDto.getState());
                addressInterface.save(address);
                response.setMessage(ConstantStatus.SUCCESS);

            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseData listOfAddresses() {
        try {
            AddressDto addressDto = new AddressDto();
            List<Address> list = new ArrayList<>();
            List<Address> addressList = addressInterface.findAll();
            ResponseData responseData = new ResponseData<>();
            for(Address addr : addressList) {
                addressDto.setCity(addr.getCity());
                addressDto.setDistrict(addr.getDistrict());
                addressDto.setState(addr.getState());
                list.add(addr);
            }
            responseData.setData(list);
            responseData.setStatus(ConstantStatus.SUCCESS);

            return responseData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseData getAddressById(Long id) {
        try {
            ResponseData responseDto = new ResponseData();
            Optional<Address> address = addressInterface.findById(id);
            responseDto.setData(address);
            responseDto.setStatus(ConstantStatus.SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDto deleteAddressById(Long id) {
        try {
            ResponseDto dto = new ResponseDto();
            addressInterface.deleteById(id);
            dto.setMessage(ConstantStatus.SUCCESS);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Address> getAddressPages(Pageable pageable) {
        try {
            Page<Address> pages = addressInterface.findAll(pageable);
            return pages;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Address> getPagesBySize(int pageNo, int pageSize, String sort) {
        Pageable pages = PageRequest.of(pageNo,pageSize, Sort.by(sort));
        return addressInterface.findAll(pages);
    }
}
