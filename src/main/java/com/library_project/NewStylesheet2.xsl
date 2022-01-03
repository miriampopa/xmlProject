<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <xsl:text>Book title starts with 'A'</xsl:text>
        <xsl:for-each select="//books/book[title= //book/title[starts-with(.,'A')]]">
            <div style="background-color:teal;color:white;padding:4px">
                <span style="font-weight:bold"><xsl:value-of select="title"/> - </span>
                <xsl:value-of select="subject"/>
            </div>
            <div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
                <p>
                    <xsl:value-of select="publisher"/>
                    <span style="font-style:italic"> (<xsl:value-of select="copyright_year"/>)</span>
                </p>
            </div>
            <xsl:text>Chapters</xsl:text>
            <xsl:for-each select="chapters_and_sections/chapter">
                <div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
                    <xsl:value-of select="name"/>
                </div>
            </xsl:for-each>
            <xsl:text>Sections</xsl:text>
            <xsl:for-each select="chapters_and_sections/section">
                <div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
                    <xsl:value-of select="name"/>
                </div>
            </xsl:for-each>

        </xsl:for-each>
    </body>
</html>