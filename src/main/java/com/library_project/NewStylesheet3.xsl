<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <xsl:param name="word" xml:space="preserve"/>
        <xsl:text>Book titles that have as author an author that has a book with title that contains:  <xsl:value-of select="$word"/></xsl:text>
        <xsl:for-each select="//book[authors/id =//book[contains(title, $word)]/authors/id]">
            <div style="background-color:teal;color:white;padding:4px">
                <span style="font-weight:bold">Title: </span>
                <xsl:value-of select="title"/>
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