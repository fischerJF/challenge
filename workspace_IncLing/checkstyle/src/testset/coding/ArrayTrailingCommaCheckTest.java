////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2018 the original author or authors.
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

package testset.coding;

import static checks.coding.ArrayTrailingCommaCheck.MSG_KEY;

import org.junit.Assert;
import org.junit.Test;

import checks.coding.ArrayTrailingCommaCheck;
//import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import checkstyle.DefaultConfiguration;
import testset.checkstyle.AbstractModuleTestSupport;

public class ArrayTrailingCommaCheckTest
    extends AbstractModuleTestSupport {

    @Override
    protected String getPackageLocation() {
        return "com/puppycrawl/tools/checkstyle/checks/coding/arraytrailingcomma";
    }

    @Test
    public void testDefault()
            throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(ArrayTrailingCommaCheck.class);
        final String[] expected = {
            "17: " + getCheckMessage(MSG_KEY),
            "37: " + getCheckMessage(MSG_KEY),
        };
        verify(checkConfig, getPath("InputArrayTrailingComma.java"), expected);
    }

    @Test
    public void testTokensNotNull() {
        final ArrayTrailingCommaCheck check = new ArrayTrailingCommaCheck();
        Assert.assertNotNull("Invalid acceptable tokens", check.getAcceptableTokens());
        Assert.assertNotNull("Invalid default tokens", check.getDefaultTokens());
        Assert.assertNotNull("Invalid required tokens", check.getRequiredTokens());
    }

}
