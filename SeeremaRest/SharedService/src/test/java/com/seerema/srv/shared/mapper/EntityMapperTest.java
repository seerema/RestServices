/*
 * Seerema Business Solutions - http://www.seerema.com/
 * 
 * Copyright 2020 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Contributors:
 * 
 */

package com.seerema.srv.shared.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.seerema.base.WsSrvException;
import com.seerema.shared.common.TestConstants;
import com.seerema.srv.shared.BaseObj;
import com.seerema.srv.shared.BaseValue;
import com.seerema.srv.shared.ErrorCodes;
import com.seerema.srv.shared.ExtDto;
import com.seerema.srv.shared.ExtObj;
import com.seerema.srv.shared.GenericTestDto;
import com.seerema.srv.shared.GenericTestObj;
import com.seerema.srv.shared.LinkedDto;
import com.seerema.srv.shared.LinkedObj;
import com.seerema.srv.shared.SimpleDto;
import com.seerema.srv.shared.ValueDto;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

class EntityMapperTest {

  private static EntityMapper _mapper;

  @BeforeAll
  public static void setMapper() {
    _mapper = new EntityMapper();
    _mapper.setLog(TestConstants.LOG);
  }

  @Test
  void testBaseDto() throws WsSrvException {
    checkBaseDto(_mapper.map(getTestBaseObj(), SimpleDto.class));
  }

  @Test
  void testExtMap() throws WsSrvException {
    checkExtDto(_mapper.map(getTestExtObj(), ExtDto.class));
  }

  @Test
  void testMissingDtoTypeAnnotation() throws WsSrvException {
    try {
      _mapper.map(getTestBaseObj(), FakeObj.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.MISSING_DTO_TYPE_ANNOTATION.name(),
          e.getErrorCode());
      assertEquals("Class FakeObj missing @DtoFrom annotation.",
          e.getErrorInfo());
    }
  }

  @Test
  void testMissingDtoInputGetter() throws WsSrvException {
    try {
      _mapper.map(new BadObj(1), BadDto.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.MISSING_MODEL_INPUT_GETTER.name(),
          e.getErrorCode());
      assertEquals("Unable find getter for field BadObj.b", e.getErrorInfo());
    }
  }

  @Test
  void testInvalidInputEntity() {
    try {
      _mapper.map(getTestBaseObj(), ExtDto.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.INVALID_INPUT_ENTITY.name(), e.getErrorCode());
      assertEquals(
          "Input entity of type BaseObj differs from expected ExtObj " +
              "defined in @DtoFrom annotation.",
          e.getErrorInfo());
    }
  }

  @Test
  void testListMap() throws WsSrvException {
    List<ExtObj> list = new ArrayList<>();

    list.add(getTestExtObj());
    list.add(getTestExtObj());

    List<ExtDto> dlist = _mapper.map(list, ExtDto.class);

    assertEquals(2, dlist.size());
    checkExtDto(dlist.get(0));
    checkExtDto(dlist.get(1));
  }

  @Test
  void testPrimitiveFieldType() {
    try {
      _mapper.map(new FakeObj(), PrimitiveTypeDto.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.MISSING_MODEL_INPUT_GETTER.name(),
          e.getErrorCode());
      assertEquals("Unable find getter for field FakeObj.id", e.getErrorInfo());
    }
  }

  @Test
  void testSetDtoFieldFail() {
    try {
      _mapper.map(new TestObj(), TestDto.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.SET_DTO_FIELD_FAIL.name(), e.getErrorCode());
      assertEquals(
          "Error set field TestDto.test of type FakeDto with value of type BaseValue",
          e.getErrorInfo());
    }
  }

  @Test
  void testBaseConvert() throws WsSrvException {
    checkBaseObj(_mapper.convert(getTestBaseDto(), BaseObj.class));
  }

  @Test
  void testExtConvert() throws WsSrvException {
    checkExtObj(_mapper.convert(SharedTestUtils.getTestExtDto(), ExtObj.class));
  }

  @Test
  void testConvertInvalidInputEntity() {
    try {
      _mapper.convert(getTestBaseDto(), ExtObj.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.INVALID_INPUT_ENTITY.name(), e.getErrorCode());
      assertEquals(
          "Input entity of type SimpleDto differs from expected ExtObj defined in @DtoFrom annotation.",
          e.getErrorInfo());
    }
  }

  @Test
  void testConvertMissingOutputField() {
    try {
      _mapper.convert(new FakeDto("QQ"), FakeObj.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.MISSING_OUTPUT_FIELD.name(), e.getErrorCode());
      assertEquals("Unable find output field FakeObj.qq", e.getErrorInfo());
    }
  }

  @Test
  void testConvertPrimitifeField() {
    try {
      _mapper.convert(new PrimitiveTypeDto(), FakeObj.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.PRIMITIVE_DATATYPE.name(), e.getErrorCode());
      assertEquals("Detect primitive field PrimitiveTypeDto.id",
          e.getErrorInfo());
    }
  }

  @Test
  void testMissingInputGetter() {
    try {
      _mapper.convert(new IncompleteDto(), FakeObj.class);
      fail("WsSrvException expected.");
    } catch (WsSrvException e) {
      // Expecting exception
      assertEquals(ErrorCodes.MISSING_INPUT_GETTER.name(), e.getErrorCode());
      assertEquals("Unable find getter for field IncompleteDto.qq",
          e.getErrorInfo());
    }
  }

  @Test
  void testLinkedObj() throws WsSrvException {
    ExtObj xobj = getTestExtObj();

    List<LinkedObj> list = new ArrayList<>();
    list.add(getTestLinkedObj(1, xobj));
    list.add(getTestLinkedObj(2, xobj));
    xobj.setLinked(list);

    ExtDto xdto = _mapper.map(xobj, ExtDto.class);

    assertNull(xdto.getLinked(), "Linked objects are not null.");
  }

