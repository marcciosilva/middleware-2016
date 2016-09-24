<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
		<ListaItems>		
		<xsl:for-each select="/dtoOrder/itemsList">
			<Item>
				<idProducto><xsl:value-of select="./productId"></xsl:value-of></idProducto>
                                <cantidad><xsl:value-of select="./amount"></xsl:value-of></cantidad>                     
                                <xsl:variable name="idorden" select="/dtoOrder/orderNumber"/>
                                <xsl:variable name="iditem" select="./itemNumber"/>
				<identtificadortransaccion>
					<xsl:value-of select="concat($idorden,$iditem)">					
				</identtificadortransaccion>
                                <xsl:variable name="dateTime" select="/dtoOrder/creationDate"/>
				<fechaCreacion><xsl:value-of select="concat(
                      substring($dateTime, 9, 2),
                      '/',
                      substring($dateTime, 6, 2),
                      '/',
                      substring($dateTime, 1, 4),
                      ' ',
                      substring($dateTime, 12, 8)                      
                      )">
                      </xsl:value-of></fechaCreacion>
                              
			</Item>
		</xsl:for-each>
		</ListaItems>
	</xsl:template>
</xsl:stylesheet>