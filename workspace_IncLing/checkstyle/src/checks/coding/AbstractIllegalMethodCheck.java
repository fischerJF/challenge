////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2015 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package checks.coding;

import api.Check;
import api.DetailAST;
import api.TokenTypes;

/**
 * Provide support for checking for a method with a specified name and no
 * arguments.
 * @author Oliver Burn
 */
public abstract class AbstractIllegalMethodCheck extends Check
{
    /** Name of method to disallow. */
    private final String methodName;
    /** The error key to report with. */
    private final String errorKey;

    /**
     * Creates an instance.
     * @param methodName name of the method to disallow.
     * @param errorKey the error key to report with.
     */
    public AbstractIllegalMethodCheck(String methodName, String errorKey)
    {
        this.methodName = methodName;
        this.errorKey = errorKey;
    }

    @Override
    public int[] getDefaultTokens()
    {
        return new int[] {TokenTypes.METHOD_DEF};
    }

    @Override
    public int[] getAcceptableTokens()
    {
        return new int[] {TokenTypes.METHOD_DEF};
    }

    @Override
    public void visitToken(DetailAST aAST)
    {
        final DetailAST mid = aAST.findFirstToken(TokenTypes.IDENT);
        final String name = mid.getText();

        if (methodName.equals(name)) {

            final DetailAST params = aAST.findFirstToken(TokenTypes.PARAMETERS);
            final boolean hasEmptyParamList =
                !params.branchContains(TokenTypes.PARAMETER_DEF);

            if (hasEmptyParamList) {
                log(aAST.getLineNo(), errorKey);
            }
        }
    }
}