  @Test
  void testLinkedDto() throws WsSrvException {
    ExtDto dto = new ExtDto(1, 2L, new ValueDto("test"), null);

    // Backward reference in LinkedDto -> ExtDto is not required 
    // since ExtDto is coming as single self-contained object
    List<LinkedDto> list = new ArrayList<>();
    list.add(getTestLinkedDto(1, null));
    list.add(getTestLinkedDto(2, null));
    dto.setLinked(list);

    ExtObj xobj = _mapper.convert(dto, ExtObj.class);

    List<LinkedObj> lst = xobj.getLinked();
    assertNotNull(lst, "Linked objects is not NULL.");
    assertEquals(2, lst.size(), "Size of list of linked object doesn't match.");

    for (int i = 0; i < lst.size(); i++) {
      LinkedObj lobj = lst.get(i);
      assertEquals(i + 1, lobj.getId(),
          "Linked object id #" + i + " doesn't match");
    }
  }

  @Test
  void testGenericDto() throws WsSrvException {
    GenericTestDto dto = new GenericTestDto();
    dto.setDto(SharedTestUtils.getTestExtDto());

    List<ExtDto> list = new ArrayList<>();
    list.add(SharedTestUtils.getTestExtDto());
    dto.setList(list);

    GenericTestObj gto = _mapper.convert(dto, GenericTestObj.class);
    checkExtObj(gto.getObj());

    List<ExtObj> lst = gto.getList();
    assertNotNull(lst, "Generic Tets Object List is NULL");
    checkExtObj(lst.get(0));
  }

  @Test
  void testGenericObj() throws WsSrvException {
    GenericTestObj obj = new GenericTestObj();
    obj.setObj(getTestExtObj());

    List<ExtObj> list = new ArrayList<>();
    list.add(getTestExtObj());
    obj.setList(list);

    GenericTestDto gtd = _mapper.map(obj, GenericTestDto.class);
    ExtDto dto = gtd.getDto();
    checkExtDto(dto);

    List<ExtDto> lst = gtd.getList();
    assertNotNull(lst, "Generic Tets Dto List is NULL");
    checkExtDto(lst.get(0));
  }

  private LinkedDto getTestLinkedDto(int i, ExtDto xdto) {
    LinkedDto ldto = new LinkedDto();
    ldto.setId(i);
    ldto.setExtDto(xdto);

    return ldto;
  }

  private LinkedObj getTestLinkedObj(int id, ExtObj extObj) {
    LinkedObj lobj = new LinkedObj();
    lobj.setId(id);
    lobj.setExtObj(extObj);

    return lobj;
  }

  private void checkBaseDto(SimpleDto dto) {
    assertEquals(1, dto.getId());
    assertEquals(2L, dto.getB());
  }

  private void checkBaseObj(BaseObj obj) {
    assertEquals(1, obj.getA());
    assertEquals(2L, obj.getB());
  }

  private void checkExtDto(ExtDto dto) {
    checkBaseDto(dto);
    assertNotNull(dto.getValue());
    assertEquals("test", dto.getValue().getValue());

    List<ValueDto> list = dto.getValues();
    assertNotNull(list);
    assertEquals(1, list.size());
    assertEquals("test", list.get(0).getValue());
  }

  private void checkExtObj(ExtObj obj) {
    assertNotNull(obj, "Ext Object is NULL");
    checkBaseObj(obj);

    List<BaseValue> list = obj.getValues();
    assertNotNull(list);
    assertEquals(1, list.size());
    assertEquals("test", list.get(0).getValue());
  }

  private BaseObj getTestBaseObj() {
    return new BaseObj(1, 2L);
  }

  private SimpleDto getTestBaseDto() {
    return new SimpleDto(1, 2L);
  }

  private ExtObj getTestExtObj() {
    List<BaseValue> values = new ArrayList<>();
    values.add(new BaseValue("test"));

    return new ExtObj(1, 2L, new BaseValue("test"), values);
  }
}

class BadObj {

  private final Integer a;

  public BadObj(Integer a) {
    this.a = a;
  }

  public Integer getA() {
    return a;
  }
}

class FakeObj {

  private FakeObj test;

  public FakeObj(boolean isDoubleFake) {
    if (isDoubleFake)
      test = new FakeObj();
  }

  public FakeObj() {
    this(false);
  }

  public FakeObj getTest() {
    return test;
  }

  public void setTest(FakeObj test) {
    this.test = test;
  }
}

@DtoFor(FakeObj.class)
class FakeDto {

  @ModelItem
  private String qq;

  public FakeDto() {
  }

  public FakeDto(String qq) {
    this.qq = qq;
  }

  public String getQq() {
    return qq;
  }
}

@DtoFor(FakeObj.class)
class PrimitiveTypeDto {
  @ModelItem
  private int id;

  public PrimitiveTypeDto() {
  }

  public int getId() {
    return id;
  }
}

class TestObj {

  private BaseValue test;

  public TestObj() {
    test = new BaseValue("test");
  }

  public BaseValue getTest() {
    return test;
  }

  public void setTest(BaseValue test) {
    this.test = test;
  }
}

@DtoFor(TestObj.class)
class TestDto {

  @ModelItem
  private FakeDto test;

  public TestDto() {
  }

  public FakeDto getTest() {
    return test;
  }

  public void setTest(FakeDto test) {
    this.test = test;
  }
}

@DtoFor(FakeObj.class)
class IncompleteDto {

  @ModelItem
  private String qq;

  public IncompleteDto() {
  }
}