<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="bookId" xml:space="preserve"/>
    <xsl:template match="/">
    <books>
        <xsl:for-each select="library/books/book[@id=$bookId]">
            <title><xsl:value-of select="title"/></title>
            <isbn><xsl:value-of select="isbn"/></isbn>
        </xsl:for-each>
    </books>
    </xsl:template>
</xsl:stylesheet>
