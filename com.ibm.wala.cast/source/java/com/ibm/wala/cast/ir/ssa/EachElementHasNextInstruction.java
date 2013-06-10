/******************************************************************************
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *****************************************************************************/
package com.ibm.wala.cast.ir.ssa;

import java.util.Collection;
import java.util.Collections;

import com.ibm.wala.ssa.SSAAbstractUnaryInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAInstructionFactory;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.types.TypeReference;

/**
 * This instruction represents iterating through the properties of its receiver object. The use represents an object,
 * and the l-value represents a boolean indicating whether the object has more properties. This instruction does not
 * currently take the previously-returned property as an argument, so it is a somewhat incomplete model as of now.
 * 
 * Iterating across the fields or properties of a given object is a common idiom in scripting languages, which is why
 * the IR has first-class support for it.
 * 
 * @author Julian Dolby (dolby@us.ibm.com)
 */
public class EachElementHasNextInstruction extends SSAAbstractUnaryInstruction {

  public EachElementHasNextInstruction(int lValue, int objectRef) {
    super(lValue, objectRef);
  }

  @Override
  public SSAInstruction copyForSSA(SSAInstructionFactory insts, int[] defs, int[] uses) {
    return ((AstInstructionFactory)insts).EachElementHasNextInstruction((defs == null) ? getDef(0) : defs[0], (uses == null) ? getUse(0) : uses[0]);
  }

  @Override
  public String toString(SymbolTable symbolTable) {
    return getValueString(symbolTable, getDef(0)) + " = has next property: " + getValueString(symbolTable, getUse(0));
  }

  @Override
  public void visit(IVisitor v) {
    ((AstInstructionVisitor) v).visitEachElementHasNext(this);
  }

  @Override
  public Collection<TypeReference> getExceptionTypes() {
    return Collections.emptySet();
  }
}
