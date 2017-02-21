<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:svg="http://java.sun.com/xml/ns/j2ee">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="main"

                                       margin-top="0.5cm"

                                       margin-bottom="0.5cm"

                                       margin-left="1cm"

                                       margin-right="0.5cm">

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
    <xsl:template match="profile">

        <fo:block font-family="sans-serif">
            <fo:block text-align-last="right" font-family="sans-serif" font-size="8pt" space-after="8pt"
                      color="#b3b3b3">
                <xsl:text> This document is dynamically generated using Apache(tm) FOP, for more information or source code, view my website below</xsl:text>
            </fo:block>
            <fo:block text-align-last="center">
                <fo:external-graphic
                        content-height="scale-to-fit" height="50pt" content-width="50pt"
                        scaling="non-uniform">
                    <xsl:attribute name="src">
                        <xsl:value-of select="faceImg"/>
                    </xsl:attribute>
                </fo:external-graphic>
            </fo:block>
            <fo:block text-align-last="center" font-family="Myriad Pro" font-size="24pt">
                <xsl:value-of select="firstName"/>
                <xsl:text> </xsl:text>
                <xsl:value-of select="lastName"/>
            </fo:block>
            <fo:block text-align-last="center" font-family="sans-serif" font-size="8pt" color="#b3b3b3">
                <fo:block>
                    <xsl:text>Email: </xsl:text>
                    <xsl:value-of select="contact/email"/>
                </fo:block>
                <fo:block>
                    <xsl:text>My site: </xsl:text>
                    <xsl:value-of select="contact/website"/>
                </fo:block>
                <!--<fo:block>-->
                <!--github :-->
                <!--<xsl:value-of select="contact/github"/>-->
                <!--</fo:block>-->
                <!--<fo:block>-->
                <!--linkedin :-->
                <!--<xsl:value-of select="contact/linkedin"/>-->
                <!--</fo:block>-->
            </fo:block>
            <fo:block text-align-last="center" font-family="Myriad Pro" font-size="12pt">
                <xsl:text> • </xsl:text>
                <xsl:value-of select="objective/personalEstimate"/>
            </fo:block>
            <fo:block text-align-last="center" font-family="Myriad Pro" font-size="12pt">
                <xsl:text> • </xsl:text>
                <xsl:value-of select="objective/interests"/>
            </fo:block>
            <fo:block font-family="sans-serif" font-weight="bold">
                <xsl:text>Experience: </xsl:text>
                <fo:block space-after="1em"></fo:block>
            </fo:block>
            <fo:block font-family="sans-serif">
                <xsl:for-each select="workExperienceList/workExperience">
                    <fo:block font-family="sans-serif" font-weight="bold" margin-left="30pt" font-size="12pt">
                        <xsl:value-of select="role"/>
                    </fo:block>
                    <fo:block>
                        <fo:block margin-left="1em" color="#666666" font-size="13">
                            <fo:external-graphic
                                    content-height="scale-to-fit" height="15pt" content-width="15pt"
                                    scaling="non-uniform">
                                <xsl:attribute name="src">
                                    <xsl:value-of select="icon"/>
                                </xsl:attribute>
                            </fo:external-graphic>

                            <xsl:text>    </xsl:text>
                            <xsl:value-of select="name"/>
                        </fo:block>
                        <fo:block margin-left="32pt" color="#666666" font-size="10pt">
                            <!--<fo:leader leader-pattern="space"/>-->
                            <xsl:text>    </xsl:text>
                            <xsl:value-of select="start"/>
                            <xsl:text> - </xsl:text>
                            <xsl:value-of select="end"/>
                            <xsl:text> • </xsl:text>
                            <xsl:value-of select="length"/>
                            <xsl:text> • </xsl:text>
                            <xsl:value-of select="address/city"/>
                            <xsl:text> , </xsl:text>
                            <xsl:value-of select="address/state"/>

                        </fo:block>
                    </fo:block>

                    <fo:block font-family="sans-serif" space-before="0.5em" color="#666666">
                        <xsl:for-each select="bulletList/bullet">
                            <fo:block font-family="sans-serif" margin-left="3em">
                                <xsl:text>•</xsl:text>
                                <xsl:text>                    </xsl:text>
                                <xsl:value-of select="content"/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                    <!--<fo:block space-after="1em"></fo:block>-->
                    <fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" color="#D3D3D3"
                               rule-thickness="1pt"/>
                    <!--<fo:block space-after="1em"></fo:block>-->
                </xsl:for-each>
            </fo:block>
            <fo:block font-family="sans-serif" font-weight="bold">
                <xsl:text>Education: </xsl:text>
                <fo:block space-after="1em"></fo:block>
            </fo:block>
            <fo:block font-family="sans-serif">
                <xsl:for-each select="educationList/education">
                    <fo:block font-family="sans-serif" font-weight="bold" margin-left="30pt" font-size="12pt">
                        <xsl:value-of select="name"/>
                    </fo:block>
                    <fo:block margin-left="1em" color="#666666" font-size="13pt">
                        <fo:external-graphic
                                content-height="scale-to-fit" height="15pt" content-width="15pt"
                                scaling="non-uniform">
                            <xsl:attribute name="src">
                                <xsl:value-of select="icon"/>
                            </xsl:attribute>
                        </fo:external-graphic>
                        <xsl:text>    </xsl:text>
                        <xsl:value-of select="degree"/>
                        <xsl:text> , </xsl:text>
                        <xsl:value-of select="major"/>
                    </fo:block>
                    <fo:block margin-left="32pt" color="#666666" font-size="10pt">
                        <!--<fo:leader leader-pattern="space"/>-->
                        <xsl:value-of select="start"/>
                        <xsl:text> - </xsl:text>
                        <xsl:value-of select="end"/>
                        <xsl:text> • </xsl:text>
                        <xsl:value-of select="length"/>

                    </fo:block>
                    <fo:block font-family="sans-serif" space-before="0.5em" color="#666666">
                        <xsl:for-each select="bulletList/bullet">
                            <fo:block font-family="sans-serif" margin-left="3em">
                                <xsl:text>•</xsl:text>
                                <xsl:text>                    </xsl:text>
                                <xsl:value-of select="content"/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                    <fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" color="#D3D3D3"
                               rule-thickness="1pt"/>
                </xsl:for-each>
            </fo:block>
            <!--<fo:float float="left">-->
            <!--<fo:block>-->
            <!--<fo:external-graphic rule-style="float" src="url('sdsd.png')" content-height="scale-to-fit"-->
            <!--height="21pt"-->
            <!--content-width="21pt"/>-->
            <!--</fo:block>-->
            <!--</fo:float>-->
            <!--<fo:block>-->
            <!--Fig. 3: A Smiling Face-->
            <!--</fo:block>-->
            <fo:block>
                <fo:block font-weight="bold">
                    <xsl:text>Featured Skills: </xsl:text>
                </fo:block>

                <fo:block>
                    <xsl:for-each select="skillSet/category">
                        <fo:block font-family="sans-serif" margin-left="30pt" font-size="8pt">
                            <xsl:value-of select="name"/>
                            <xsl:text> : </xsl:text>
                            <xsl:for-each select="technologyList/technology">
                                <xsl:value-of select="."/>
                                <xsl:text> , </xsl:text>
                            </xsl:for-each>
                        </fo:block>
                        <!--<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" color="#D3D3D3"-->
                        <!--rule-thickness="1pt"/>-->
                    </xsl:for-each>
                </fo:block>
            </fo:block>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>
