/**
 * ***************************************************************************
 * Copyright (c) 2010 Qcadoo Limited
 * Project: Qcadoo Framework
 * Version: 1.2.0-SNAPSHOT
 *
 * This file is part of Qcadoo.
 *
 * Qcadoo is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * ***************************************************************************
 */
package com.qcadoo.report.api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

/**
 * Utilities for pdf fonts management.
 * 
 */
public final class FontUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FontUtils.class);

    private static Font dejavuBold19Light;

    private static Font dejavuBold19Dark;

    private static Font dejavuBold11Dark;

    private static Font dejavuBold11Light;

    private static Font dejavuRegular9Light;

    private static Font dejavuRegular9Dark;

    private static Font dejavuBold9Dark;

    private static Font dejavuRegular10Dark;

    private static Font dejavuBold10Dark;

    private static BaseFont dejavu;

    private FontUtils() {
    }

    /**
     * Prepare fonts.
     * 
     * @throws DocumentException
     * @throws IOException
     */
    public static synchronized void prepare() throws DocumentException, IOException {
        if (dejavuBold10Dark == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Pdf fonts initialization");
            }
            try {
                FontFactory.register("/fonts/dejaVu/DejaVuSans.ttf");
                dejavu = BaseFont.createFont("/fonts/dejaVu/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } catch (ExceptionConverter e) {
                LOG.warn("Font not found, using embedded font helvetica");
                dejavu = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
            }
            dejavuBold19Light = new Font(dejavu, 19);
            dejavuBold19Light.setStyle(Font.BOLD);
            dejavuBold19Light.setColor(ColorUtils.getLightColor());
            dejavuBold19Dark = new Font(dejavu, 19);
            dejavuBold19Dark.setStyle(Font.BOLD);
            dejavuBold19Dark.setColor(ColorUtils.getDarkColor());
            dejavuRegular9Light = new Font(dejavu, 9);
            dejavuRegular9Light.setColor(ColorUtils.getLightColor());
            dejavuRegular9Dark = new Font(dejavu, 9);
            dejavuRegular9Dark.setColor(ColorUtils.getDarkColor());
            dejavuBold9Dark = new Font(dejavu, 9);
            dejavuBold9Dark.setColor(ColorUtils.getDarkColor());
            dejavuBold9Dark.setStyle(Font.BOLD);
            dejavuBold11Dark = new Font(dejavu, 11);
            dejavuBold11Dark.setColor(ColorUtils.getDarkColor());
            dejavuBold11Dark.setStyle(Font.BOLD);
            dejavuBold11Light = new Font(dejavu, 11);
            dejavuBold11Light.setColor(ColorUtils.getLightColor());
            dejavuBold11Light.setStyle(Font.BOLD);
            dejavuRegular10Dark = new Font(dejavu, 10);
            dejavuRegular10Dark.setColor(ColorUtils.getDarkColor());
            dejavuBold10Dark = new Font(dejavu, 10);
            dejavuBold10Dark.setColor(ColorUtils.getDarkColor());
            dejavuBold10Dark.setStyle(Font.BOLD);
        }
    }

    public static Font getDejavuBold19Light() {
        return dejavuBold19Light;
    }

    public static Font getDejavuBold19Dark() {
        return dejavuBold19Dark;
    }

    public static Font getDejavuBold11Dark() {
        return dejavuBold11Dark;
    }

    public static Font getDejavuBold11Light() {
        return dejavuBold11Light;
    }

    public static Font getDejavuRegular9Light() {
        return dejavuRegular9Light;
    }

    public static Font getDejavuRegular9Dark() {
        return dejavuRegular9Dark;
    }

    public static Font getDejavuBold9Dark() {
        return dejavuBold9Dark;
    }

    public static Font getDejavuRegular10Dark() {
        return dejavuRegular10Dark;
    }

    public static Font getDejavuBold10Dark() {
        return dejavuBold10Dark;
    }

    public static BaseFont getDejavu() {
        return dejavu;
    }
}
