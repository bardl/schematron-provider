<?xml version="1.0" encoding="UTF-16" standalone="yes"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:sch="http://www.ascc.net/xml/schematron" xmlns:iso="http://purl.oclc.org/dsdl/schematron" xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="utf-8" />

    <xsl:template match="*">
        <xsl:text>/</xsl:text>
    </xsl:template>

</xsl:stylesheet>
