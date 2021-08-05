package com.pcloud.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void address_has_noArgs() {
        Address address = new Address();
    }

    @Test
    void address_has_fields() {
        Address address = Address.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();

        assertThat(address.getCity()).isEqualTo("city");
        assertThat(address.getStreet()).isEqualTo("street");
        assertThat(address.getZipcode()).isEqualTo("zipcode");
    }
}
