<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="year" xml:space="preserve"/>
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <p>Year: <xsl:value-of select="$year"/></p>

        <p>Books that were not published on <xsl:value-of select="$year"/></p>
        <xsl:for-each select="//book[not(contains(copyright_year,$year))]">
            <div style="background-color:teal;color:white;padding:4px">
                <span style="font-weight:bold">Title - </span>
                <xsl:element name="book">
                    <xsl:value-of select="title"/>
                </xsl:element>
                <br></br>
                <span style="font-weight:bold">Authors - </span>
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
                <span style="font-weight:bold">Copyright year</span>
                <xsl:element name="copyright_year">
                    <xsl:value-of select="copyright_year"/>
                </xsl:element>
            </div>
        </xsl:for-each>
    </body>
</html>