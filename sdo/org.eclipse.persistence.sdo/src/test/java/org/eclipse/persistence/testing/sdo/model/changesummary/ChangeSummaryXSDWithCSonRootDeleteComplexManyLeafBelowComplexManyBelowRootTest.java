/*
 * Copyright (c) 1998, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.testing.sdo.model.changesummary;

import junit.textui.TestRunner;
import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;

/**
 * There are 2 types of tests model and XML
 * both use the same assertion sets and 2 different CS-on-root and CS-on-child XSD schemas.
 * We will not duplicate the full loadandsave tests here, just toString versions
 *
 *  Model:
 *  1. read in model from a populated XML file - via setup()
 *  2. rootObject will be in pre-operation state
 *  3. get references to the original object tree - before operation is performed
 *  4. [perform operation]
 *  -
 *  5. compare new modified model with original objects - in memory
 *  6.
 *
 *
 *  XML:
 *  1. read in modified model from a modified XML file with CS pre-populated - [perform operation]
 *  2. rootObject will be in post-operation state
 *  3. partially compare new modified model with ([original] objects by doing an effective undo or using oldValues) - in memory
 *  4. save model to XML
 *  5. compare generated XML with file system XML
 *
 *  Combined:
 *
 */
public class ChangeSummaryXSDWithCSonRootDeleteComplexManyLeafBelowComplexManyBelowRootTest extends ChangeSummaryOnRootTestCases {
    protected String getControlFileName() {
        return ("./org/eclipse/persistence/testing/sdo/helper/xmlhelper/changesummary/PODWithCSonRootDeleteComplexManyLeafBelowComplexManyBelowRoot.xml");
    }

    protected String getControlFileName2() {
        return getControlFileName();
    }

    public ChangeSummaryXSDWithCSonRootDeleteComplexManyLeafBelowComplexManyBelowRootTest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        String[] arguments = { "-c", "org.eclipse.persistence.testing.sdo.model.changesummary.ChangeSummaryXSDWithCSonRootDeleteComplexManyLeafBelowComplexManyBelowRootTest" };
        TestRunner.main(arguments);
    }

    public void setUp() {
        super.setUp();// watch setup redundancy
    }

    /**
     * Purpose: this test will delete the first item (price[1]) in a child list of a parent list.
     * The result of this is that the 2nd item (price[2]) will in effect move from index 2 to 1 in the resulting dataobject
     *
     * Before Delete
   <ns0:items>
      <ns0:item partNum="872-AA">
        ....
      </ns0:item>
      <ns0:item partNum="926-AA">
         <ns0:product>
            <ns0:price>
                <ns0:dollarAmount>39.95</ns0:dollarAmount>
                <ns0:currency>US</ns0:currency>
            </ns0:price>
            <ns0:price>
                <ns0:dollarAmount>45.95</ns0:dollarAmount>
                <ns0:currency>CDN</ns0:currency>
            </ns0:price>
         </ns0:product>
      </ns0:item>
   </ns0:items>
     *
     * After Delete
   <ns0:items>
      <ns0:item partNum="872-AA">
      ....
      </ns0:item>
      <ns0:item partNum="926-AA">
         <ns0:product>
            <ns0:price>
               <ns0:dollarAmount>45.95</ns0:dollarAmount>
               <ns0:currency>CDN</ns0:currency>
            </ns0:price>
         </ns0:product>
      </ns0:item>
   </ns0:items>
     */
    protected void verifyAfterLoad(XMLDocument document) {
        super.verifyAfterLoad(document);
        // replace global object with one from xml file (with cs pre-populated)
        rootObject = document.getRootObject();
        cs = rootObject.getChangeSummary();
        DataObject itemsDO = rootObject.getDataObject("items");
        DataObject item1DO = rootObject.getDataObject("items/item[1]");
        DataObject item2DO = rootObject.getDataObject("items/item[2]");

        DataObject item1ProductDO = item1DO.getDataObject("product");
        DataObject item1ProductPrice1DO = item1ProductDO.getDataObject("price[1]");
        DataObject item1ProductPrice2DO = item1ProductDO.getDataObject("price[2]");

        DataObject item2ProductDO = item2DO.getDataObject("product");
        DataObject item2ProductPrice1DO = null;//item2ProductDO.getDataObject("price[1]");
        DataObject item2ProductPrice2DO = item2ProductDO.getDataObject("price[1]");// moved from index 2

        assertCSonRootDeleteDetachUnsetComplexManyLeafBelowComplexManyBelowRoot(true,//
                                                                                itemsDO,//
                                                                                item1DO,//
                                                                                item1ProductDO,//
                                                                                item1ProductPrice1DO,//
                                                                                item1ProductPrice2DO,//
                                                                                item2DO,//
                                                                                item2ProductDO,//
                                                                                item2ProductPrice1DO,//
                                                                                item2ProductPrice2DO);
    }

    public void testDeleteComplexManySingleBelowRoot() {
        defineTypes();
        // 1. read in model from a populated XML file - via setup()
        // 2. rootObject will be in pre-operation state
        // 3. get references to the original object tree - before operation is performed
        // 4. [perform operation]
        // 5. compare new modified model with original objects - in memory
        DataObject itemsDO = rootObject.getDataObject("items");
        DataObject item1DO = rootObject.getDataObject("items/item[1]");
        DataObject item2DO = rootObject.getDataObject("items/item[2]");

        DataObject item1ProductDO = item1DO.getDataObject("product");
        DataObject item1ProductPrice1DO = item1ProductDO.getDataObject("price[1]");
        DataObject item1ProductPrice2DO = item1ProductDO.getDataObject("price[2]");

        DataObject item2ProductDO = item2DO.getDataObject("product");
        DataObject item2ProductPrice1DO = item2ProductDO.getDataObject("price[1]");
        DataObject item2ProductPrice2DO = item2ProductDO.getDataObject("price[2]");

        cs.beginLogging();
        item2ProductPrice1DO.delete();

        assertCSonRootDeleteDetachUnsetComplexManyLeafBelowComplexManyBelowRoot(false,//
                                                                                itemsDO,//
                                                                                item1DO,//
                                                                                item1ProductDO,//
                                                                                item1ProductPrice1DO,//
                                                                                item1ProductPrice2DO,//
                                                                                item2DO,//
                                                                                item2ProductDO,//
                                                                                item2ProductPrice1DO,//
                                                                                item2ProductPrice2DO);
    }
}