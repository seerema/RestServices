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

package com.seerema.srv.shared;

import com.seerema.srv.shared.annotations.DtoFor;

/**
 * AbstractTestDto
 */
@DtoFor(GenericTestObj.class)
public class GenericTestDto extends AbstractTestDto<ExtDto> {

}
