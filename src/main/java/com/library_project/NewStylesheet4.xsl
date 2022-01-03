<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <xsl:text>Chapter number</xsl:text>
        <br></br>
        <xsl:for-each select="//book/chapters_and_sections/chapter">
            <xsl:text>Chapter</xsl:text>
            <xsl:for-each select=".">
                <div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
                    <xsl:value-of select="name"/>
                </div>
            </xsl:for-each>
        </xsl:for-each>
        <div style="background-color:teal;color:white;padding:4px">
            <span style="font-weight:bold">Title: </span>
            <xsl:value-of select="count(//book/chapters_and_sections/chapter)"/>
        </div>
    </body>
</html>