<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="book_title"/>
    <xsl:template match="/">
        1. Book titles
        <xsl:apply-templates select="//books/child::*/title"/>

        2. Books that have 'author_2' as coauthor
        <xsl:apply-templates
                select="//book[authors/coauthors/id = 'author_2']/title" />
        3. Total number of chapters
        <xsl:apply-templates>
            <xsl:decimal-format  pattern-separator="\n" />
            <xsl:value-of select="count(//book/chapters_and_sections/chapter)"/>
        </xsl:apply-templates>

        4. Display coauthors for a book that has author = Jeff Keller or coauthors from a book that has title that starts with letter A
        <xsl:apply-templates
                select="//author_data[id = //book[authors/id = //author_data[author_name = 'Jeff Keller']/id]/authors/coauthors/id or  id =//book[authors/coauthors/id = //book[title[starts-with(.,'A')]]/authors/coauthors/id]]/author_name" />

        5. Title for Chapter that contains number 1 for all books
        <xsl:apply-templates
                select="//book/chapters_and_sections/chapter[number=contains(.,'1')]/name" />

        6. Book ids that were not published on 2010
        <xsl:apply-templates
                select="//book[not(contains(copyright_year,'2010'))]/@id" />
        7. Book authors that don't have a co-author
        <xsl:apply-templates
                select="//author_data[id =//book/authors[count(coauthors/id) = 0]/id]/author_name"/>

    </xsl:template>

</xsl:stylesheet>