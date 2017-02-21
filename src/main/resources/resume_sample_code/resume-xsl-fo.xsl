<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="main">
                    <fo:region-body region-name="body"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="main">
                <fo:flow flow-name="body">
                    <xsl:apply-templates/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <xsl:template match="resume">
        <fo:block font-family="sans-serif">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>
    <xsl:template match="school">
        <fo:block font-family="sans-serif" font-size="24pt">
            <xsl:value-of select="name"/>
        </fo:block>
        <fo:block font-family="sans-serif">
            <xsl:value-of select="degree"/>
        </fo:block>
        <fo:block font-family="sans-serif">
            <xsl:value-of select="year"/>
        </fo:block>
        <fo:block font-family="sans-serif">
            <xsl:value-of select="location/city"/>, <xsl:value-of select="location/state"/>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>
