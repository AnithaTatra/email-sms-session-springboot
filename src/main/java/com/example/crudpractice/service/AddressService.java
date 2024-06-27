package com.example.crudpractice.service;

import com.example.crudpractice.entity.Address;
import com.example.crudpractice.request.AddressDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.response.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    ResponseDto addAddress(AddressDto address);

    ResponseData listOfAddresses();

    ResponseData getAddressById(Long id);

    ResponseDto deleteAddressById(Long id);

    Page<Address> getAddressPages(Pageable pageable);

    Page<Address> getPagesBySize(int pageNo, int pageSize, String sort);
}
