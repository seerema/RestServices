package com.seerema.srv.shared.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.seerema.srv.shared.ExtDto;

public class BaseDtoTest {

  @Test
  void testExtDto() {

    ExtDto dto = SharedTestUtils.getTestExtDto();
    assertEquals("value:{value:test};values:[value:test];linked:null;id:1;b:2",
        dto.toString());
  }
}
