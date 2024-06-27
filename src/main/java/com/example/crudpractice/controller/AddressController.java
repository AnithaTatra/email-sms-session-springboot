package com.example.crudpractice.controller;

import com.example.crudpractice.entity.Address;
import com.example.crudpractice.request.AddressDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressApi/v3")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/saveAddress")
    public ResponseDto addAddress(@RequestBody AddressDto addressDto) {
        ResponseDto response= addressService.addAddress(addressDto);
        return  response;
    }

    @GetMapping("/fetchAddress")
    public ResponseData listAddress() {
        ResponseData data = addressService.listOfAddresses();
        return data;
    }

    @GetMapping("/getAddressById")
    public ResponseData getAddressById(@RequestParam Long id) {
        ResponseData data = addressService.getAddressById(id);
        return data;
    }

    @DeleteMapping("/deleteAddressById")
    public ResponseDto deleteAddressById(@RequestParam Long id) {
        ResponseDto response = addressService.deleteAddressById(id);
        return response;
    }

    @GetMapping("/pagination")
    public Page<Address> showPages(Pageable pageable) {
        Page<Address> pages = addressService.getAddressPages(pageable);
        return pages;
    }

    @GetMapping("/filter")
    public Page<Address> getPagesBySize(@RequestParam int pages, @RequestParam int size, @RequestParam String sort) {
        Page<Address> pagesBySize = addressService.getPagesBySize(pages, size, sort);
        return pagesBySize;
    }
}
