<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="name" xml:space="preserve"/>
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <p>Author: <xsl:value-of select="translate($name,'%20',' ')"/></p>
        <xsl:value-of select="//author_data[author_name =translate($name,'%20',' ')]/id "/>

        <p>Results for Jeff Keller as author/co-author</p>
        <xsl:for-each select="//book[authors/id = //author_data[author_name = translate($name,'%20',' ')]/id or authors/coauthors/id = //author_data[author_name = translate($name,'%20',' ')]/id]">
            <div style="background-color:teal;color:white;padding:4px">
                <span style="font-weight:bold">Title - </span>
                <xsl:element name="book">
                    <xsl:value-of select="title"/>
                </xsl:element>
                <br></br>
                <span style="font-weight:bold">Authors - </span>
                <xsl:element name="id">
                    <xsl:value-of select="authors/id"/>
                </xsl:element>
                <br></br>
                <span style="font-weight:bold">CoAuthors - </span>
                <xsl:element name="coauthors">
                    <xsl:value-of select="authors/coauthors"/>
                </xsl:element>
            </div>
        </xsl:for-each>
    </body>
</html>