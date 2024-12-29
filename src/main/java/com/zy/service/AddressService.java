package com.zy.service;

import com.zy.pojo.Address;
import java.util.List;

public interface AddressService {
    List<Address> findAddressByUserId(int user_id);

    Address findAddressByAddressId(int address_id);

    void addAddress(Address address);

    void delAddressById(int address_id);
}