/*
 * Copyright (c) 1998, 2019 Oracle and/or its affiliates. All rights reserved.
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
//     Mike Norman - Oct 29th 2008, create packager for Javase 6 'Containerless' Endpoint deployment
package org.eclipse.persistence.tools.dbws;

import static org.eclipse.persistence.internal.xr.Util.DBWS_SCHEMA_XML;
//EclipseLink imports
import static org.eclipse.persistence.internal.xr.Util.DBWS_WSDL;
import static org.eclipse.persistence.tools.dbws.DBWSPackager.ArchiveUse.archive;
import static org.eclipse.persistence.tools.dbws.Util.DBWS_PROVIDER_CLASS_FILE;
import static org.eclipse.persistence.tools.dbws.Util.SWAREF_FILENAME;
import static org.eclipse.persistence.tools.dbws.Util.UNDER_DBWS;

//javase imports
import java.io.File;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

/**
 * <p>
 * <b>PUBLIC:</b> JavasePackager extends {@link ProviderPackager}. It generates a simplified version<br>
 * of the JAX-WS Provider Endpoint that can be run using the Javase 6 'containerless' Endpoint API:
 * <pre>
 *   Endpoint endpoint = Endpoint.create(new &#064;WebService or &#064;WebServiceProvider);
 *   endpoint.publish(ENDPOINT_ADDRESS);
 *   QName serviceQName = new QName(serviceNamespace, serviceName);
 *   QName portQName = new QName(serviceNamespace, portName);
 *   service = Service.create(serviceQName);
 *   service.addPort(portQName, javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);
 *   ...
 *   Dispatch&lt;SOAPMessage&gt; dispatch = testService.createDispatch(portQName, SOAPMessage.class,
 *     Service.Mode.MESSAGE);
 *   SOAPMessage response = dispatch.invoke(request);
 *   ...
 * </pre>
 * Package files generated by parent according to the following layout:
 * <pre>
 * ${PACKAGER_ROOT} - could be a directory or a <code>.jar</code> file:
 *    +---META-INF
 *    |       eclipselink-dbws-or.xml
 *    |       eclipselink-dbws-ox.xml
 *    |       eclipselink-dbws-sessions.xml
 *    |       eclipselink-dbws.xml
 *    |
 *    +---_dbws
 *    |       DBWSProvider.class
 *    |
 *    \---wsdl
 *            <i>swaref.xsd</i>                 -- optional if using attachments
 *            eclipselink-dbws-schema.xsd
 *            eclipselink-dbws.wsdl
 * </pre>
 *
 * @author Mike Norman - michael.norman@oracle.com
 * @since EclipseLink 1.x
 */
public class JavasePackager extends ProviderPackager {

    public JavasePackager() {
        this(new JavaseArchiver(), "javase", archive);
    }
    protected JavasePackager(Archiver archiver, String packagerLabel, ArchiveUse useJavaArchive) {
        super(archiver, packagerLabel, useJavaArchive);
    }

    @Override
    public Archiver buildDefaultArchiver() {
        return new JavaseArchiver(this);
    }

    /**
     * by returning null, the generated _dbws.DDBWProvider class will have a slightly-different
     * class annotation:
     * <pre>
     * &#064;WebServiceProvider(
     *   // note: no 'wsdlLocation' attribute
     *   serviceName = ${serviceName},
     *   portName = ${servicePort},
     *   targetNamespace = ${serviceNamespace}
     * )
     * </pre>
     */
    @Override
    public String getWSDLPathPrefix() {
        return null;
    }

    static class JavaseArchiver extends JarArchiver {
        JavaseArchiver() {
            super();
        }
        JavaseArchiver(DBWSPackager packager) {
            super(packager);
        }
        @Override
        protected void addFilesToJarOutputStream(JarOutputStream jarOutputStream) {
            super.addFilesToJarOutputStream(jarOutputStream);
            /* and more ...
             * DBWSProvider.class
             * eclipselink-dbws.wsdl
             */
            try {
                jarOutputStream.putNextEntry(getDBWSProviderClassJarEntry());
                f = new File(packager.getStageDir(), DBWS_PROVIDER_CLASS_FILE);
                fis = new FileInputStream(f);
                for (int read = 0; read != -1; read = fis.read(buffer)) {
                    jarOutputStream.write(buffer, 0, read);
                }
                fis.close();
                f.deleteOnExit();

                jarOutputStream.putNextEntry(getWSDLJarEntry());
                f = new File(packager.getStageDir(), DBWS_WSDL);
                fis = new FileInputStream(f);
                for (int read = 0; read != -1; read = fis.read(buffer)) {
                    jarOutputStream.write(buffer, 0, read);
                }
                fis.close();
                f.deleteOnExit();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected JarEntry getSchemaJarEntry() {
            return new JarEntry(getWSDLPathPrefix() + DBWS_SCHEMA_XML);
        }
        @Override
        protected JarEntry getSWARefJarEntry() {
            return new JarEntry(getWSDLPathPrefix() + SWAREF_FILENAME);
        }
        ZipEntry getWSDLJarEntry() {
            return new JarEntry(getWSDLPathPrefix() + DBWS_WSDL);
        }
        ZipEntry getDBWSProviderClassJarEntry() {
            return new JarEntry(UNDER_DBWS + "/" + DBWS_PROVIDER_CLASS_FILE);
        }
    }
}
