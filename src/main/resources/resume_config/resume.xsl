<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Resume</title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="school">
        <h2><xsl:value-of select="name"/></h2>
        <p><xsl:value-of select="degree"/></p>
        <p><xsl:value-of select="year"/></p>
        <p><xsl:value-of select="location/city"/>, <xsl:value-of select="location/state"/>
        </p>
    </xsl:template>


</xsl:stylesheet>
