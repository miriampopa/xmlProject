<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="name" xml:space="preserve"/>
    <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
        <p>Author: <xsl:value-of select="translate($name,'%20',' ')" /></p>

        <p>Display coauthors for a book that has author = Jeff Keller or coauthors from a book that has title that starts with letter A </p>
        <xsl:for-each select="//author_data[id = //book[authors/id = //author_data[author_name = translate($name,'%20',' ')]/id]/authors/coauthors/id or  id =//book[authors/coauthors/id = //book[title[starts-with(.,'A')]]/authors/coauthors/id]]">
            <div style="background-color:teal;color:white;padding:4px">
                <span style="font-weight:bold">Name - </span>
                <xsl:value-of select="author_name"/>
                <br></br>
                <span style="font-weight:bold">Author id - </span>
                <xsl:value-of select="id"/>
            </div>
        </xsl:for-each>
    </body>
</html>