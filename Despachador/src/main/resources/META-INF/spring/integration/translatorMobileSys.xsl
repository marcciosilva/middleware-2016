<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
		<ListaItems>		
		<xsl:for-each select="/dtoOrder/itemsList">
			<Item>
				<idCliente><xsl:value-of select="/dtoOrder/customerId"></xsl:value-of></idCliente>
				<idProducto><xsl:value-of select="./productId"></xsl:value-of></idProducto>
				<fechaCreacion><xsl:value-of select="/dtoOrder/creationDate"></xsl:value-of></fechaCreacion>
				<idOrdenItem>
					<xsl:value-of select="/dtoOrder/orderNumber"></xsl:value-of>
					<xsl:value-of select="./itemNumber"></xsl:value-of>					
				</idOrdenItem>
			</Item>
		</xsl:for-each>
		</ListaItems>
	</xsl:template>
</xsl:stylesheet>